package com.jucaipen.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jucaipen.utils.MD5Util;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         ��¼��̨����ϵͳ
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
		String checkCode = request.getParameter("checkCode");
		String lowerCase = MD5Util.MD5("123456").toLowerCase();
		if (StringUtil.isNotNull(account) && account.equals("admin")) {
			if (StringUtil.isNotNull(password) && password.equals(lowerCase)) {
				// ��¼�ɹ�
				HttpSession session = request.getSession(true);
				String code = (String) session.getAttribute("randomCode");
				if (code.equalsIgnoreCase(checkCode)) {
					session.setAttribute("account", account);
					session.setMaxInactiveInterval(60 * 10);
					response.sendRedirect("admin/index.jsp");
				} else {
					// ��֤�����
					response.sendRedirect("login.jsp?errCode=1");
				}
			} else {
				// �������
				response.sendRedirect("login.jsp?errCode=2");
			}
		} else {
			// �û�������
			response.sendRedirect("login.jsp?errCode=4");
		}
		out.flush();
		out.close();
	}
}
