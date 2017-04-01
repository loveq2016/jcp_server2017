package com.jucaipen.main.order;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.MD5Util;
import com.jucaipen.utils.TimeUtils;

public class OrderManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> param = new HashMap<String, String>();
	private static final String REFUSE_RESULT="https://cashier.allinpay.com/mchtoq/refundQuery";
	/**
	 *   单笔订单查询
	 */
	private static final String QUERRY_ORDER="https://cashier.allinpay.com/gateway/index.do";
	private static final String REFUSE_ORDER="https://cashier.allinpay.com/gateway/index.do";
	private String result;
	private static final String merchantId="008310189990108";

	// 单笔订单查询
	// https://service.allinpay.com/gateway/index.do
	// 单笔订单退款
	// https://service.allinpay.com/gateway/index.do
	// 退款结果查询
	// https://service.allinpay.com/mchtoq/refundQuery
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		
		
		
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 this.doGet(req, resp);
	}

	/**
	 * @param orderNo   订单号
	 * @param orderDate  支付时间
	 * @throws ParseException
	 *  查询订单
	 */
	public static void querryOrder(String orderNo,String orderDate) throws ParseException{
		param.clear();
		 String time = TimeUtils.format(new Date(), "yyyyMMddHHmmss");
		param.put("merchantId", merchantId);
		param.put("version", "v1.5");
		param.put("signType", 0+"");
		param.put("orderNo", orderNo);
		param.put("orderDatetime",orderDate);
		param.put("queryDatetime",time);
		 String md5Str = MD5Util.MD5("merchantId="+merchantId+"&version=v1.5&signType=0&orderNo="
				 +orderNo
		 		+ "&orderDatetime="+orderDate+"&queryDatetime="+time+"&key=1234567890");
		param.put("signMsg", md5Str);
		String result = LoginUtil.sendHttpPost(QUERRY_ORDER, param);
		System.out.println(result);
		
	}

	/**
	 * @param orderNo   订单号
	 * @param orderDate  支付时间
	 * @param amount      退款金额
	 * @throws ParseException
	 * 
	 * 订单退款
	 */
	public static void refuseOrder(String orderNo,String orderDate,int amount) throws ParseException{
		param.clear();
		param.put("merchantId", merchantId);
		param.put("version", "v2.3");
		param.put("signType", 0+"");
		param.put("orderNo", orderNo);
		param.put("refundAmount", amount+"");
		param.put("orderDatetime",orderDate);
		 String md5Str = MD5Util.MD5("merchantId="+merchantId+"&version=v2.3&signType=0&orderNo="
				 +orderNo+"&refundAmount="+amount
		 		+ "&orderDatetime="+orderDate+"&key=1234567890");
		param.put("signMsg", md5Str);
		String result = LoginUtil.sendHttpPost(REFUSE_ORDER, param);
		System.out.println(result);
		
	}
	
	
	
	/**
	 * @param orderNo   订单号
	 * @param orderDate  
	 * @param amount
	 * @throws ParseException
	 * 
	 * 退款结果查询
	 */
	public static void refuseResult(String orderNo,String orderDate,int refundAmount) throws ParseException{
		param.clear();
		param.put("merchantId", merchantId);
		param.put("version", "v2.4");
		param.put("signType", 0+"");
		param.put("orderNo", orderNo);
		param.put("orderDatetime",orderDate);
		param.put("refundAmount", refundAmount+"");
		 String md5Str = MD5Util.MD5("merchantId="+merchantId+"&version=v2.4&signType=0&orderNo="
				 +orderNo
		 		+ "&orderDatetime="+orderDate+"&refundAmount="+refundAmount+"&key=1234567890");
		param.put("signMsg", md5Str);
		String result = LoginUtil.sendHttpPost(REFUSE_RESULT, param);
		System.out.println(result);
		
	}

	public static void main(String[] args) throws ParseException {
		try {
			querryOrder("617031513572623186","20170315135731");
			refuseOrder("617031513572623186", "20170315135731", 200);
			refuseResult("617031513572623186","20170315135731",200);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
