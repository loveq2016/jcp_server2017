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
		new PrintLog(ua, req, resp).start();
//		String localAddr = req.getLocalAddr();
//		String uri = req.getRequestURI();
		// 过滤访问 ios android 第三方回调
		chain.doFilter(req, resp);
		/*if (localAddr.equals("121.40.227.121")) {
			if (ua.contains("iPhone") || ua.contains("Android")
					|| ua.contains("iOS") || uri.contains("livenotify")) {
				chain.doFilter(req, resp);
			} else {
				//resp.sendError(205, "请求失败");
			}
		} else if (localAddr.equals("192.168.1.134")) {
			 chain.doFilter(req, resp);
		} else {
			//resp.sendError(205, "请求失败");
		}*/
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 初始化

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
			System.out.println("时间:"
					+ TimeUtils.format(new Date(), "yyyy年MM月dd日 HH时mm分ss秒"));
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
