package com.jucaipen.main.my;

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
import com.jucaipen.model.Guardian;
import com.jucaipen.model.User;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取我的守护 type 0 我守护的 1 守护我的 （针对讲师）
 */
public class MyGuardian extends HttpServlet {
	private static final long serialVersionUID = -8293459433867652032L;
	private String result;
	private List<FamousTeacher> teachers = new ArrayList<FamousTeacher>();
	private List<User> users = new ArrayList<User>();
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
		if (isDevice == HeaderUtil.PHONE_APP) {
			String userId = request.getParameter("userId");
			String page = request.getParameter("page");
			String type = request.getParameter("type");
			hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
							if (StringUtil.isNotNull(type)
									&& StringUtil.isInteger(type)) {
								int t = Integer.parseInt(type);
								result = initMyGuardian(uId, t, p);
							} else {
								result = JsonUtil.getRetMsg(2, "type 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "page 参数异常");
						}

					} else {
						result = JsonUtil.getRetMsg(3, "该用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, " userId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initMyGuardian(int uId, int t, int p) {
		// 初始化我的守护 /守护我的
		teachers.clear();
		users.clear();
		List<Guardian> guardians;
		/*if(t==0){
			Object cached = DataManager.getCached(Constant.TEACHER_CACHE,"gradinlist"+uId+p);
			if(cached!=null){
				return cached.toString();
			}
		}else{
			Object cached = DataManager.getCached(Constant.TEACHER_CACHE, "gradinMelist"+uId+p);
			if(cached!=null){
				return cached.toString();
			}
		}*/
		
		
		if (t == 0) {
			// 我守护的
			guardians = GuardianSer.findGurdianByUid(uId, p);
		} else {
			// 守护我的
			guardians = GuardianSer.findGuradianByTeacherId(uId, p);
		}
		for (Guardian guardian : guardians) {
			int tId = guardian.getTeacherId();
			int uid = guardian.getUserId();
			if (t == 0) {
				FamousTeacher teacher;
				Object cached = DataManager.getCached(Constant.TEACHER_CACHE, "userInfo"+uId,hasCache);
				if(cached==null){
					teacher = FamousTeacherSer.findFamousTeacherById(tId);
					new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherInfo"+tId, teacher);
				}else{
					teacher=(FamousTeacher) cached;
				}
				teachers.add(teacher);
			} else {
				User user;
				Object cached = DataManager.getCached(Constant.TEACHER_CACHE, "userInfo"+uId,hasCache);
				if(cached==null){
					user = UserServer.findUserById(uid);
					new CacheUtils(Constant.TEACHER_CACHE).addToCache("userInfo"+uId, user);
				}else{
					user=(User) cached;
				}
				users.add(user);
			}
		}
		if (t == 0) {
			String myGuardian = JsonUtil.getMyGuardian(guardians, teachers);
		//	new CacheUtils(Constant.VIDEO_CACHE).addToCache("gradinlist"+uId+p, myGuardian);
			return myGuardian;
		} else {
			 String guardianMy = JsonUtil.getGuardianMy(guardians, users);
		//	 new CacheUtils(Constant.VIDEO_CACHE).addToCache("gradinMelist"+uId+p, guardianMy);
			return guardianMy;
		}
	}

}
