package com.jucaipen.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.jucaipen.main.payinfo.Constants;
import com.jucaipen.utils.LoginUtil;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String wxUrl="http://192.168.1.134:8080/AccumulateWealth/test";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		InputStream inputStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inputStream.close();
		out.flush();
		out.close();
		String content=new String(buffer,"UTF-8");
		System.out.println("cintent==="+content);
	}
	
	
	public static void main(String[] args) {
		String sendPostStr = LoginUtil.sendPostStr(wxUrl, genProductArgs("11","11","111"),null);
		System.out.println("str="+sendPostStr);
	}

	
	/**
	 * @param ip 
	 * @param bills 
	 * @param order 
	 * @return  微信获取订单参数
	 */
	private static String genProductArgs(String order, String bills, String ip) {
		StringBuffer xml = new StringBuffer();
		try {
			xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
			String string = new String("聚财币".getBytes(),"UTF-8");
			packageParams.add(new BasicNameValuePair("body", string));
			packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
			packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.227.121:8080/AccumulateWealth/jucaipen/rechargeResult"));
			packageParams.add(new BasicNameValuePair("out_trade_no",order));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",ip));
			packageParams.add(new BasicNameValuePair("total_fee", bills));
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));
		    String xmlstring =toXml(packageParams);
			return new String(xmlstring.getBytes(),"UTF-8");
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * @param params
	 * @return  转换XML
	 */
	private static String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<"+params.get(i).getName()+">");
			sb.append(params.get(i).getValue());
			sb.append("</"+params.get(i).getName()+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
