package com.jucaipen.main.push;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.utils.StringUtil;
/**
 * @author —Ó¿ ∑…
 * 
 */
public class PushSysMessage extends HttpServlet {
	private static final long serialVersionUID = 3463292037748376005L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
		if(!id.isEmpty()&&StringUtil.isInteger(id)){
			int mId=Integer.parseInt(id);
			
		}
		out.println(result);
		out.flush();
		out.close();
	}

}
