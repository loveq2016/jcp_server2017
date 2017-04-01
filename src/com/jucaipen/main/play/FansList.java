package com.jucaipen.main.play;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Studio;
import com.jucaipen.service.StudioSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 *
 *  ÈËÆø°ñ
 */
public class FansList extends HttpServlet {
	private static final long serialVersionUID = 161956627147491980L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		result=initFansList();
		out.println(result);
		out.flush();
		out.close();
	}

	private String initFansList() {
		List<Studio> studio = StudioSer.findStudioFansByToday(TimeUtils.getWeek());
		return JsonUtil.getStudioFansList(studio);
	}

}
