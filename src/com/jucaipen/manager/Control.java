package com.jucaipen.manager;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.utils.StringUtil;
/**
* @author Administrator
* 
*   更改状态
*/
public class Control extends HttpServlet {
	private static final long serialVersionUID = 4906602039828919994L;
	private String result;
	private int res;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String yy=request.getParameter("yy");
		String code=request.getParameter("code");
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")){
			if(yy.equals("on")){
				 request.getServletContext().setAttribute("check", true);
				 result="审核状态--操作成功";
			}else{
				 request.getServletContext().setAttribute("check", false);
				 result="审核完成状态--操作成功";
			}
		}else{
			result="操作码验证失败";
		}
		out.print("<h2>");
		out.print(result);
		out.print("</h2>");
		out.flush();
		out.close();
	}


}
