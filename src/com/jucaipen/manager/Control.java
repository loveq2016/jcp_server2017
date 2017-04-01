package com.jucaipen.manager;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.service.AdverSer;
/**
* @author Administrator
*/
public class Control extends HttpServlet {
	private String img="http://img.jucaipen.com/jucaipenStudy/2017/1/24/2017124141518.png";
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
		if(yy.equals("on")){
			 res=AdverSer.updateState(12, img, "shake");
		}else{
			 res=AdverSer.updateState(12, img, "sh");
		}
		if(res>0){
			result="操作成功";
		}else{
			result="操作失败";
		}
		out.print("<h2>");
		out.print(result);
		out.print("</h2>");
		out.flush();
		out.close();
	}


}
