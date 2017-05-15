package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.GiftClass;
import com.jucaipen.service.GiftClassSer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
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
	private boolean hasCache;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
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
		Object cached = DataManager.getCached(Constant.FILE_CACHE, "giftType",hasCache);
		if(cached!=null){
			return cached.toString();
		}
		
		List<GiftClass> leftClass = GiftClassSer.findAllClass();
		String giftClass = JsonUtil.getGiftClass(leftClass);
		new CacheUtils(Constant.FILE_CACHE).addToCache("giftType", giftClass);
		return giftClass;
	}
	

}
