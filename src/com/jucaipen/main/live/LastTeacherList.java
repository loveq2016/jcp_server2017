package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞  讲师总榜单
 */
public class LastTeacherList extends HttpServlet {
	private static final long serialVersionUID = 2779291546693682575L;
	private String result;
	private boolean hasCache;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
		if (StringUtil.isNotNull(type) && StringUtil.isInteger(type)) {
			int typeId = Integer.parseInt(type);
			result = getTeacherLastList(typeId);
		}
		out.println(result);
		out.flush();
		out.close();
	}
	/**
	 * @param typeId
	 * @param p
	 * @return 获取最近的讲师排行榜
	 */
	private String getTeacherLastList(int typeId) {
		Object cached = DataManager.getCached(Constant.TEACHER_CACHE, "teacherLase"+typeId,hasCache);
		if(cached!=null){
			return cached.toString();
		}
		
		List<Contribute> contributes;
		if (typeId == 0) {
			contributes=ContributeSer.findContributeGroupByTid("year");
		} else if (typeId == 1) {
			contributes=ContributeSer.findContributeGroupByTid("month");
		} else {
			contributes=ContributeSer.findContributeGroupByTid("day");
		}
		for (Contribute contribute : contributes) {
			FamousTeacher teacher;
			VideoLive videoLive;
			int teacherId = contribute.getTeacherId();
			Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE, "teacherInfo"+teacherId,hasCache);
			if(cached2==null){
				teacher = FamousTeacherSer
						.findTeacherBaseInfo(teacherId);
				new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherInfo"+teacherId, teacher);
			}else{
				try {
					teacher=(FamousTeacher) cached2;
				} catch (Exception e) {
					teacher = FamousTeacherSer
							.findTeacherBaseInfo(teacherId);
				}
			}
			Object cached3 = DataManager.getCached(Constant.VIDEO_CACHE, "teachervideo"+teacherId,hasCache);
			if(cached3==null){
				videoLive=VideoLiveServer.findLiveBytId(teacherId);
				new CacheUtils(Constant.TEACHER_CACHE).addToCache("teachervideo"+teacherId, videoLive);
			}else{
				videoLive=(VideoLive) cached3;
			}
			contribute.setFromName(teacher.getNickName());
			contribute.setFromFace(teacher.getHeadFace());
			contribute.setLeavel(teacher.getLevel());
			contribute.setIntroduce(teacher.getIntroduce());
			if(videoLive!=null){
				contribute.setIsEnd(videoLive.getIsEnd());
			}
		}
		String last = JsonUtil.getLastTeacherLast(contributes);
		new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherLase"+typeId, last);
		return last;
	}
}
