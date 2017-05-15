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
import com.jucaipen.model.Province;
import com.jucaipen.service.ProvinceServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.MsgCode;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         查询省份信息
 * 
 */
@SuppressWarnings("serial")
public class QuerryProvince extends HttpServlet {
	private String result;
	private boolean hasCache;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			result=initProvinceInfo();
		}else{
			result=MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? StringUtil.isVaild : JsonUtil.getProvinceV2(null, MsgCode.RET_FAIL_DEVERROR_CODE, MsgCode.RET_FAIL_DEVERROR);
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initProvinceInfo() {
		Object cached = DataManager.getCached(Constant.DEFAULT_CACHE, "province",hasCache);
		if(cached!=null){
			return cached.toString();
		}
		List<Province> provinces = ProvinceServer.getProvinces();
		String object = JsonUtil.getObject(provinces);
		new CacheUtils(Constant.DEFAULT_CACHE).addToCache("province", object);
		return MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? object : JsonUtil.getProvinceV2(provinces, MsgCode.RET_SUCCESS_CODE, MsgCode.RET_SUCCESS);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
