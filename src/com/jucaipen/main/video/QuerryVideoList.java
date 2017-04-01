package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.Special;
import com.jucaipen.model.Video;
import com.jucaipen.model.VideoClass;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.MyVideoSer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.VideoClassSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator 获取视频列表信息
 */
public class QuerryVideoList extends HttpServlet {
	private static final long serialVersionUID = -2514081515250219027L;
	private String result;
	private StringBuffer cIdArray;
	private int isPurch;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String classId = request.getParameter("classId");
		String typeId = request.getParameter("typeId");
		String teacherId = request.getParameter("teacherId");
		String page = request.getParameter("page");
		String userId=request.getParameter("userId");
		if (StringUtil.isNotNull(classId) && StringUtil.isInteger(classId)) {
			int cId = Integer.parseInt(classId);
			if (StringUtil.isNotNull(typeId) && StringUtil.isInteger(typeId)) {
				int type = Integer.parseInt(typeId);
				if (StringUtil.isNotNull(teacherId)
						&& StringUtil.isInteger(teacherId)) {
					int tId = Integer.parseInt(teacherId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						result = initVideoList(type, tId, cId, p,userId);
					} else {
						result = JsonUtil.getRetMsg(4, "page 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "teacherId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "typeId 参数异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "classId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}


	private String initVideoList(int type, int tId, int cId, int p,String userId) {
		// cId 查询时必须存在 大于0
		isPurch=1;     //0 已购买 1 未购买 2 已过期
		int uId = 0;
		//cId >= 0&&type > 0 && tId > 0 
		//cId >= 0 && type <= 0 && tId <= 0
		//cId >= 0 && type > 0 && tId <= 0
		//cId >= 0 && type <= 0 && tId > 0
		if(!StringUtil.isNotNull(userId)||!StringUtil.isInteger(userId)){
			isPurch=1;
		}else{
			uId=Integer.parseInt(userId);
			if(uId<=0){
				isPurch=1;
			}
		}
		
		
		
		List<Video> videos;
		if (cId < 0) {
			return JsonUtil.getRetMsg(1, "分类id必须大于0");
		}
		cIdArray = new StringBuffer();
		if (cId >= 0&&type > 0 && tId > 0 ) {
			// 各种分类 非全部
			List<VideoClass> vcs = VideoClassSer.findClassByPid(cId);
			StringBuffer vs = getVideoClass(vcs);
			if (vs != null && vs.length() > 0) {
				if (vs.toString().endsWith(",")) {
					vs.replace(vs.length() - 1, vs.length(), "");
				}
				videos = VideoServer.findVideoByTypeAndClassIdAndTeacherId(
						type, vs.toString(), tId, p);
			} else {
				videos = VideoServer.findVideoByTypeAndClassIdAndTeacherId(
						type, cId, tId, p);
			}
			if (videos != null) {
				for (Video video : videos) {
					// 是否为付费视频 0为免费视频，1为付费视频
					createVideo(video, uId);
				}
			}
			return JsonUtil.getContainVideoList(videos);
		}else
		// cId>0
		if (cId >= 0 && type <= 0 && tId <= 0) {
			List<VideoClass> vcs = VideoClassSer.findClassByPid(cId);
			StringBuffer vs = getVideoClass(vcs);
			if (vs != null && vs.length() > 0) {
				if (vs.toString().endsWith(",")) {
					vs.replace(vs.length() - 1, vs.length(), "");
				}
				videos = VideoServer.findVideoByClassId(vs.toString(), p);
			} else {
				videos = VideoServer.findVideoByClassId(cId, p);
			}

			if (videos != null) {
				for (Video video : videos) {
					// 是否为付费视频 0为免费视频，1为付费视频
					createVideo(video, uId);
				}
			}

			return JsonUtil.getContainVideoList(videos);
		}else

		if (cId >= 0 && type > 0 && tId <= 0) {
			List<VideoClass> vcs = VideoClassSer.findClassByPid(cId);
			StringBuffer vs = getVideoClass(vcs);
			if (vs != null && vs.length() > 0) {
				if (vs.toString().endsWith(",")) {
					vs.replace(vs.length() - 1, vs.length(), "");
				}
				videos = VideoServer.findVideoByTypeAndClassId(type,
						vs.toString(), p);
			} else {
				videos = VideoServer.findVideoByTypeAndClassId(type, cId, p);
			}
			if (videos != null) {
				for (Video video : videos) {
					// 是否为付费视频 0为免费视频，1为付费视频
					createVideo(video, uId);
				}
			}

			return JsonUtil.getContainVideoList(videos);
		}else

		if (cId >= 0 && type <= 0 && tId > 0) {
			List<VideoClass> vcs = VideoClassSer.findClassByPid(cId);
			StringBuffer vs = getVideoClass(vcs);
			if (vs != null && vs.length() > 0) {
				if (vs.toString().endsWith(",")) {
					vs.replace(vs.length() - 1, vs.length(), "");
				}
				videos = VideoServer.findVideoByTeacherIdAndClassId(tId,
						vs.toString(), p);
			} else {
				videos = VideoServer
						.findVideoByTeacherIdAndClassId(tId, cId, p);
			}

			if (videos != null) {
				for (Video video : videos) {
					createVideo(video, uId);
				}
			}
			return JsonUtil.getContainVideoList(videos);
		}
		
		return null;
	}
	
	
	
	public void createVideo(Video video,int uId){
		// 是否为付费视频 0为免费视频，1为付费视频
		int specialId = video.getPecialId();
		int videoType = video.getVideoType();
		video.setCharge(videoType == 1);
		//0 已购买 1 未购买 2 已过期
		if(uId>0&&specialId>0){
			MySpecial mySpecial=MySpecialSer.getIsMySpecial(uId, specialId);
			if(mySpecial!=null){
				if(TimeUtils.isLive(mySpecial.getStartDate(), mySpecial.getEndDate())){
					isPurch=0;
				}else{
					isPurch=2;
				}
			}else{
				isPurch=1;
			}
			
		}
		
		if(uId>0&&specialId<=0){
			MyVideo myVideo=MyVideoSer.findIsMyVideo(uId, video.getId());
			if(myVideo!=null){
				if(TimeUtils.isLive(myVideo.getStartDate(), myVideo.getEndDate())){
					isPurch=0;
				}else{
					isPurch=2;
				}
			}else{
				isPurch=1;
			}
		}
		if (specialId > 0) {
			Special special = SpecialSer.findSpecialById(specialId);
			video.setCharge(special.getIsFree() == 2);
		}
		video.setIsPurch(isPurch);
	}

	public StringBuffer getVideoClass(List<VideoClass> vcs) {
		// 1，8，9
		// 1，1，8，9
		if (vcs != null) {
			for (VideoClass vc : vcs) {
				cIdArray.append(vc.getId());
				cIdArray.append(",");
				List<VideoClass> vs = VideoClassSer.findClassByPid(vc.getId());
				if (vs != null) {
					getVideoClass(vs);
				}
			}
		}
		return cIdArray;
	}

}
