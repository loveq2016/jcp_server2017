package com.jucaipen.test;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/**
 * @author Administrator
 * 
 *         测试在线人数
 */
@SuppressWarnings("serial")
public class TestOnLine extends HttpServlet {
	private List<String> names = new ArrayList<String>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("name");
		names.add(userName);
		JsonArray array = new JsonArray();
		for (String n : names) {
			JsonObject object = new JsonObject();
			object.addProperty("name", n);
			array.add(object);
		}
		//out.println(array.toString());
		request.setAttribute("list", names);
		request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
		out.flush();
		out.close();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
