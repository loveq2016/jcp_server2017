package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Favorites;
import com.jucaipen.service.FavoritesSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         收藏、取消收藏新闻 type 0 视频 1 知识
 * 
 *         opType ----0 收藏 -----1 取消收藏
 * 
 */
public class CollectNews extends HttpServlet {
	private static final long serialVersionUID = -7615999442914686319L;
	private String result;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String typeId = request.getParameter("type");
			String userId = request.getParameter("userId");
			String newsId = request.getParameter("newsId");
			String opType = request.getParameter("opType");
			if (StringUtil.isInteger(typeId)) {
				int type = Integer.parseInt(typeId);
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isInteger(newsId)) {
							int nId = Integer.parseInt(newsId);
							if (StringUtil.isNotNull(opType)
									&& StringUtil.isInteger(opType)) {
								int opId = Integer.parseInt(opType);
								result = initIdea(opId, uId, nId, type);
							} else {
								result = JsonUtil.getRetMsg(7, "opType 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(5, "用户id参数格式化异常");
						}
					} else {
						result = JsonUtil.getRetMsg(6, "当前用户还没登录");
					}

				} else {
					result = JsonUtil.getRetMsg(4, "用户id参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(3, "类型参数数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initIdea(int opId, int uId, int nId, int type) {
		if (type == 0) {
			// 视频
			boolean isCollect = newsIsCollect(uId, nId, 1);
			if (opId == 0) {
				// 收藏
				if (isCollect) {
					return JsonUtil.getRetMsg(1, "视频已经收藏");
				} else {
					return insertNewsCollect(uId, nId, 1);
				}
			} else {
				// 取消
				if (!isCollect) {
					return JsonUtil.getRetMsg(1, "视频还没收藏");
				} else {
					return cancellNewsCollect(uId, nId, 1);
				}
			}
		} else {
			// 知识
			boolean isCollect = newsIsCollect(uId, nId, 2);
			if (opId == 0) {
				// 收藏
				if (isCollect) {
					return JsonUtil.getRetMsg(1, "知识已经收藏");
				} else {
					return insertNewsCollect(uId, nId, 2);
				}
			} else {
				// 取消
				if (!isCollect) {
					return JsonUtil.getRetMsg(1, "知识还没收藏");
				} else {
					return cancellNewsCollect(uId, nId, 2);
				}
			}
		}
	}

	private String cancellNewsCollect(int uId, int nId, int state) {
		// 取消收藏
		int isSuccess = FavoritesSer.cancelNews(uId, nId, state);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "取消成功") : JsonUtil
				.getRetMsg(1, "取消失败");
	}

	private boolean newsIsCollect(int uId, int nId, int type) {
		// 判断是否收藏
		Favorites favour = FavoritesSer.findNewsIsCollect(uId, nId, type);
		return favour != null ? true : false;

	}

	private String insertNewsCollect(int uId, int nId, int type) {
		// 新闻收藏
		Favorites newsFavorites = new Favorites();
		newsFavorites.setFk_Id(nId);
		newsFavorites.setuId(uId);
		newsFavorites.setType(type);
		newsFavorites.setDate(sdf.format(new Date()));
		int isSuccess = FavoritesSer.insertNews(newsFavorites);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "收藏成功") : JsonUtil
				.getRetMsg(1, "收藏失败");
	}

}
