package com.jucaipen.main.order;

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

import com.jucaipen.main.userid.SunMd5;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取订单号 和 通联支付的 userId
 */
public class GetOrderInfo extends HttpServlet {
	private static final long serialVersionUID = -7055306551181829233L;
	private String result;
	private static final String GET_ORDER_CODE = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=PayOrder";
	private Map<String, String> map = new HashMap<String, String>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String paymoney = request.getParameter("paymoney");
		String type = request.getParameter("type");
		String userid = request.getParameter("userid");
		if (StringUtil.isNotNull(paymoney) && StringUtil.isInteger(paymoney)) {
			int money = Integer.parseInt(paymoney);
			if (money > 0) {
				if (StringUtil.isNotNull(type) && StringUtil.isInteger(type)) {
					int t = Integer.parseInt(type);
					if (StringUtil.isNotNull(userid)
							&& StringUtil.isInteger(userid)) {
						int uId = Integer.parseInt(userid);
						if (uId > 0) {
							result = initOrderInfo(money, t, uId);
						} else {
							result = JsonUtil.getPayUserInfo(2, "用户还没登录", null,
									null);
						}
					} else {
						result = JsonUtil.getPayUserInfo(3, "userid 参数异常",
								null, null);
					}
				} else {
					result = JsonUtil
							.getPayUserInfo(4, "type 参数异常", null, null);
				}
			} else {
				result = JsonUtil.getPayUserInfo(5, "支付金额必须大于0", null, null);
			}
		} else {
			result = JsonUtil.getPayUserInfo(6, "paymoney 参数异常", null, null);
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initOrderInfo(int paymoney, int t, int uId) {
		map.clear();
		map.put("paymoney", paymoney + "");
		map.put("type", t + "");
		map.put("userId", uId + "");
		String res = LoginUtil.sendHttpPost(GET_ORDER_CODE, map);
		JSONObject object = new JSONObject(res);
		boolean Result = object.getBoolean("Result");
		if (Result) {
			// String userId=SunMd5.TextRegister("008310107420099",
			// sdf.format(new Date()), "1234567890");
			String userId = SunMd5.allinpayRegister("008310189990108",
					sdf.format(new Date()), "1234567890");
			String Msg = object.getString("Msg");
			return JsonUtil.getPayUserInfo(0, "获取成功", userId, Msg);
		}
		return JsonUtil.getPayUserInfo(1, "获取异常", null, null);
	}

}
