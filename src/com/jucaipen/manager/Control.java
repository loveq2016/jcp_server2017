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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String yy=request.getParameter("yy");
		String fun=request.getParameter("fun");
		String code=request.getParameter("code");
		String cacheSwith=request.getParameter("cacheSwith");
		StringBuilder buffer=new StringBuilder();
		// 审核状态
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(yy)){
			if(yy.equals("on")){
				 request.getServletContext().setAttribute("check", true);
				 buffer.append("正在审核状态--操作成功<br/>");
			}else{
				 request.getServletContext().setAttribute("check", false);
				 buffer.append("审核完成状态--操作成功<br/>");
			}
		}else{
			buffer.append("审核状态操作码验证失败<br/>");
		}
		
		
		// 功能展示
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(fun)){
			if(fun.equals("on")){
				 request.getServletContext().setAttribute("expand", true);
				 buffer.append("显示扩展功能--操作成功<br/>");
			}else{
				 request.getServletContext().setAttribute("expand", false);
				 buffer.append("隐藏扩展功能--操作成功<br/>");
			}
		}else{
			buffer.append("功能扩展操作码验证失败<br/>");
		}
		
		//  启用 关闭缓存
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(cacheSwith)){
			if(cacheSwith.equals("on")){
				 request.getServletContext().setAttribute("hasCache", true);
				 buffer.append("开启缓存--操作成功<br/>");
			}else{
				 request.getServletContext().setAttribute("hasCache", false);
				 buffer.append("关闭缓存--操作成功<br/>");
			}
		}else{
			buffer.append("缓存管理操作码验证失败<br/>");
		}
		
		request.setAttribute("result", buffer.toString());
		request.getRequestDispatcher("admin/result.jsp").forward(request, response);
		out.flush();
		out.close();
	}


}
