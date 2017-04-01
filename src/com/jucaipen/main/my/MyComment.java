package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Comment;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.JcpNews;
import com.jucaipen.model.User;
import com.jucaipen.model.UserComm;
import com.jucaipen.model.Video;
import com.jucaipen.service.CommentSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.JcpNewsSer;
import com.jucaipen.service.UserCommSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取我的评论
 */
public class MyComment extends HttpServlet {
	private static final long serialVersionUID = -1247987697537247275L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String page = request.getParameter("page");
		String userId = request.getParameter("userId");
		String typeId = request.getParameter("typeId");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(typeId)
						&& StringUtil.isInteger(typeId)) {
					int type = Integer.parseInt(typeId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						result = initMyComments(uId, type, p);
					} else {
						result = JsonUtil.getRetMsg(4, "page 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "typeId 参数异常");
				}

			} else {
				result = JsonUtil.getRetMsg(3, "用户还没登录");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}

		out.println(result);
		out.flush();
		out.close();
	}

	private String initMyComments(int uId, int type, int p) {
		User user = UserServer.findBaseInfoById(uId);
		if (type == 0) {
			// 0 资讯
			List<UserComm> comms = UserCommSer.findComment(uId, 0, p);
			if (comms != null) {
				for (UserComm com : comms) {
					int id = com.getNovId();
					JcpNews news = JcpNewsSer.findNews(id);
					com.setFkName(news.getTitle());
					com.setFkDesc(news.getDesc());
					com.setUserName(user.getNickName());
					com.setUserFace(user.getFaceImage());
					com.setUserLeavel(user.getUserLeval());
				}
			}
			return JsonUtil.getNewsComments(comms);
		} else if (type == 1) {
			// 1 视频
			List<UserComm> comms = UserCommSer.findComment(uId, 1, p);
			if (comms != null) {
				for (UserComm com : comms) {
					int id = com.getNovId();
					Video video = VideoServer.findVideoById(id);
					com.setFkName(video.getTitle());
					com.setFkImage(video.getImages());
					com.setFkDesc(video.getDescript());
					com.setUserName(user.getNickName());
					com.setUserFace(user.getFaceImage());
					com.setUserLeavel(user.getUserLeval());
				}
			}
			return JsonUtil.getVideoComments(comms);
		} else if (type == 2) {
			// 评论类型（1观点日志，2文字直播）
			List<Comment> comms = CommentSer.findComment(uId, 1, p);
			if (comms != null) {
				for (Comment com : comms) {
					int id = com.getLogOrLiveId();
					HotIdea idea = HotIdeaServ.findIdeaById(id);
					com.setFkName(idea.getTitle());
					com.setFkDesc(idea.getBodys());
					com.setUserName(user.getNickName());
					com.setUserFace(user.getFaceImage());
					com.setUserLeavel(user.getUserLeval());
				}
			}
			return JsonUtil.getLogComments(comms);
		}
		return null;
	}

}
