package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞  讲师总榜单
 */
public class LastTeacherList extends HttpServlet {
	private static final long serialVersionUID = 2779291546693682575L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
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
		List<Contribute> contributes;
		if (typeId == 0) {
			contributes=ContributeSer.findContributeGroupByTid("year");
		} else if (typeId == 1) {
			contributes=ContributeSer.findContributeGroupByTid("month");
		} else {
			contributes=ContributeSer.findContributeGroupByTid("day");
		}
		for (Contribute contribute : contributes) {
			int teacherId = contribute.getTeacherId();
			FamousTeacher teacher = FamousTeacherSer
					.findTeacherBaseInfo(teacherId);
			VideoLive videoLive=VideoLiveServer.findLiveBytId(teacherId);
			contribute.setFromName(teacher.getNickName());
			contribute.setFromFace(teacher.getHeadFace());
			contribute.setLeavel(teacher.getLevel());
			contribute.setIntroduce(teacher.getIntroduce());
			if(videoLive!=null){
				contribute.setIsEnd(videoLive.getIsEnd());
			}
		}
		return JsonUtil.getLastTeacherLast(contributes);
	}
}
