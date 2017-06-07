package com.jucaipen.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 杨朗飞
 *    操作日志 、权限控制
 */
public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		String url = req.getRequestURI();
		String account = (String) req.getSession().getAttribute("account");
		if(null!=account&&!"".equals(account.trim())){
			//1、判断 account 账号身份 是否可以操作 url 请求
			//2、判断 url 操作类型   在 doChain() 之后将 log写入文件
			chain.doFilter(req, resp);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			req.getServletContext().log(sdf.format(new Date())+"     "+account+":"+url);
		}else{
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
