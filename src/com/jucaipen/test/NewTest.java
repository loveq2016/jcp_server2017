package com.jucaipen.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="news",value="newsing" )
public class NewTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("hhh");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.getWriter().write("<h1>哈哈哈，我访问到了...</h1>");
	}

}
