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
import com.jucaipen.utils.TimeUtils;

public class MyFilter implements Filter {
	@Override
	public void destroy() {
		// 销毁
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 过滤请求类型---过滤
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String ua = req.getHeader("User-Agent");
		String localAddr = req.getLocalAddr();
		String uri = req.getRequestURI();
		String host = req.getRemoteHost();
		if (localAddr.equals("121.40.227.121")){
			new PrintLog(ua, req, resp,uri,localAddr,host).start();
		}
		chain.doFilter(new BaseRequest(req), resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 初始化

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
			String format = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			String ip1 = req.getHeader("x-forwarded-for");
			System.out.println("时间:"
					+format );
			System.out.println("请求Agent:" + ua);
			if (!StringUtils.isEmpty(ua)) {
				if (ua.contains("Android")) {
					System.out.println("设备类型:" + "Android");
				} else if (ua.contains("iPhone")) {
					System.out.println("设备类型:" + "iPhone");
				} else {
					System.out.println("设备类型:" + "Unknown");
				}
			} else {
				System.out.println("设备类型:" + "Unknown");
			}
			System.out.println("请求URL:" + req.getRequestURI());
			String ip = req.getRemoteAddr();
			System.out.println("设备IP：" + (ip1 == null ? ip : ip1));
			System.out
					.println("===============================================");
		}
	}
}
