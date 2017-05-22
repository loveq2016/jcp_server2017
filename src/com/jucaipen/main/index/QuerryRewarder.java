package com.jucaipen.main.index;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Marker;
import com.jucaipen.model.User;
import com.jucaipen.service.MarkerSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator 观点中 获取打赏列表
 */
@SuppressWarnings("serial")
public class QuerryRewarder extends HttpServlet {
	private String result;
	private List<String> faces = new ArrayList<String>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String logId = request.getParameter("logId");
			if (StringUtil.isNotNull(logId)) {
				if (StringUtil.isInteger(logId)) {
					int lId = Integer.parseInt(logId);
					result = initMarkData(lId);
				} else {
					result = JsonUtil.getRetMsg(2, "logId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "logId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initMarkData(int logId) {
		// 初始化打赏信息
		faces.clear();
		List<Marker> markers = MarkerSer.findTopMarkerByLogId(logId, 18);
		for (Marker marker : markers) {
			int uId = marker.getUserId();
			User user= UserServer.findFaceImageById(uId);
			faces.add(user.getFaceImage());
		}
		return JsonUtil.getMarkerData(markers, faces);

	}

}
