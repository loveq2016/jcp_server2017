package com.jucaipen.base;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.utils.LoginUtil;

public class LoadImg extends HttpServlet{
	private static final long serialVersionUID = 4381883941906114089L;
	private String path="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=ImageFomat";
	private Map<String, String> param=new HashMap<String, String>();
	private  String resultMsg;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
        getImage(request,response);
	}
	
	
	private void getImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String url = request.getParameter("url");
		if(url!=null){
			param.put("imgurl", url);
			resultMsg = LoginUtil.sendHttpPost(path, param);
		}else{
			resultMsg="url²»ÄÜÎª¿Õ";
		}
		
		System.out.println("resultMsg:"+resultMsg);
		out.println(resultMsg);
		out.flush();
		out.close();
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  getImage(request,response);
	}

}
