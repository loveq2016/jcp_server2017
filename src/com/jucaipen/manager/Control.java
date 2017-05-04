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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String yy=request.getParameter("yy");
		String fun=request.getParameter("fun");
		String code=request.getParameter("code");
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(yy)){
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
		
		
		
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(fun)){
			if(fun.equals("on")){
				 request.getServletContext().setAttribute("expand", true);
				 result="显示扩展功能--操作成功";
			}else{
				 request.getServletContext().setAttribute("expand", false);
				 result="隐藏扩展功能--操作成功";
			}
		}else{
			result="操作码验证失败";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		out.flush();
		out.close();
	}


}
