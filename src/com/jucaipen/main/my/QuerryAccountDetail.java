package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.service.AccountDetailSer;
import com.jucaipen.service.AccountSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取账单详细信息  state 0 聚财币 1 积分 
 */
public class QuerryAccountDetail extends HttpServlet {
	private static final long serialVersionUID = -5370364459925577430L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			String userId = request.getParameter("userId");
			String page = request.getParameter("page");
			String state = request.getParameter("state");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
								if (StringUtil.isNotNull(state)
										&& StringUtil.isInteger(state)) {
									int s = Integer.parseInt(state);
									result = initAccountData(uId, p, s);
								} else {
									result = JsonUtil.getRetMsg(1, "state 参数异常");
								}
						} else {
							result = JsonUtil.getRetMsg(1, "page 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(1, "该用户还没有登录");
					}
				} else {
					result = JsonUtil.getRetMsg(1, "userId 数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}
		}else{
			result=StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initAccountData(int userId,  int page, int state) {
		// 初始化账户信息
		List<AccountDetail> details = AccountDetailSer
				.findDetailByUserIdAndType(userId, state, page);
		Account account=AccountSer.findAccountByUserId(userId);
		return JsonUtil.getAccountDetail(details,account);
	}

}
