package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         注册
 */
public class Regin extends HttpServlet {
	private static final long serialVersionUID = -1703673261889402301L;
	private String reginUrl = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=new_reg";
	private String result;
	private String ip;
	private Map<String, String> param = new HashMap<String, String>();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		ip = request.getRemoteAddr();
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String telPhone = request.getParameter("telPhone");
		//	String checkCode = request.getParameter("checkCode");
		//	String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String reptPwd = request.getParameter("reptPwd");
			//String investCode = request.getParameter("investCode");
			//if (StringUtil.isMobileNumber(telPhone)) {
				/*if (StringUtil.isNotNull(checkCode) && checkCode.length() >= 4) {
					if (StringUtil.isNotNull(userName)) {
						if (StringUtil.isUserName(userName)) {*/
							if (StringUtil.isPassword(password)) {
								if (StringUtil.isPassword(reptPwd)&&password.equals(reptPwd)) {
									result = regin(telPhone, password);
								} else {
									result = JsonUtil.getRetMsg(6, "两次密码不一致");
								}
							} else {
								result = JsonUtil.getRetMsg(5, "密码长度在8-23位");
							}

					/*	} else {
							result = JsonUtil.getRetMsg(4, "用户名长度为2-9位");
						}

					} else {
						result = JsonUtil.getRetMsg(3, "用户名不能为空");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "验证码长度不足");
				}*/

			/*} else {
				result = JsonUtil.getRetMsg(1, "手机号不符合要求");
			}*/
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @param userName
	 * @param password
	 * @param telPhone
	 * @param checkCode
	 * @param investCode
	 * @return  注册
	 */
	private String regin(String telPhone, String password) {
		param.put("mobilenum", telPhone);
		param.put("pwd", password);
		param.put("pwd_sure", password);
		param.put("ip", ip);
		String result = LoginUtil.sendHttpPost(reginUrl, param);
		JSONObject object = new JSONObject(result);
		boolean res = object.getBoolean("Result");
		String msg = object.getString("Msg");
		if (res) {
			return JsonUtil.getRetMsg(0, msg);
		} else {
			return JsonUtil.getRetMsg(1, msg);
		}
	}
}
