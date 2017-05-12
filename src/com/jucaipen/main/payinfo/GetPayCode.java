package com.jucaipen.main.payinfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.tools.sysinfo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.jucaipen.model.ChargeOrder;
import com.jucaipen.service.ChargeOrderSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.MD5Util;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

public class GetPayCode extends HttpServlet {
	private static String serverUrl = "https://openapi.alipay.com/gateway.do";

	private static String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private static final long serialVersionUID = 4762261617238874031L;
	private String result;
	/*// 微信商户号
	private String APPID = "1273861101";
	*//**
	 *   微信appId
	 *//*
	private String wappId="wx4e03910ab5dec31d";
	*//**
	 *   微信商户号
	 *//*
	private String mchId="1273861101";
	private static String SERVER = "https://yintong.com.cn/offline_api/";
	// 银通商户号
	private static String TRADERNO = "201704171001650515";*/
	/*private StringBuffer sb;
	// MD5_KEY
	private static String MD5_KEY = "201704171001650515";
	// prikeyvalue
	private static String prikeyvalue = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMlGNh/WsyZSYnQcHd9t5qUkhcOhuQmozrAY9DM4+7fhpbJenmYee4chREW4RB3m95+vsz9DqCq61/dIOoLK940/XmhKkuVjfPqHJpoyHJsHcMYy2bXCd2fI++rERdXtYm0Yj2lFbq1aEAckciutyVZcAIHQoZsFwF8l6oS6DmZRAgMBAAECgYAApq1+JN+nfBS9c2nVUzGvzxJvs5I5qcYhY7NGhySpT52NmijBA9A6e60Q3Ku7vQeICLV3uuxMVxZjwmQOEEIEvXqauyYUYTPgqGGcwYXQFVI7raHa0fNMfVWLMHgtTScoKVXRoU3re6HaXB2z5nUR//NE2OLdGCv0ApaJWEJMwQJBAPWoD/Cm/2LpZdfh7oXkCH+JQ9LoSWGpBDEKkTTzIqU9USNHOKjth9vWagsR55aAn2ImG+EPS+wa9xFTVDk/+WUCQQDRv8B/lYZD43KPi8AJuQxUzibDhpzqUrAcu5Xr3KMvcM4Us7QVzXqP7sFc7FJjZSTWgn3mQqJg1X0pqpdkQSB9AkBFs2jKbGe8BeM6rMVDwh7TKPxQhE4F4rHoxEnND0t+PPafnt6pt7O7oYu3Fl5yao5Oh+eTJQbyt/fwN4eHMuqtAkBx/ob+UCNyjhDbFxa9sgaTqJ7EsUpix6HTW9f1IirGQ8ac1bXQC6bKxvXsLLvyLSxCMRV/qUNa4Wxu0roI0KR5AkAZqsY48Uf/XsacJqRgIvwODstC03fgbml890R0LIdhnwAvE4sGnC9LKySRKmEMo8PuDhI0dTzaV0AbvXnsfDfp";*/

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String code = request.getParameter("orderCode");
		System.out.println("type=="+type);
		if (StringUtil.isNotNull(type) && StringUtil.isInteger(type)) {
			int t = Integer.parseInt(type);
			if (t == 1) {
				// 支付宝
				
			} else {
				// 微信
					if(StringUtil.isNotNull(code)){
						ChargeOrder order = ChargeOrderSer.findOrderByOrderCode(code);
						if(order!=null){
							result = orderInit(order.getChargeMoney()+"",request.getRemoteAddr(),code);
						}else{
							result=JsonUtil.getRetMsg(9,"订单不存在");
						}
					}else{
						result=JsonUtil.getRetMsg(8,"订单号不能为空");
					}
			}
		} else {
			result = JsonUtil.getRetMsg(2, "type 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	public String getPayInfo(String money) {
		try {
			AlipayClient client = new DefaultAlipayClient(
					serverUrl,
					"2016010501066384",
					"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7JT1PiVhKbR8Zd42tepPx5iVezsZNyMa/JQHuly0SCJY/yjxhmFZxse0oUO+UQqpx1X7BsZHAajc8osAesdX+CpAvFccnt4E8W0llTRyXU/WbT6rMvN9U0so2+DILqvxOr0KVwvLk01H00+TA/XBsP9tuR/Eld+5WP4t63I5lM74tYIlzGw0P2VgN+KZ5tjWkA60ZoAo0bxnNYpWXrzWn7mv+e389LQ+L1enEsHnEytJ4uTrWhUH0Bn9z9qkqiDR2IoUgMQPhK49XK/Y5LqwCHiA92pK0TRnAScLIAMP9TLs8uzrvgPlfDCmYkTiR7/2PNXkLGo1z3pS2M6+ITtvTAgMBAAECggEBALRoBL+MriEpKma5ekl2tfHmI7ZObJoFZ5pSCZfAAkG1J2odAbIvUX3OwMkxpJLu5TJRVh1QmSm8+ryoQ74TPy/jbPUG8HKYEqxlb86I1HwJAuYSmZs+o6g4f5kYD95xmUIc73pBcTcng4UZUxa7+klQEa8xCQVOpOJd5Y6t2VMyNHoJe/XRV3qHTr+Y1g6+Jy0OzSLOaBxwr45BA97Is9Jw1KSOBt2GivMvWqeRQCYcLTVunPvWXvo3swsHUaX3STvRc7MBov3vR+VwYqApPfPhwQQPAgCrR/Wi8X7aTvzO+i/rRXlbrt/IEOY5Sbs9Wu569/lAv+MQO/kaqKtOdmECgYEA59EuPoB/erlTP/sBeZ8sxq0tz4TckiQOQBJ6h/lbhnFcXxtMqp2HOd2R7X11SXed0tuPtdWR7YoyUXsbfqZVhzfbbME/yxAZP8GPgj1n8HCXzOp6sd6g0q/l1iMbaIxXcRCWdcnbvmd8sMIegsI0RlGvWuUe/tHDe+htkwtRoMMCgYEAzqsTL0t9TRqsz5DpKARj5SKMtUCHuqcjR1wwr80DUQeKArgZJFi67p1MVFOp2EOQF2vwGoIppRrMPpdEit3DD4TD8U158KeJTDKvl0VmSsT5utyhp+djO1RXK//pPmpfpkPd6tuC8MJHjnd3RvCJ7UWUc8ji9+lrHBKOS18LJ7ECgYEAuExmrowsTV5fYENnRdEVoRJ3lD81uP3vUmMehVHqwPUJEhe7GP3KT2fnJ1+RyonNsKCcHV0g+i/fl0X/cu48mxb+XPU6iW9QE48IT+wP4WkyVliomRa81yO+30Lj/Hdu4VXluNl12WefC0wZQ62wL6OrfOEhhkcwWidMThY2nE8CgYBDz957pkl3A0mw0duvRcGBbdS9pG7kz/LDd3yCMsLj05V1TvLw+ixcH0DsaodQbtrY1JH0IMGJa0GZSAk1h455iRbCFqnsTQy/8+GpwRvPbNKEfeCNN19YGFSCG3ZtBHpeE+a9KUnNtfHEzW5V2JdpQ2tyknfm4LBEhdIq6QTAAQKBgERS0VNpmOyb1AzBW2ReAe8m26UFQ8tkpfC+6C/NX5hdTdm/Y12KamPk8O9QVy3J7Y12XBMfQ/8wI2X04HxDU58gOjWoVesKhkGB43fo5qVkJ5XP0pnClPMLorE3cXSK3x/P3c3rZ8k7wzfK5rr8HUsYIKSijeuM4def6os8/oyI",
					"json",
					"UTF-8",
					"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuyU9T4lYSm0fGXeNrXqT8eYlXs7GTcjGvyUB7pctEgiWP8o8YZhWcbHtKFDvlEKqcdV+wbGRwGo3PKLAHrHV/gqQLxXHJ7eBPFtJZU0cl1P1m0+qzLzfVNLKNvgyC6r8Tq9ClcLy5NNR9NPkwP1wbD/bbkfxJXfuVj+LetyOZTO+LWCJcxsND9lYDfimebY1pAOtGaAKNG8ZzWKVl681p+5r/nt/PS0Pi9XpxLB5xMrSeLk61oVB9AZ/c/apKog0diKFIDED4SuPVyv2OS6sAh4gPdqStE0ZwEnCyADD/Uy7PLs674D5XwwpmJE4ke/9jzV5CxqNc96UtjOviE7b0wIDAQAB",
					"RSA2");
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setSubject("购买聚财币");
			model.setOutTradeNo(TimeUtils.format(new Date(), "yyyyMMddHHmmss"));
			model.setTimeoutExpress("30m");
			model.setTotalAmount(money);
			model.setProductCode("QUICK_MSECURITY_PAY");
			model.setSellerId("pay@jucaipen.com");
			request.setBizModel(model);
			request.setNotifyUrl("http://");
			request.setApiVersion("1.0");
			AlipayTradeAppPayResponse response = client.sdkExecute(request);
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param bills
	 * @return 微信订单      //ULquRXtJG6tyiUU23H8bSbdJJE9z6yFv  
	 */
	public String orderInit(String bills,String ip,String order) {
		String sendPostStr = LoginUtil.sendPostStr(wxUrl,genProductArgs(order,bills,ip),null);
		return sendPostStr;
	}
	/**
	 * @return  获取NonceStr
	 */
	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
	
	/**
	 * @param ip 
	 * @param bills 
	 * @param order 
	 * @return  微信获取订单参数
	 */
	private String genProductArgs(String order, String bills, String ip) {
		try {
			String	nonceStr = genNonceStr();
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
			String msg="梧桐直播";
			String string = new String(msg.getBytes("UTF-8"));
			packageParams.add(new BasicNameValuePair("body", string));
			packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
			packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
			packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.227.121:8080/AccumulateWealth/jucaipen/rechargeResult"));
			packageParams.add(new BasicNameValuePair("out_trade_no",order));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",ip));
			packageParams.add(new BasicNameValuePair("total_fee", Integer.parseInt(bills)*100+""));
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));
			String sign = genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));
		    String xmlstring =toXml(packageParams);
			return xmlstring;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	/**
	 * @param params
	 * @return  转换XML
	 * @throws UnsupportedEncodingException 
	 */
	private String toXml(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		//sb.append("<xml version='1.0' encoding='utf-8'>");
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<"+params.get(i).getName()+">");
			sb.append(params.get(i).getValue());
			sb.append("</"+params.get(i).getName()+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	
	/**
	  *   生成签名
	 * @throws UnsupportedEncodingException 
	 */
	private String genPackageSign(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);
		String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		return packageSign;
	}
	
	
	/*public String payLL(String bills) {
		HttpRequestSimple http = HttpRequestSimple.getInstance();
		TestUtil testUtil = new TestUtil();
		OrderParamBean reqBean = new OrderParamBean();
		//订单号
		reqBean.setNo_order(DateUtil.getCurrentDateTimeStr1());
		// 银通商户号
		reqBean.setOid_partner(TRADERNO);
		//支付金额
		reqBean.setMoney_order(bills);
		// 商家订单时间
		reqBean.setDt_order(DateUtil.getCurrentDateTimeStr1());
		//回调url
		reqBean.setNotify_url("https://www.baidu.com");
		//商品名称
		reqBean.setName_goods("b");
		//微信商户号
		reqBean.setAppid(APPID);
		// 支付类型
		reqBean.setPay_type("15");
		// 支付安全
		reqBean.setRisk_item("pass");
		// 加密方式
		reqBean.setSign_type("MD5");
		String sign = testUtil.addSign(
				JSON.parseObject(JSONObject.toJSONString(reqBean)),
				prikeyvalue, MD5_KEY);
		// 秘钥
		reqBean.setSign(sign);

		String url = SERVER + "createOrder_init.htm";
		String resStr = JSON.toJSONString(reqBean);
		String resStr1 = http.postSendHttp(url, resStr);
		org.json.JSONObject obj=new org.json.JSONObject(resStr1);
		String dimension_url=obj.optString("dimension_url");
		return dimension_url;
	}*/

}
