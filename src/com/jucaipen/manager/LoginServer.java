package com.jucaipen.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jucaipen.model.ServerManager;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         登录后台管理系统
 * 
 */
public class LoginServer extends HttpServlet {
	private static final long serialVersionUID = -2446747156672320315L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (StringUtil.isNotNull(account)&&account.equals("admin")) {
			if (StringUtil.isNotNull(password)) {
					if (password.equals("123456")) {
						// 登录成功
					/*	Cookie cookie=new Cookie("name", "admin"+UUID.randomUUID().toString());
						cookie.setSecure(false);
						cookie.setValue(account+UUID.randomUUID().toString());
						response.addCookie(cookie);*/
						HttpSession session = request.getSession(true);
						session.setAttribute("account", account);
						session.setMaxInactiveInterval(60);
						request.getRequestDispatcher("index.jsp").forward(
								request, response);
					} else {
						// 密码错误
						response.sendRedirect("login.jsp");
					}
				} else {
					// 当前账号不存在
					response.sendRedirect("login.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		out.flush();
		out.close();
	}
}
