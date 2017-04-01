package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Fans;
import com.jucaipen.service.FansSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *   查询是否关注
 */
@SuppressWarnings("serial")
public class QuerryAttention extends HttpServlet {
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
			String teacherId = request.getParameter("teacherId");
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isInteger(teacherId)) {
							int tId = Integer.parseInt(teacherId);
							result = querryIsAttention(uId, tId);
						} else {
							result = JsonUtil.getRetMsg(1, "讲师id数字格式化异常");
						}
					} else {
						result = JsonUtil.getRetMsg(3, "用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(4, "userId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	public String querryIsAttention(int uId, int tId) {
		Fans fans = FansSer.findIsFans(uId, tId);
		return fans == null ? JsonUtil.getRetMsg(1, "该讲师还没关注") : JsonUtil
				.getRetMsg(0, "该讲师已经关注");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
