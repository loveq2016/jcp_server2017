package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.MobileMessage;
import com.jucaipen.service.MobileMessageSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.MD5Util;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         修改密码
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = -2665589395926091613L;
	private String result;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String encrypePath = "http://user.jucaipen.com/ashx/AndroidUser.ashx?action=GetEncryptMobileNum";
	private Map<String, String> map = new HashMap<String, String>();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String userId = request.getParameter("userId");
			String oldPwd = request.getParameter("oldPwd");
			String telPhone = request.getParameter("telPhone");
			String actionCode = request.getParameter("actionCode");
			String newPwd = request.getParameter("newPwd");
			String reptPwd = request.getParameter("reptPwd");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						result = changePassword(uId, oldPwd, telPhone,
								actionCode, newPwd, reptPwd);
					} else {
						result = JsonUtil.getRetMsg(3, "当前用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String changePassword(int uId, String oldPwd, String telPhone,
			String actionCode, String newPwd, String reptPwd) {
		// 修改密码
		// 1 、判断参数合法性 2、验证旧密码正确性 3、验证手机验证码是否正确 4、加密密码并修改密码
		// 解密手机号
		map.put("mobilenum", telPhone);
		String resJson = LoginUtil.sendHttpPost(encrypePath, map);
		org.json.JSONObject object = new org.json.JSONObject(resJson);
		boolean isRes = object.getBoolean("Result");
		if (isRes) {
			String tel = object.getString("MobileNum");
			int isPassed = checkMobileCode(tel, actionCode);
			if (isPassed == 1) {
				return JsonUtil.getRetMsg(5, "手机验证码超时");
			} else if (isPassed == 2) {
				return JsonUtil.getRetMsg(6, "手机验证码错误");
			} else {
				if (StringUtil.isNotNull(telPhone)
						&& StringUtil.isMobileNumber(telPhone)) {
					if (StringUtil.isNotNull(actionCode)) {
						if (StringUtil.isNotNull(oldPwd)
								&& StringUtil.isNotNull(newPwd)
								&& StringUtil.isNotNull(reptPwd)) {
							String oldMd5Pwd = MD5Util.MD5(oldPwd);
							if (newPwd.equals(reptPwd)) {
								String password = UserServer
										.findPasswordById(uId);
								if (oldMd5Pwd.equals(password)) {
									// 用户密码验证通过
									String newMd5Pwd = MD5Util.MD5(newPwd);
									int isSuccess = UserServer.updatePassword(
											uId, newMd5Pwd);
									return isSuccess == 1 ? JsonUtil.getRetMsg(
											0, "密码修改成功") : JsonUtil.getRetMsg(
											1, "密码修改失败");
								} else {
									return JsonUtil.getRetMsg(6, "原始密码错误");
								}
							} else {
								return JsonUtil.getRetMsg(5, "两次密码不一致");
							}
						} else {
							return JsonUtil.getRetMsg(4, "密码不能为空");
						}
					} else {
						return JsonUtil.getRetMsg(3, "验证码不能为空");
					}
				} else {
					return JsonUtil.getRetMsg(2, "手机号不合法");
				}
			}
		} else {
			String msg = object.getString("Msg");
			return JsonUtil.getRetMsg(1, msg);
		}
	}

	private int checkMobileCode(String mobile, String actionCode) {
		try {
			// 验证手机验证码是否合法
			MobileMessage mobileList = MobileMessageSer.findIsRegin(mobile,
					actionCode);
			if (mobileList != null) {
				String checkCode = mobileList.getActionCode();
				String sendDate = mobileList.getSendDate();
				String msgId = mobileList.getMsgid();
				long sendTime = sdf.parse(sendDate).getTime();
				long currrentTime = System.currentTimeMillis();
				if ((actionCode.equals(checkCode))) {
					if ((currrentTime - sendTime) <= (3 * 60 * 1000)) {
						insertCheckInfo(mobile, sdf.format(new Date()), msgId,
								true);
						return 0;
					} else {
						return 1;
					}
				} else {
					return 2;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 2;
	}

	private void insertCheckInfo(String mobileNum, String checkDate,
			String msgId, boolean isPassed) {
		// 修改短信激活状态
		MobileMessage mobileMessage = new MobileMessage();
		if (isPassed) {
			mobileMessage.setMsgType(2);
			mobileMessage.setCheckDate(checkDate);
		} else {
			mobileMessage.setMsgType(3);
		}
		MobileMessageSer.upDateMessageType(msgId, mobileMessage);

	}
}
