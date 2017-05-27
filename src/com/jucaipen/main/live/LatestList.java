package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.User;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取最新榜单信息 type 0 日榜单 1 月榜单
 */
public class LatestList extends HttpServlet {
	private static final long serialVersionUID = -1470334903009105509L;
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
			String type = request.getParameter("type");
			String teacherId = request.getParameter("teacherId");
			String page=request.getParameter("page");
			if (!StringUtil.isNotNull(type)) {
				result = JsonUtil.getRetMsg(1, "type 参数不能为空");
			} else {
				if (StringUtil.isInteger(type)) {
					int t = Integer.parseInt(type);
					if (StringUtil.isNotNull(teacherId)
							&& StringUtil.isInteger(teacherId)) {
						int tId = Integer.parseInt(teacherId);
						result = initlist(t, tId,page);
					} else {
						result = JsonUtil.getRetMsg(3, "teacherId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "type 参数数字格式化异常");
				}
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	public String initlist(int t, int tId, String page) {
		// 初始化榜单信息
		List<Contribute> contributes;
		Object cached = DataManager.getCached(Constant.TEACHER_CACHE,t+"userLast"+tId,hasCache);
		if(cached!=null){
			return cached.toString();
		}
		if (t == 0) {
			// 日榜单 
			contributes=ContributeSer.findTopContributeByTid(tId, "day",15);
		} else if (t == 1) {
			// 月榜单
			contributes=ContributeSer.findTopContributeByTid(tId, "month",15);
		} else {
			// 年榜单
			contributes=ContributeSer.findTopContributeByTid(tId, "year",15);
		}
		for (Contribute contribute : contributes) {
			int userId = contribute.getUserId();
			User user ;
			Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE, "userInfo"+userId,hasCache);
			if(cached2==null){
				user = UserServer.findUserById(userId);
				int integeral=user.getAllIntegral();
				if(integeral>0){
					user.setUserLeval(integeral < 30001 ? (int) Math
							.ceil((double)integeral / 100)
							: (int) Math.ceil((double) 30001 / 100));
				}else{
					user.setUserLeval(0);
				}
				new CacheUtils(Constant.TEACHER_CACHE).addToCache( "userInfo"+userId, user);
			}else{
				user=(User) cached2;
			}
			contribute.setFromName(user.getNickName());
			contribute.setFromFace(user.getFaceImage());
		}
		String lateList = JsonUtil.getLateList(contributes);
		new CacheUtils(Constant.TEACHER_CACHE).addToCache(t+"userLast"+tId, lateList);
		return lateList;
	}

}
