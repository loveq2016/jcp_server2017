package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.MySpecial;
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
 *         获取我的专辑下的视频
 * 
 */
public class SpecialVide extends HttpServlet {
	private static final long serialVersionUID = -8557171212499676905L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String specialId = request.getParameter("specialId");
		String page = request.getParameter("page");
		if (StringUtil.isNotNull(specialId) && StringUtil.isInteger(specialId)) {
			int sId = Integer.parseInt(specialId);
			if (StringUtil.isNotNull(page) && StringUtil.isInteger(page)) {
				int p = Integer.parseInt(page);
				if (StringUtil.isNotNull(userId)
						&& StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					result = initVideoList(sId, p, uId);
				} else {
					result = JsonUtil.getRetMsg(3, "userId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "page 参数异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "specialId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initVideoList(int specialId, int page, int uId) {
		MySpecial mySpecial = MySpecialSer.getIsMySpecial(uId, specialId);
		if (mySpecial == null) {
			return JsonUtil.getRetMsg(5, "该专辑还没购买");
		}

		if (!TimeUtils.isLive(mySpecial.getStartDate(), mySpecial.getEndDate())) {
			return JsonUtil.getRetMsg(6, "该专辑已经过期，请进行续费");
		}

		Special special = SpecialSer.findSpecialById(specialId);

		List<Video> videos = VideoServer.findSpecialVideo(page, specialId);
		if (videos != null) {
			for (Video video : videos) {
				// 是否为付费视频 0为免费视频，1为付费视频
				video.setCharge(special.getIsFree() == 2);
			}
		}
		return JsonUtil.getMySpecialVideoList(videos);
	}
}
