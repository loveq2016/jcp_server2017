package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ChargeOrder;
import com.jucaipen.model.Comment;
import com.jucaipen.model.Favorites;
import com.jucaipen.model.UserComm;
import com.jucaipen.service.ChargeOrderSer;
import com.jucaipen.service.CommentSer;
import com.jucaipen.service.FavoritesSer;
import com.jucaipen.service.UserCommSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         删除我的数据 typeId 0 我收藏的视频 1 我收藏的知识 2 视频评论 3 观点评论 4 资讯评论 5 充值记录
 * 
 */
public class RemoveMyData extends HttpServlet {
	private static final long serialVersionUID = 3097481023070681816L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String typeId = request.getParameter("typeId");
		String fkId = request.getParameter("fkId");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(typeId)
						&& StringUtil.isInteger(typeId)) {
					int type = Integer.parseInt(typeId);
					if (StringUtil.isNotNull(fkId)
							&& StringUtil.isInteger(fkId)) {
						int id = Integer.parseInt(fkId);
						result = delUserData(uId, type, id);
					} else {
						result = JsonUtil.getRetMsg(4, "fkId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "typeId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "用户还没登录");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String delUserData(int uId, int type, int id) {
		if (type == 0) {
			// 删除我收藏的视频
			Favorites favorites = FavoritesSer.findNewsIsCollect(uId, id, 1);
			if (favorites == null) {
				return JsonUtil.getRetMsg(1, "视频还没有收藏");
			}
			int isSuccess = FavoritesSer.cancelNews(uId, id, 1);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		} else if (type == 1) {
			// 删除我收藏的知识
			Favorites favorites = FavoritesSer.findNewsIsCollect(uId, id, 2);
			if (favorites == null) {
				return JsonUtil.getRetMsg(1, "知识还没有收藏");
			}
			int isSuccess = FavoritesSer.cancelNews(uId, id, 2);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		} else if (type == 2) {
			// 视频评论
			UserComm comms = UserCommSer.findCommentById(id);
			if (comms == null || comms.getUserId() != uId) {
				return JsonUtil.getRetMsg(1, "评论不存在");
			}
			int isSuccess = UserCommSer.cancelComm(id);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		} else if (type == 3) {
			// 观点评论
			Comment comment = CommentSer.findCommentById(id);
			if (comment == null || comment.getUserId() != uId) {
				return JsonUtil.getRetMsg(1, "评论不存在");
			}
			int isSuccess = CommentSer.cancelComm(id);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		} else if (type == 4) {
			// 资讯评论
			UserComm comms = UserCommSer.findCommentById(id);
			if (comms == null || comms.getUserId() != uId) {
				return JsonUtil.getRetMsg(1, "评论不存在");
			}
			int isSuccess = UserCommSer.cancelComm(id);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		} else if (type == 5) {
			// 充值记录
			ChargeOrder order = ChargeOrderSer.findOrderById(id);
			if (order == null || order.getUserId() != uId) {
				return JsonUtil.getRetMsg(1, "订单不存在");
			}
			int isSuccess = ChargeOrderSer.deleteOrder(id);
			return isSuccess == 1 ? JsonUtil.getRetMsg(0, "删除成功") : JsonUtil
					.getRetMsg(1, "删除失败");
		}
		return null;
	}

}
