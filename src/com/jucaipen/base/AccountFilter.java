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
public class AccountFilter implements Filter {

	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String url = req.getRequestURI();
		//来源验证
		String referer = req.getHeader("referer");
		if(!url.contains(".jsp")&&!url.contains(".html")){
			//不过滤非 html、JSP文件
			chain.doFilter(req, resp);
			return ;
		}
		
		if (!url.contains("login.jsp")) {
			if (session != null && session.getAttribute("account") != null) {
				chain.doFilter(new BaseRequest(req), resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login.jsp");
			}
		} else {
			if (session != null && session.getAttribute("account") != null) {
				resp.sendRedirect("admin/index.jsp");
			} else {
				chain.doFilter(new BaseRequest(req), resp);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
