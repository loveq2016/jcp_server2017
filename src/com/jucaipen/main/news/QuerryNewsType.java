package com.jucaipen.main.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.NewSmallClass;
import com.jucaipen.service.NewSmallClassSer;
import com.jucaipen.utils.JsonUtil;

/**
 * @author Administrator
 * 
 *         获取资讯分类信息
 */
public class QuerryNewsType extends HttpServlet {
	private static final long serialVersionUID = -8237640124427982067L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		result = initNewsType();
		out.println(result);
		out.flush();
		out.close();
	}

	private String initNewsType() {
		List<NewSmallClass> smalls = NewSmallClassSer.findClassByBigId(2);
		return JsonUtil.getJcpNewsType(smalls);
	}

}
