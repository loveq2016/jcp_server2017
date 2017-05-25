package com.jucaipen.base;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AccountFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url=req.getRequestURI();
		if(!url.contains("login")){
			if(session!=null&&session.getAttribute("account")!=null){
				String attribute = (String) session.getAttribute("account");
				if("admin".equals(attribute)){
					chain.doFilter(new BaseRequest(req), resp);
				}else{
					resp.sendRedirect(req.getContextPath()+"/login.jsp");
				}
			}else{
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
			}
		}else{
			chain.doFilter(new BaseRequest(req), resp);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
