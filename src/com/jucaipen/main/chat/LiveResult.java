package com.jucaipen.main.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author 杨朗飞
 *        直播接口回调
 */
public class LiveResult extends HttpServlet {
	private static final long serialVersionUID = 3902704489096210297L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Enumeration<String> names = request.getParameterNames();
		StringBuffer buffer=new StringBuffer();
		while (names.hasMoreElements()) {
			String string = (String) names.nextElement();
			buffer.append(string+"====="+request.getParameter(string));
			System.out.println(string+"====="+request.getParameter(string));
		}
		out.flush();
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
