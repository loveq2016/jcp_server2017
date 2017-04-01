package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.VideoType;
import com.jucaipen.service.VideoTypeSer;
import com.jucaipen.utils.JsonUtil;

/**
 * @author Administrator
 *   获取视频类型信息    
 */
public class QuerryVideoType extends HttpServlet {
	private static final long serialVersionUID = 6637614625160082897L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		result=initVideoClassInfo();
		out.println(result);
		out.flush();
		out.close();
	}
	private String initVideoClassInfo() {
		List<VideoType> videoClass = VideoTypeSer.findAll();
		return JsonUtil.getVideoTypelist(videoClass);
	}

}
