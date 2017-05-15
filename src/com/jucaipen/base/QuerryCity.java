package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.City;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.service.CityServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.MsgCode;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator ��ѯ���г�����Ϣ
 */

@SuppressWarnings("serial")
public class QuerryCity extends HttpServlet {
	private String result;
	private boolean hasCache;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
		if(isDevice==HeaderUtil.PHONE_APP){
			String provinceId=request.getParameter("provinceId");
			if(StringUtil.isInteger(provinceId)){
				int pId=Integer.parseInt(provinceId);
				result=initCityInfo(pId);
			}else {
				result=MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? JsonUtil.getRetMsg(1, "ʡ��id�������ָ�ʽ���쳣") : JsonUtil.getCityV2(null, MsgCode.RET_FAIL_PARAMERROR_CODE, MsgCode.RET_FAIL_PARAMERROR);
			}
		}else{
			result=MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? StringUtil.isVaild : JsonUtil.getCityV2(null, MsgCode.RET_FAIL_DEVERROR_CODE, MsgCode.RET_FAIL_DEVERROR);
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initCityInfo(int pId) {
		Object cached = DataManager.getCached(Constant.DEFAULT_CACHE, "city"+pId,hasCache);
		if(cached!=null){
			return cached.toString();
		}
		
		List<City> cities = CityServer.getCitys(pId);
		String object = JsonUtil.getObject(cities);
		new CacheUtils(Constant.DEFAULT_CACHE).addToCache("city"+pId, object);
		return MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? object  : JsonUtil.getCityV2(cities, MsgCode.RET_SUCCESS_CODE, MsgCode.RET_SUCCESS);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
