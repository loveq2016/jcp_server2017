package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.Answer;
import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.AnswerSer;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         付费追问
 */
public class PayRecycleAsk extends HttpServlet {
	private static final long serialVersionUID = 2750171635464982466L;
	private String ip;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ip = request.getRemoteAddr();
		String typeId = request.getParameter("typeId");
		String userId = request.getParameter("userId");
		String answerId = request.getParameter("answerId");
		String askBody = request.getParameter("askBody");
		String bills = request.getParameter("bills");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(bills) && StringUtil.isInteger(bills)) {
					int bs = Integer.parseInt(bills);
					if (StringUtil.isNotNull(typeId)
							&& StringUtil.isInteger(typeId)) {
						int type = Integer.parseInt(typeId);
						if (StringUtil.isNotNull(askBody)) {
							if (StringUtil.isNotNull(answerId)
									&& StringUtil.isInteger(answerId)) {
								int aId = Integer.parseInt(answerId);
								result = payRecycleAsk(uId, bs, type, askBody,
										aId);
							} else {
								result = JsonUtil.getRetMsg(7, "answerId 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(6, "提问内容不能为空");
						}
					} else {
						result = JsonUtil.getRetMsg(5, "typeId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(4, "bills 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "还有还没登录");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.println(result);

		out.flush();
		out.close();
	}

	private String payRecycleAsk(int uId, int bs, int type, String askBody,
			int aId) {
		Answer answer = AnswerSer.findAnswerById(aId);
		int teacherId = answer.getTeacherId();
		FamousTeacher teacher = FamousTeacherSer
				.findFamousTeacherById(teacherId);
		int isCatch = answer.getIsCatch();
		if (isCatch != 1) {
			// 回复没有被采纳
			return JsonUtil.getRetMsg(1, "回答还没有被采纳");
		}

		List<Ask> asks = AskSer.findAskByParentId(aId);
		if (asks.size() <= 3) {
			// 可以免费追问
			return JsonUtil.getRetMsg(2, "超过3次追问才需要消耗聚财币");
		}

		if (bs != 10) {
			// 追问需要消耗10个聚财币
			return JsonUtil.getRetMsg(3, "追问需要消耗10个聚财币");
		}

		Account account = AccountSer.findAccountByUserId(uId);
		User user = UserServer.findBaseInfoById(uId);

		if (account == null || account.getJucaiBills() < bs) {
			// 聚财币不足
			return JsonUtil.getRetMsg(4, "聚财币不足，请先充值");
		}

		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(bs);
		accountDetail.setOrderCode("");
		// 订单类型 0收入，1消费
		accountDetail.setDetailType(1);
		// 0聚财币，1积分
		accountDetail.setState(0);
		accountDetail.setRemark("【" + user.getNickName() + "】追问讲师【"
				+ teacher.getNickName() + "】的回复:" + answer.getAnswerBody());
		accountDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetail.setIsDel(0);
		accountDetail.setUserId(uId);

		AccountDetail accountDetailIntegeral = new AccountDetail();
		accountDetailIntegeral.setDetailMoney(bs);
		accountDetailIntegeral.setOrderCode("");
		// 订单类型 0收入，1消费
		accountDetailIntegeral.setDetailType(0);
		// 0聚财币，1积分
		accountDetailIntegeral.setState(1);
		accountDetailIntegeral.setRemark("【" + user.getNickName() + "】追问讲师【"
				+ teacher.getNickName() + "】的回复:" + answer.getAnswerBody()
				+ ",账户积分+" + bs);
		accountDetailIntegeral.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetailIntegeral.setIsDel(0);
		accountDetailIntegeral.setUserId(uId);

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount detailAccount = new SysDetailAccount();
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailAccount.setIp(ip);
		detailAccount.setIsDel(0);
		detailAccount.setOrderId(0);
		detailAccount.setPrice(bs);
		detailAccount.setRecoderType(2);
		detailAccount.setRemark("【" + user.getNickName() + "】追问讲师【"
				+ teacher.getNickName() + "】的回复:" + answer.getAnswerBody());
		detailAccount.setType(7);
		detailAccount.setUserId(uId);

		Ask ask = new Ask();
		ask.setUserId(uId);
		ask.setTeacherId(teacherId);
		ask.setClassId(type);
		ask.setAskBody(askBody);
		ask.setAskDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		ask.setHits(0);
		ask.setIp(ip);
		ask.setIsPay(1);
		ask.setIsReply(1);
		ask.setParentId(0);
		ask.setAskState(1);
		ask.setAskFrom(2);
		ask.setJucaiBills(bs);
		ask.setIsReturnJcb(0);
		int isSuccess = RollBackUtil.getInstance().payRecycleAsk(ask, detailAccount,
				sysAccount, uId, user, account, accountDetail, aId,
				detailAccount, bs, accountDetailIntegeral);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "追问成功") : JsonUtil
				.getRetMsg(1, "追问失败");
	}

}
