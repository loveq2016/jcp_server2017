package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.LoginLog;
import com.jucaipen.model.User;
import com.jucaipen.service.LoginLogServer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author YLF
 * 
 *         第三方账号登录 accountType ---第三方账号类型 accountId ---第三方账号id
 * 
 */
public class OtherAccountLogin extends HttpServlet {
	private static final long serialVersionUID = 2932971607795771388L;
	private User user;
	private String result;
	private int uId;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String clientAddress;
	private Map<String, String> param=new HashMap<String, String>();
	/**
	 *   获取userSign
	 */
	private static final String GET_SIGN="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";


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
			String accountType = request.getParameter("accountType");
			String accountId = request.getParameter("accountId");
			clientAddress = request.getRemoteAddr();
			if (StringUtil.isInteger(accountType)) {
				int type = Integer.parseInt(accountType);
				if (StringUtil.isNotNull(accountId)) {
					loginUser(type, accountId);
					if (user != null) {
						param.clear();
						param.put("userid", user.getId()+"");
						String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
						JSONObject object=new JSONObject(signResult);
						boolean isCreate=object.optBoolean("Result");
						if(isCreate){
							String sign = object.optString("UserSig");
							result = JsonUtil.getLoginResult(user,sign);
							uId = user.getId();
							handleLoginResult(1, user);
						}else{
							result = JsonUtil.getRetMsg(5,"登录失败");
						}
					} else {
						// 未绑定的第三方登录
						result = JsonUtil.getRetMsg(3, "该账户未绑定");
					}
				} else {
					result = JsonUtil.getRetMsg(1, "账号id不能为空");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "账号类型参数数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private void handleLoginResult(int i, User user) {
		// 处理登录结果
		LoginLog log = new LoginLog();
		log.setLoginDate(sdf.format(new Date()));
		log.setUserId(uId);
		log.setAccount(user.getUserName());
		log.setPassword(user.getPassword());
		log.setRemark("登录成功");
		log.setLoginIp(clientAddress);
		LoginLogServer.insertLog(log);

	}

	private void loginUser(int type, String accountId) {
		// 利用第三方账号登录程序
		user = UserServer.otherAccountLogin(type, accountId);
	}

}
