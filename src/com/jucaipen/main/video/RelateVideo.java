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
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         获取(相关视频 typeId 0 选集 typeId 1 推荐视频 typeId 2
 */
public class RelateVideo extends HttpServlet {
	private static final long serialVersionUID = 4174777371136060413L;
	private String result;
	private int isPurch;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isIndex = request.getParameter("isIndex");
		String typeId = request.getParameter("typeId");
		String classId = request.getParameter("classId");
		String userId = request.getParameter("userId");
		if (StringUtil.isNotNull(typeId) && StringUtil.isInteger(typeId)) {
			int type = Integer.parseInt(typeId);
			if (StringUtil.isNotNull(classId) && StringUtil.isInteger(classId)) {
				int cId = Integer.parseInt(classId);
				if (StringUtil.isNotNull(isIndex)
						&& StringUtil.isInteger(isIndex)) {
					int index = Integer.parseInt(isIndex);
					result = initVideoList(cId, type, index, userId);
				} else {
					result = JsonUtil.getRetMsg(3, "isIndex 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "classId 参数异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "typeId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initVideoList(int cId, int type, int index, String userId) {
		// 是否购买 0 已购买 1 未购买 2 已过期
		isPurch = 1;
		int uId = 0;
		if (!StringUtil.isNotNull(userId) || !StringUtil.isInteger(userId)) {
			isPurch = 1;
		} else {
			uId = Integer.parseInt(userId);
			if (uId <= 0) {
				isPurch = 1;
			}
		}
		List<Video> videos;
		if (type == 0) {
			// 相关视频 ----classId
			videos = VideoServer.findVideoByClassIdLast(4, cId);
		} else if (type == 1) {
			// 选集 -----specialId
			videos = VideoServer.findVideoBySpecialId(cId);
		} else {
			// 推荐视频 -------- isJingXuan
			videos = VideoServer.findVideoByIsBestLast(4, 1);
		}
		if (videos != null) {
			for (Video video : videos) {
				// 是否为付费视频 0为免费视频，1为付费视频
				createVideo(video, uId);
			}
		}
		return JsonUtil.getContainVideoList(videos);
	}

	public void createVideo(Video video, int uId) {
		int specialId = video.getPecialId();
		int videoType = video.getVideoType();
		video.setCharge(videoType == 1);
		if (specialId > 0) {
			Special special = SpecialSer.findSpecialById(specialId);
			video.setCharge(special.getIsFree() == 2);
		}

		if (uId > 0 && specialId > 0) {
			MySpecial mySpecial = MySpecialSer.getIsMySpecial(uId, specialId);
			if (mySpecial != null) {
				if (TimeUtils.isLive(mySpecial.getStartDate(),
						mySpecial.getEndDate())) {
					isPurch = 0;
				} else {
					isPurch = 2;
				}
			} else {
				isPurch = 1;
			}
		}
/*
		if (uId > 0 && specialId <= 0) {
			MyVideo myVideo = MyVideoSer.findIsMyVideo(uId, video.getId());
			if (myVideo != null) {
				if (TimeUtils.isLive(myVideo.getStartDate(),
						myVideo.getEndDate())) {
					isPurch = 0;
				} else {
					isPurch = 2;
				}
			} else {
				isPurch = 1;
			}
		}
*/
		video.setIsPurch(isPurch);
	}
}
