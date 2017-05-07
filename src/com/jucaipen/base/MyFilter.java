package com.jucaipen.base;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jpush.api.utils.StringUtils;

import com.jucaipen.model.Visitor;
import com.jucaipen.service.VisitorService;
import com.jucaipen.utils.TimeUtils;

public class MyFilter implements Filter {
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
		String localAddr = req.getLocalAddr();
		String uri = req.getRequestURI();
		String host = req.getRemoteHost();
		new PrintLog(ua, req, resp,uri,localAddr,host).start();
		chain.doFilter(new BaseRequest(req), resp);
		// ���˷��� ios android �������ص�
		/*if (localAddr.equals("121.40.227.121")) {
			if (ua.contains("iPhone") || ua.contains("Android")
					|| ua.contains("iOS") || 
					uri.contains("livenotify")
					||uri.contains("rechargeResult")
					||uri.contains("jsp")
					||uri.contains("html")
					||uri.contains("upload")
					||host.equals("192.168.1.134")
					) {
				chain.doFilter(new BaseRequest(req), resp);
			} else {
				//resp.sendError(205, "����ʧ��");
			}
		} else if (localAddr.equals("192.168.1.134")) {
			 chain.doFilter(new BaseRequest(req), resp);
		} else {
			//resp.sendError(205, "����ʧ��");
		}*/
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// ��ʼ��

	}

	public class PrintLog extends Thread {
		private String ua;
		private HttpServletRequest req;
		private String uri;
		private String hostAddress;
		private String host;

		public PrintLog(String ua, HttpServletRequest req,
				HttpServletResponse resp, String uri, String hostAddress, String host) {
			this.ua = ua;
			this.req = req;
			this.uri=uri;
			this.hostAddress=hostAddress;
			this.host=host;
		}

		@Override
		public void run() {
			Visitor visitor=new Visitor();
			String format = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			String ip1 = req.getHeader("x-forwarded-for");
			System.out.println("ʱ��:"
					+format );
			System.out.println("����Agent:" + ua);
			visitor.setHead(ua);
			if (!StringUtils.isEmpty(ua)) {
				if (ua.contains("Android")) {
					visitor.setDevType(1);
					System.out.println("�豸����:" + "Android");
				} else if (ua.contains("iPhone")) {
					visitor.setDevType(2);
					System.out.println("�豸����:" + "iPhone");
				} else {
					visitor.setDevType(3);
					System.out.println("�豸����:" + "Unknown");
				}
			} else {
				visitor.setDevType(3);
				System.out.println("�豸����:" + "Unknown");
			}
			visitor.setInsertDate(format);
			System.out.println("����URL:" + req.getRequestURI());
			String ip = req.getRemoteAddr();
			visitor.setIp(ip);
			visitor.setUrl(uri);
			visitor.setHost(host);
			visitor.setHostAddress(hostAddress);
			VisitorService.addVisitor(visitor);
			System.out.println("�豸IP��" + (ip1 == null ? ip : ip1));
			System.out
					.println("===============================================");
		}
	}
}
