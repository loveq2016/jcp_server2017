package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.LevBean;
import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.Video;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         获取视频资源 以及购买状态 videoUrl isPurch
 */
public class VideoResource extends HttpServlet {
	private static final long serialVersionUID = 2797128254989094436L;
	private String result;
	private static Map<String, String> param = new HashMap<String, String>();
	private static final String LEVT_PATH = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=VideoDetail";

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
			String videoId = request.getParameter("videoId");
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (StringUtil.isNotNull(videoId)) {
						if (StringUtil.isInteger(videoId)) {
							int id = Integer.parseInt(videoId);
							if (uId > 0) {
								result = initVideoResource(id, uId);
							} else {
								result = JsonUtil.getRetMsg(4, "用户还没登录");
							}
						} else {
							result = JsonUtil.getRetMsg(1, "参数videoId 数字格式化异常");
						}
					} else {
						result = JsonUtil.getRetMsg(2, "参数videoId 不能为空");
					}
				} else {
					result = JsonUtil.getRetMsg(4, "参数userId 数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(3, "参数 userId 不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initVideoResource(int id, int uId) {
		// 获取视频资源
		Video video = VideoServer.findVideoResourceById(id);
		/*MyVideo myVideo = MyVideoSer.findIsMyVideo(uId, id);
		int specialId = video.getPecialId();
		if (myVideo != null) {
			if (TimeUtils.isLive(myVideo.getStartDate(), myVideo.getEndDate())) {
				video.setIsPurch(0);
			} else {
				video.setIsPurch(2);
			}
		} else {
			video.setIsPurch(1);
		}*/
		/*if (specialId > 0) {
			MySpecial mySpecial = MySpecialSer.getIsMySpecial(uId, specialId);
			if (mySpecial != null) {
				if (TimeUtils.isLive(mySpecial.getStartDate(),
						mySpecial.getEndDate())) {
					video.setIsPurch(0);
				} else {
					video.setIsPurch(2);
				}
			}
		}
*/
		if (video.getVideoUrl().contains("script")) {
			// 乐视
			video.setPlayType(2);
			LevBean ben = getLevtVideoUrl(id);
			video.setVid(ben.getVid());
			video.setUid(ben.getUid());
		} else {
			// 趣看
			video.setPlayType(1);
		}
		return JsonUtil.getLiveUrl(video);
	}

	private LevBean getLevtVideoUrl(int id) {
		param.clear();
		param.put("id", id + "");
		String res = LoginUtil.sendHttpPost(LEVT_PATH, param);
		JSONObject object = new JSONObject(res);
		boolean Result = object.getBoolean("Result");
		// String Msg=object.getString("Msg");
		if (Result) {
			String vu = object.getString("vu");
			String uu = object.getString("uu");
			LevBean lev = new LevBean();
			lev.setUid(uu);
			lev.setVid(vu);
			return lev;
		}
		return null;
	}

}