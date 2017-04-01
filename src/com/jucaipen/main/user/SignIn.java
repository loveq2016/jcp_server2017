package com.jucaipen.main.user;

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
import com.jucaipen.model.RebateIntegeralDetail;
import com.jucaipen.model.Sign;
import com.jucaipen.model.SignDetail;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.SignDetailSer;
import com.jucaipen.service.SignSer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         签到
 */
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 4989304771882627987L;
	private String result;
	private String ip;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		ip = request.getRemoteAddr();
		if (StringUtil.isNotNull(userId)) {
			if (StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					result = initSignInData(uId);
				} else {
					result = JsonUtil.getRetMsg(3, "用户还没登录");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
		}

		out.println(result);
		out.flush();
		out.close();
	}

	private String initSignInData(int uId) {
		// 初始化签到信息
		List<SignDetail> dets = SignDetailSer.findMothSignDetailByUserId(uId);
		for (SignDetail detail : dets) {
			String insertDate = detail.getInsertDate();
			if (TimeUtils.isToday(insertDate)) {
				return JsonUtil.getRetMsg(1, "您今日已经签到成功");
			}
		}
		User user = UserServer.findBaseInfoById(uId);
		if(user==null){
			return "";
		}
		String insertDate = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 更新签到详细表
		SignDetail detail = new SignDetail();
		detail.setInsertDate(insertDate);
		detail.setIp(ip);
		detail.setUserId(uId);
		detail.setRemark("签到成功");

		int signNum = SignSer.findSignCount(uId);
		Sign sign = new Sign();
		sign.setLastDate(insertDate);
		sign.setIp(ip);
		sign.setSignNum(signNum + 1);
		sign.setUserId(uId);

		SiteConfig config = SiteConfigSer.findSiteConfig();
		int signIntegeral = config.getSignIntegeral();
		// 更新返利表
		RebateIntegeralDetail inDetail = new RebateIntegeralDetail();
		inDetail.setFromId(uId);
		inDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		inDetail.setIntegralNum(signIntegeral);
		inDetail.setType(4);
		inDetail.setUserId(uId);
		inDetail.setRemark("用户签到【" + user.getUserName() + "】");

		// 更新账户详细积分信息表
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(signIntegeral);
		accountDetail.setDetailType(0);
		accountDetail.setInsertDate(insertDate);
		accountDetail.setIsDel(0);
		accountDetail.setOrderCode("");
		accountDetail.setUserId(uId);
		accountDetail.setState(1);
		accountDetail.setRemark("签到送" + signIntegeral + "积分");

		// 更新总账户积分信息
		Account a = AccountSer.findAccountByUserId(uId);

		int isSuccess = RollBackUtil.getInstance().signIn(detail, sign, inDetail,
				accountDetail, a, uId, signIntegeral, user);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "签到成功") : JsonUtil
				.getRetMsg(1, "签到失败");
	}

}
