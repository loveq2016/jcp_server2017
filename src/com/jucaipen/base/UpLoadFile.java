package com.jucaipen.base;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * @author 杨朗飞
 *   多文件上传文件
 */
@MultipartConfig(location="C:\\Users\\Administrator\\git\\jcp_server2017\\AccumulateWealth\\WebRoot\\WEB-INF\\upload")
public class UpLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		System.out.println("user:"+userName);
		for(Part p : request.getParts()){
			if(p!=null){
				String name = getFilename(p);
				if(name!=null&&!"".equals(name)){
					System.out.println("fileName:"+name);
					p.write(name);
				}
			}
		}
		PrintWriter writer = response.getWriter();
		writer.write("上传成功");
		System.out.println("上传成功");
		writer.flush();
		writer.close();
	}
	
	  /**
	 * @param part
	 * @return  获取文件名称
	 */
	private String getFilename(Part part) {
	        if (part == null) {
	            return null;
	        }
	        String fileName = part.getHeader("content-disposition");
	        if (isBlank(fileName)) {
	            return null;
	        }
	        return substringBetween(fileName, "filename=\"", "\"");
	    }

	    public static boolean isBlank(String str) {
	        int strLen;
	        if (str == null || (strLen = str.length()) == 0)
	            return true;
	        for (int i = 0; i < strLen; i++) {
	            if (!Character.isWhitespace(str.charAt(i))) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public static String substringBetween(String str, String open, String close) {
	        if (str == null || open == null || close == null)
	            return null;
	        int start = str.indexOf(open);
	        if (start != -1) {
	            int end = str.indexOf(close, start + open.length());
	            if (end != -1)
	                return str.substring(start + open.length(), end);
	        }
	        return null;
	    }

}
