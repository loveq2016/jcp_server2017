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

import org.json.JSONObject;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.TimeUtils;

public class MyFilter implements  Filter{
	private String getAddress="http://ip.taobao.com/service/getIpInfo.php?ip=";

	@Override
	public void destroy() {
		//销毁
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 过滤请求类型---过滤
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		String ua = req.getHeader("User-Agent");
		new PrintLog(ua,req,resp).start();
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//初始化 
		
	}
	
	
	public class PrintLog extends Thread{
		private String ua;
		private HttpServletRequest req;
		public PrintLog(String ua, HttpServletRequest req,
				HttpServletResponse resp) {
			this.ua=ua;
			this.req=req;
		}

		@Override
		public void run() {
			ClientOsInfo mobilOS = HeaderUtil.getMobilOS(ua);
			String ip1 = req.getHeader("x-forwarded-for");
			System.out.println("时间:"+TimeUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			System.out.println("设备Header:"+ua);
			System.out.println("请求接口:"+req.getRequestURI());
			String ip = req.getRemoteAddr();
			System.out.println("设备IP："+(ip1==null ? ip : ip1));
			String get = LoginUtil.sendHttpGet(getAddress+(ip1==null ? ip : ip1), null);
			JSONObject object=new JSONObject(get);
			JSONObject jsonObject = object.optJSONObject("data");
			if(jsonObject!=null){
				String country=jsonObject.optString("country");
				String region=jsonObject.optString("region");
				String city=jsonObject.optString("city");
				String area=jsonObject.optString("area");
				String isp=jsonObject.optString("isp");
				System.out.println("位置:"+country+"-"+region+"-"+city+"-"+area+"-"+isp);
			}
			System.out.println("=======================================================");
		}
	}

}
