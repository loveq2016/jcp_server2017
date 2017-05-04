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
import com.jucaipen.model.User;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         ��¼
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 7330291568573845129L;
	private String loginUrl = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=login";
	private String result;
	private Map<String, String> param = new HashMap<String, String>();
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
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int id = Integer.parseInt(userId);
					if (id <= 0) {
						result = userLogin(request, os);
					} else {
						result = JsonUtil.getRetMsg(3, "��ǰ�û��Ѿ���¼");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId ������ʽ���쳣");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId ��������Ϊ��");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String userLogin(HttpServletRequest request, ClientOsInfo os) {
		// �����¼
		String  loginType=request.getParameter("loginType");
		if(StringUtil.isNotNull(loginType)&&StringUtil.isInteger(loginType)){
			int type=Integer.parseInt(loginType);
			if(type==0){
				//��ͨ��¼
				return normalLogin(request);
			}else{
				//���ٵ�¼
				return fastLogin(request,loginType);
			}
		}else{
			return normalLogin(request);
		}
	}
	
	
	/**
	 * @param request
	 * @param loginType 
	 * @return   ���ٵ�¼
	 */
	private String fastLogin(HttpServletRequest request, String loginType) {
		String telPhone = request.getParameter("telPhone");
		String activeCode = request.getParameter("activeCode");
		if (!StringUtil.isNotNull(telPhone)) {
			return JsonUtil.getRetMsg(4, "�ֻ��Ų���Ϊ��");
		}
		
		if (!StringUtil.isNotNull(activeCode)) {
			return JsonUtil.getRetMsg(5, "��֤�벻��Ϊ��");
		}
		param.clear();
		param.put("ip", request.getRemoteAddr());
		param.put("loginType", loginType);
		param.put("telPhone", telPhone);
		param.put("activeCode", activeCode);
		String result = LoginUtil.sendHttpPost(loginUrl, param);
		JSONObject object = new JSONObject(result);
		boolean res = object.getBoolean("Result");
		int userId = object.getInt("ActionId");
		if (res) {
			// ��¼�ɹ�����
			String sign = object.getString("Msg");
			User user = UserServer.findBaseInfoById(userId);
			return JsonUtil.getLoginResult(user,sign);
		} else {
			// ��¼ʧ�ܴ���
			return JsonUtil.getRetMsg(1, "��¼ʧ��");
		}
	}
	

	/**
	 * @param request
	 * @param loginType 
	 * @return  ��ͨ��¼
	 */
	private String normalLogin(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if (!StringUtil.isNotNull(userName)) {
			return JsonUtil.getRetMsg(4, "�û�������Ϊ��");
		}
		if (!StringUtil.isUserName(userName)) {
			return JsonUtil.getRetMsg(5, "�û�������Ϊ1-10λ");
		}
		if (!StringUtil.isNotNull(password)) {
			return JsonUtil.getRetMsg(6, "���벻��Ϊ��");
		}
		if (!StringUtil.isPassword(password)) {
			return JsonUtil.getRetMsg(7, "�������Ϊ6-23λ");
		}
		param.clear();
		param.put("username", userName);
		param.put("pwd", password);
		String result = LoginUtil.sendHttpPost(loginUrl, param);
		JSONObject object = new JSONObject(result);
		boolean res = object.getBoolean("Result");
		int userId = object.getInt("ActionId");
		if (res) {
			// ��¼�ɹ�����
			String sign = object.getString("Msg");
			User user = UserServer.findBaseInfoById(userId);
			return JsonUtil.getLoginResult(user,sign);
		} else {
			// ��¼ʧ�ܴ���
			return JsonUtil.getRetMsg(1, "��¼ʧ��");
		}
	}

}
