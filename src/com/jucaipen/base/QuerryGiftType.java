package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.GiftClass;
import com.jucaipen.service.GiftClassSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取礼品分类信息
 */
@SuppressWarnings("serial")
public class QuerryGiftType extends HttpServlet {
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
		   result = initClassData();
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initClassData() {
		// 初始化礼品分类信息 left
		List<GiftClass> leftClass = GiftClassSer.findAllClass();
		return JsonUtil.getGiftClass(leftClass);
	}
	

}
