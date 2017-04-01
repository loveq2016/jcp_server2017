package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         付费问
 */
public class PayAsk extends HttpServlet {
	private static final long serialVersionUID = 3749798484035649579L;
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
		String askBody = request.getParameter("askBody");
		String teacherId = request.getParameter("teacherId");
		String bills = request.getParameter("bills");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(teacherId)
						&& StringUtil.isInteger(teacherId)) {
					int tId = Integer.parseInt(teacherId);
					if (StringUtil.isNotNull(bills)
							&& StringUtil.isInteger(bills)) {
						int bs = Integer.parseInt(bills);
						if (StringUtil.isNotNull(typeId)
								&& StringUtil.isInteger(typeId)) {
							int type = Integer.parseInt(typeId);
							if (StringUtil.isNotNull(askBody)) {
								result = payAskTeacher(tId, uId, bs, type,
										askBody);
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
					result = JsonUtil.getRetMsg(3, "teacherId 参数异常");
				}

			} else {
				result = JsonUtil.getRetMsg(2, "用户还没登录");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String payAskTeacher(int tId, int uId, int bs, int type,
			String askBody) {
		Account account = AccountSer.findAccountByUserId(uId);
		User user = UserServer.findBaseInfoById(uId);
		FamousTeacher teacher = null;
		if (tId > 0) {
			teacher = FamousTeacherSer.findFamousTeacherById(tId);
		}
		if (account == null || account.getJucaiBills() < bs) {
			return JsonUtil.getRetMsg(1, "余额不足，请进行充值");
		}

		Ask ask = new Ask();
		ask.setUserId(uId);
		ask.setTeacherId(tId);
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

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(bs);
		accountDetail.setDetailType(1);
		accountDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetail.setIsDel(0);
		accountDetail.setOrderCode("");
		if (tId <= 0) {
			accountDetail.setRemark("【" + user.getNickName() + "】公开向讲师付费提问："
					+ askBody + ",账户余额-" + bs);
		} else {
			accountDetail.setRemark("【" + user.getNickName() + "】向讲师【"
					+ teacher.getNickName() + "】付费提问：" + askBody + ",账户余额-"
					+ bs);
		}
		accountDetail.setState(0);
		accountDetail.setUserId(uId);

		AccountDetail detailInteger = new AccountDetail();
		detailInteger.setDetailMoney(bs);
		detailInteger.setDetailType(0);
		detailInteger.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailInteger.setIsDel(0);
		detailInteger.setOrderCode("");
		if (tId <= 0) {
			detailInteger.setRemark("【" + user.getNickName() + "】公开向讲师付费提问："
					+ askBody + ",账户积分+" + bs);
		} else {
			detailInteger.setRemark("【" + user.getNickName() + "】向讲师【"
					+ teacher.getNickName() + "】付费提问：" + askBody + ",账户积分+"
					+ bs);
		}
		detailInteger.setState(1);
		detailInteger.setUserId(uId);
		
		
		SysDetailAccount sysDetailAccount = new SysDetailAccount();
		sysDetailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysDetailAccount.setIp(ip);
		sysDetailAccount.setIsDel(0);
		sysDetailAccount.setOrderId(0);
		sysDetailAccount.setPrice(bs);
		sysDetailAccount.setRecoderType(2);
		sysDetailAccount.setType(7);
		sysDetailAccount.setUserId(uId);
		sysDetailAccount.setRemark("【" + user.getNickName() + "】向讲师付费提问："
				+ askBody);
		int isSuccess = RollBackUtil.getInstance().payAsk(ask, account, uId, user, bs,
				sysAccount,accountDetail,detailInteger,sysDetailAccount);

		return isSuccess==1 ? JsonUtil.getRetMsg(0, "提问成功") : JsonUtil.getRetMsg(1,"提问失败");
	}

}
