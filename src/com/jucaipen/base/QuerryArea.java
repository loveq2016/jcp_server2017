package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Area;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.service.AreaServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.MsgCode;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取区信息
 * 
 */
@SuppressWarnings("serial")
public class QuerryArea extends HttpServlet {
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
			String cityId = request.getParameter("cityId");
			if (StringUtil.isInteger(cityId)) {
				int cId = Integer.parseInt(cityId);
				result = initAreaData(cId);
			} else {
				result = MsgCode.CURRENT_VERSION == MsgCode.HISTORY_VISION_1 ? JsonUtil
						.getRetMsg(1, "城市id参数数字格式化异常") : JsonUtil.getAreaV2(
						null, MsgCode.RET_FAIL_PARAMERROR_CODE,
						MsgCode.RET_FAIL_PARAMERROR);
			}
		} else {
			result = MsgCode.CURRENT_VERSION == MsgCode.HISTORY_VISION_1 ? StringUtil.isVaild
					: JsonUtil.getAreaV2(null, MsgCode.RET_FAIL_DEVERROR_CODE,
							MsgCode.RET_FAIL_DEVERROR);
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initAreaData(int cId) {
		// 初始化区信息
		Object cached = DataManager.getCached(Constant.DEFAULT_CACHE, "area"+cId,hasCache);
		if(cached!=null){
			return cached.toString();
		}
		List<Area> areas = AreaServer.getAreas(0, cId);
		String object = JsonUtil.getObject(areas);
		new CacheUtils(Constant.DEFAULT_CACHE).addToCache("area"+cId, object);
		return MsgCode.CURRENT_VERSION == MsgCode.HISTORY_VISION_1 ? object : JsonUtil.getAreaV2(areas,
				MsgCode.RET_SUCCESS_CODE, MsgCode.RET_SUCCESS);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
