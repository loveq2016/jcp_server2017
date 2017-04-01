package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.User;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author YLF
 * 
 * 
 *         绑定第三方账号 ---------------解除 userId 绑定账号的用户id accountType 绑定账号类型 ----0
 *         QQ -----1 微信 -----2 新浪 accountId 绑定账号的id
 * 
 * 
 *         opType 1 绑定 2 解除
 * 
 */
public class BindOtherAccount extends HttpServlet {
	private static final long serialVersionUID = 4403585027823706943L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String accountType = request.getParameter("accountType");
		String accountId = request.getParameter("accountId");
		System.out.println("u:" + userId + "    t:" + accountType + "   id:"
				+ accountId);
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(accountType)
						&& StringUtil.isInteger(accountType)) {
					int type = Integer.parseInt(accountType);
					if (type == 0 || type == 1 || type == 2) {
						User u = checkAccountIsBind(type, accountId);
						if (accountId == null || accountId.trim().length() <= 0) {
							// 解除账号
							if (u == null) {
								// 账号已经解除
								result = JsonUtil.getRetMsg(1, "账号已经解除");
							} else {
								int res = delOtherAccount(uId, type);
								result = res == 1 ? JsonUtil.getRetMsg(0,
										"账号解除成功") : JsonUtil.getRetMsg(2,
										"账号解除失败");
							}
						} else {
							// 绑定账号
							if (u != null) {
								result = JsonUtil.getRetMsg(1, "账号已经绑定");
							} else {
								int res = insertOtherAccount(uId, type,
										accountId);
								result = res == 1 ? JsonUtil.getRetMsg(0,
										"账号绑定成功") : JsonUtil.getRetMsg(2,
										"账号绑定失败");
							}
						}
					} else {
						result = JsonUtil.getRetMsg(6, "账号类型参数必须为0或1或2");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "账号类型参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(5, "当前用户还没登录");
			}
		} else {
			result = JsonUtil.getRetMsg(4, "用户id参数数字格式化异常");
		}
		System.out.println("result:" + result);
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * @param type
	 * @param accountId
	 * 
	 *            验证账号是否绑定
	 */
	private User checkAccountIsBind(int type, String accountId) {
		User user;
		if (type == 0) {
			// QQ
			user = UserServer.findUserByQQId(accountId);
		} else if (type == 1) {
			// 微信
			user = UserServer.findUserByWeixinId(accountId);
		} else if (type == 2) {
			// 新浪
			user = UserServer.findUserBySinaId(accountId);
		} else {
			user = new User();
		}
		return user;

	}

	/**
	 * @param uId
	 * @param type
	 * @return 解除第三方账号
	 */
	private int delOtherAccount(int uId, int type) {
		return UserServer.delAccountId(uId, type);
	}

	/**
	 * @param uId
	 * @param type
	 * @param accountId
	 * @return 绑定第三方账号
	 */
	private int insertOtherAccount(int uId, int type, String accountId) {
		return UserServer.updateAccountId(uId, type, accountId);
	}

}
