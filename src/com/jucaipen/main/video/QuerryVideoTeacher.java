package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.FamousTeacher;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.utils.JsonUtil;

/**
 * @author Administrator
 *
 *   获取视频讲师分类信息
 */
public class QuerryVideoTeacher extends HttpServlet {
	private static final long serialVersionUID = -6780758137101273508L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		result=initVideoTeacherInfo();
		out.println(result);
		out.flush();
		out.close();
	}
	private String initVideoTeacherInfo() {
		List<FamousTeacher> teachers = FamousTeacherSer.findAllTeacher();
		return JsonUtil.getTeacherList(teachers);
	}

}
