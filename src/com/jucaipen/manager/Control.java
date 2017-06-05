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
*   ����״̬
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
		// ���״̬
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(yy)){
			if(yy.equals("on")){
				 request.getServletContext().setAttribute("check", true);
				 buffer.append("�������״̬--�����ɹ�<br/>");
			}else{
				 request.getServletContext().setAttribute("check", false);
				 buffer.append("������״̬--�����ɹ�<br/>");
			}
		}else{
			buffer.append("���״̬��������֤ʧ��<br/>");
		}
		
		
		// ����չʾ
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(fun)){
			if(fun.equals("on")){
				 request.getServletContext().setAttribute("expand", true);
				 buffer.append("��ʾ��չ����--�����ɹ�<br/>");
			}else{
				 request.getServletContext().setAttribute("expand", false);
				 buffer.append("������չ����--�����ɹ�<br/>");
			}
		}else{
			buffer.append("������չ��������֤ʧ��<br/>");
		}
		
		//  ���� �رջ���
		if(StringUtil.isNotNull(code)&&code.equals("jcp123")&&StringUtil.isNotNull(cacheSwith)){
			if(cacheSwith.equals("on")){
				 request.getServletContext().setAttribute("hasCache", true);
				 buffer.append("��������--�����ɹ�<br/>");
			}else{
				 request.getServletContext().setAttribute("hasCache", false);
				 buffer.append("�رջ���--�����ɹ�<br/>");
			}
		}else{
			buffer.append("��������������֤ʧ��<br/>");
		}
		
		request.setAttribute("result", buffer.toString());
		request.getRequestDispatcher("admin/result.jsp").forward(request, response);
		out.flush();
		out.close();
	}


}
