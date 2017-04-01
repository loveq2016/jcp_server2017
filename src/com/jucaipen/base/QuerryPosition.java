package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Position;
import com.jucaipen.service.PositionSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取岗位信息
 */
@SuppressWarnings("serial")
public class QuerryPosition extends HttpServlet {
	private String result;
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
			String typeId = request.getParameter("typeId");
			if (StringUtil.isNotNull(typeId) && StringUtil.isInteger(typeId)) {
				int type = Integer.parseInt(typeId);
				result = initPosition(type);
			} else {
				result = JsonUtil.getRetMsg(4, "typeId 参数数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initPosition(int type) {
		// 初始化岗位信息
		List<Position> positions = PositionSer.findPositionByCid(type);
		return JsonUtil.getPositionlist(positions);
	}
}
