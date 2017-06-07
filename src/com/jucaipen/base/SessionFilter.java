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
 * @author ���ʷ�
 *    ������־ ��Ȩ�޿���
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
			//1���ж� account �˺���� �Ƿ���Բ��� url ����
			//2���ж� url ��������   �� doChain() ֮�� logд���ļ�
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
