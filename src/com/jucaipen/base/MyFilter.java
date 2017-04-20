package com.jucaipen.base;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.bitwalker.useragentutils.UserAgent;

import com.alibaba.fastjson.JSONObject;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.TimeUtils;
public class MyFilter implements Filter {
	private String getAddress = "http://ip.taobao.com/service/getIpInfo.php?ip=";
	@Override
	public void destroy() {
		// ����
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// ������������---����
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String ua = req.getHeader("User-Agent");
		new PrintLog(ua, req, resp).start();
		String localAddr = req.getLocalAddr();
		if (localAddr.equals("121.40.227.121")) {
			if((ua.contains("iPhone") || ua.contains("Android"))){
				chain.doFilter(req, resp);
			}
		}else if(localAddr.equals("192.168.1.134")){
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// ��ʼ��

	}

	public class PrintLog extends Thread {
		private String ua;
		private HttpServletRequest req;

		public PrintLog(String ua, HttpServletRequest req,
				HttpServletResponse resp) {
			this.ua = ua;
			this.req = req;
		}

		@Override
		public void run() {
			String ip1 = req.getHeader("x-forwarded-for");
			System.out.println("ʱ��:"
					+ TimeUtils.format(new Date(), "yyyy��MM��dd�� HHʱmm��ss��"));
			System.out.println("����Agent:"+ua);
			if(ua.contains("Android")){
				System.out.println("�豸����:" +"Android");
			}else if(ua.contains("iPhone")){
				System.out.println("�豸����:"+"iPhone");
			}else{
				System.out.println("�豸����:"+"Unknown");
			}
			System.out.println("����ӿ�:" + req.getRequestURI());
			String ip = req.getRemoteAddr();
			System.out.println("�豸IP��" + (ip1 == null ? ip : ip1));
			System.out.println("===============================================");
		}
	}
}
