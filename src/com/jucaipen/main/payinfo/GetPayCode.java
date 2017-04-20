package com.jucaipen.main.payinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

public class GetPayCode extends HttpServlet {
	private static final long serialVersionUID = 4762261617238874031L;
	private String result;
	//微信、支付宝商户号
	private String APPID="wx15b402e12163f9a7";
	private static String SERVER = "https://yintong.com.cn/offline_api/";
	// 商户号
	private static String TRADERNO = "201504231000296504";
	//MD5_KEY
	private static String MD5_KEY = "201408291000007508";
	//prikeyvalue
	private static String prikeyvalue = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMlGNh/WsyZSYnQcHd9t5qUkhcOhuQmozrAY9DM4+7fhpbJenmYee4chREW4RB3m95+vsz9DqCq61/dIOoLK940/XmhKkuVjfPqHJpoyHJsHcMYy2bXCd2fI++rERdXtYm0Yj2lFbq1aEAckciutyVZcAIHQoZsFwF8l6oS6DmZRAgMBAAECgYAApq1+JN+nfBS9c2nVUzGvzxJvs5I5qcYhY7NGhySpT52NmijBA9A6e60Q3Ku7vQeICLV3uuxMVxZjwmQOEEIEvXqauyYUYTPgqGGcwYXQFVI7raHa0fNMfVWLMHgtTScoKVXRoU3re6HaXB2z5nUR//NE2OLdGCv0ApaJWEJMwQJBAPWoD/Cm/2LpZdfh7oXkCH+JQ9LoSWGpBDEKkTTzIqU9USNHOKjth9vWagsR55aAn2ImG+EPS+wa9xFTVDk/+WUCQQDRv8B/lYZD43KPi8AJuQxUzibDhpzqUrAcu5Xr3KMvcM4Us7QVzXqP7sFc7FJjZSTWgn3mQqJg1X0pqpdkQSB9AkBFs2jKbGe8BeM6rMVDwh7TKPxQhE4F4rHoxEnND0t+PPafnt6pt7O7oYu3Fl5yao5Oh+eTJQbyt/fwN4eHMuqtAkBx/ob+UCNyjhDbFxa9sgaTqJ7EsUpix6HTW9f1IirGQ8ac1bXQC6bKxvXsLLvyLSxCMRV/qUNa4Wxu0roI0KR5AkAZqsY48Uf/XsacJqRgIvwODstC03fgbml890R0LIdhnwAvE4sGnC9LKySRKmEMo8PuDhI0dTzaV0AbvXnsfDfp";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String bills=request.getParameter("bills");
		if(StringUtil.isNotNull(bills)&&StringUtil.isInteger(bills)){
			int b=Integer.parseInt(bills);
			DecimalFormat df=new DecimalFormat(".00");
			result = orderInit(df.format(b));
		}else{
			result=JsonUtil.getRetMsg(1,"bills 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	

	public String orderInit(String bills) {
		HttpRequestSimple http = HttpRequestSimple.getInstance();
		TestUtil testUtil = new TestUtil();
		OrderParamBean reqBean = new OrderParamBean();
		// 商家订单号
		reqBean.setNo_order(DateUtil.getCurrentDateTimeStr1());
		// 商户号
		reqBean.setOid_partner(TRADERNO);
		// 订单金额
		reqBean.setMoney_order(bills);
		// 商家订单时间
		reqBean.setDt_order(DateUtil.getCurrentDateTimeStr1());
		// 支付结果回调url
		reqBean.setNotify_url("https://www.baidu.com");
		reqBean.setName_goods("聚财币");
		reqBean.setAppid(APPID);
		// 支付方式       15-微信支付
		reqBean.setPay_type("15");
		// 风险控制
		reqBean.setRisk_item("pass");
		// 请求签名方式
		reqBean.setSign_type("MD5");
		String sign = testUtil.addSign(
				JSON.parseObject(JSONObject.toJSONString(reqBean)),
				prikeyvalue, MD5_KEY);
		// 签名串
		reqBean.setSign(sign);

		String url = SERVER + "createOrder_init.htm";
		String resStr = JSON.toJSONString(reqBean);
		String resStr1 = http.postSendHttp(url, resStr);
		org.json.JSONObject obj=new org.json.JSONObject(resStr1);
		String dimension_url=obj.optString("dimension_url");
		return dimension_url;

	}

}
