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
import com.jucaipen.model.Answer;
import com.jucaipen.model.Ask;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
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
 *         采纳回答
 */
public class CatchAnswer extends HttpServlet {
	private static final long serialVersionUID = 3472044508946288560L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String answerId = request.getParameter("answerId");
		String grade = request.getParameter("grade");
		String ip = request.getRemoteAddr();
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(answerId)
						&& StringUtil.isInteger(answerId)) {
					int aId = Integer.parseInt(answerId);
					if (StringUtil.isNotNull(grade)
							&& StringUtil.isInteger(grade)) {
						int g = Integer.parseInt(grade);
						result = catchAnswer(aId, uId, g, ip);
					} else {
						result = JsonUtil.getRetMsg(4, "grade 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "answerId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "还有还没登录");
			}

		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String catchAnswer(int aId, int uId, int g, String ip) {
		Answer answer = AnswerSer.findAnswerById(aId);
		int askId = answer.getAskId();
		User user = UserServer.findBaseInfoById(uId);
		Ask ask = AskSer.findAskById(askId);
		int bills = ask.getJucaiBills();
		int teacherId = ask.getTeacherId();
		Account account = AccountSer.findAccountByUserId(uId);
		FamousTeacher teacher = FamousTeacherSer
				.findFamousTeacherById(teacherId);

		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(bills);
		accountDetail.setDetailType(0);
		accountDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetail.setIsDel(0);
		accountDetail.setOrderCode("");
		accountDetail.setRemark("采纳【" + teacher.getNickName() + "】的回答:"
				+ answer.getAnswerBody());
		accountDetail.setState(1);
		accountDetail.setUserId(uId);

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		Contribute contribute = new Contribute();
		contribute.setAllJucaiBills(bills);
		contribute.setComType(4);
		contribute.setFk_id(aId);
		contribute.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		contribute.setTeacherId(teacherId);
		contribute.setUserId(uId);

		Rebate rebate = new Rebate();
		rebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		rebate.setType(0);
		rebate.setRebateMoney(bills * teacher.getReturnRate());
		rebate.setFromId(uId);
		rebate.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		rebate.setRemark("用户采纳讲师回答返利");

		Rebate sysRebate = new Rebate();
		sysRebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		sysRebate.setType(1);
		sysRebate.setRebateMoney(bills * (1 - teacher.getReturnRate()));
		sysRebate.setFromId(uId);
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysRebate.setRemark("用户采纳讲师回答返利");

		int isSuccess = RollBackUtil.getInstance().catchAnswers(account, accountDetail,
				rebate, sysAccount, sysRebate, uId, user, aId, g, bills,
				contribute);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "采纳回答成功") : JsonUtil
				.getRetMsg(1, "采纳失败");
	}

}
