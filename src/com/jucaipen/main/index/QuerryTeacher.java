package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Fans;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.FansSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取名师信息 isIndex 0 首页推荐名师 1 全部名师列表
 */
@SuppressWarnings("serial")
public class QuerryTeacher extends HttpServlet {
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
			String isIndex = request.getParameter("isIndex");
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(isIndex)) {
				if (StringUtil.isInteger(isIndex)) {
					int index = Integer.parseInt(isIndex);
					if (StringUtil.isNotNull(userId)) {
						if (StringUtil.isInteger(userId)) {
							int uId = Integer.parseInt(userId);
							if (index == 0) {
								// 首页推荐名师
								result = initIndexData(uId);
							} else {
								// 全部名师列表
								String page = request.getParameter("page");
								if (StringUtil.isNotNull(page)
										&& StringUtil.isInteger(page)) {
									int p = Integer.parseInt(page);
									result = initAllData(p, uId);
								} else {
									result = JsonUtil.getRetMsg(3, "page参数有误");
								}
							}
						} else {
							result = JsonUtil.getRetMsg(4, "userId 参数数字格式化异常");
						}
					} else {
						result = JsonUtil.getRetMsg(4, "userId 参数不能为空");
					}

				} else {
					result = JsonUtil.getRetMsg(2, "isIndex参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "isIndex参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initAllData(int page, int uId) {
		// 初始化全部名师信息
		Object cached = DataManager.getCached(Constant.TEACHER_CACHE, uId+"allTeacher"+page,hasCache);
		if(cached!=null){
			return cached.toString();
		}
		List<FamousTeacher> teachers = FamousTeacherSer
				.findAllFamousTeacher(page);
		if (teachers != null) {
			for (FamousTeacher teacher : teachers) {
				int tId = teacher.getId();
				VideoLive videoLive;
				Object cached2 = DataManager.getCached(Constant.VIDEO_CACHE, tId+"teacherVideo"+uId+page,hasCache);
				if(cached2==null){
					videoLive = VideoLiveServer.findLiveBytId(tId);
					new CacheUtils(Constant.TEACHER_CACHE).addToCache(tId+"teacherVideo"+uId+page, videoLive);
				}else{
					videoLive=(VideoLive) cached2;
				}
				if (videoLive != null) {
					teacher.setIsEnd(videoLive.getIsEnd());
				}
			}
		}
		String teacherList = JsonUtil.getAllFamousTeacherList(teachers);
		new CacheUtils(Constant.TEACHER_CACHE).addToCache(uId+"allTeacher"+page, teacherList);
		return teacherList;
	}

	private String initIndexData(int uId) {
		// 初始化首页名师推荐列表信息
		Object cached = DataManager.getCached(Constant.RECODER_TEACHER, "indexTeacher",hasCache);
		if(cached!=null){
			return cached.toString();
		}
		List<FamousTeacher> teachers = FamousTeacherSer
				.findFamousTeacherByIndex(5);
		List<Integer> isAttentions = new ArrayList<Integer>();
		if (teachers != null) {
			for (FamousTeacher teacher : teachers) {
				int tId = teacher.getId();
				VideoLive videoLive=VideoLiveServer.findLiveBytId(tId);
				if(videoLive!=null){
					teacher.setIsEnd(teacher.getIsEnd());
				}
				Fans fan = FansSer.findIsFans(uId, tId);
				if (fan != null) {
					isAttentions.add(0);
				} else {
					isAttentions.add(1);
				}
			}
		}
		String list = JsonUtil.getFamousTeacherList(teachers, isAttentions);
		new CacheUtils(Constant.RECODER_TEACHER).addToCache("indexTeacher", list);
		return list;
	}

}
