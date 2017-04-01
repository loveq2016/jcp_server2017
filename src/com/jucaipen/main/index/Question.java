package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.AnswerSale;
import com.jucaipen.model.Ask;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.User;
import com.jucaipen.service.AnswerSaleSer;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator 问答 
 *                     type 0 全部 
 *                          1 根据讲师id获取：
 */
@SuppressWarnings("serial")
public class Question extends HttpServlet {
	private String result;
	private List<Ask> asks;
	private List<User> users = new ArrayList<User>();

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
			String type = request.getParameter("type");
			String page = request.getParameter("page");
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(page)) {
				if (StringUtil.isInteger(page)) {
					int p = Integer.parseInt(page);
					if (StringUtil.isNotNull(type)
							&& StringUtil.isInteger(type)) {
						if (StringUtil.isNotNull(userId)
								&& StringUtil.isInteger(userId)) {
							int uId = Integer.parseInt(userId);
							int t = Integer.parseInt(type);
							if (t == 0) {
								result = initAllQuestion(p, uId);
							} else {
								String teacherId = request
										.getParameter("teacherId");
								if (StringUtil.isNotNull(teacherId)
										&& StringUtil.isInteger(teacherId)) {
									int tId = Integer.parseInt(teacherId);
									result = initQuestionByTeacherId(tId, p,
											uId);
								} else {
									result = JsonUtil.getRetMsg(4,
											"teacherId 参数异常");
								}
							}
						} else {
							result = JsonUtil.getRetMsg(5, "userId 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(3, "type 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(1, "page 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "page 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initQuestionByTeacherId(int tId, int page, int uId) {
		// 根据讲师id获取提问信息
		int isPurch = 1;
		if (uId <= 0) {
			isPurch = 1;
		}

		users.clear();
		asks = AskSer.findAskByTeacherId(tId, page);
		for (Ask ask : asks) {
			int userId = ask.getUserId();
			User user = UserServer.findUserNikNameById(userId);
			if (user == null) {
				user = new User();
			}
			if (ask.getIsPay() == 1) {
				AnswerSale sale = AnswerSaleSer.findSaleByUserIdAndAskId(uId,
						ask.getId());
				if (sale != null || uId == userId) {
					isPurch = 0;
				} else {
					isPurch = 1;
				}
			} else {
				if (uId == userId) {
					isPurch = 0;
				} else {
					isPurch = 1;
				}
			}
			ask.setIsPurch(isPurch);
			users.add(user);
		}
		return JsonUtil.getAskList(asks, users, 0);

	}

	private String initAllQuestion(int page, int uId) {
		// 初始化所有问答信息
		users.clear();
		int isPurch = 1;
		if (uId <= 0) {
			isPurch = 1;
		}

		asks = AskSer.findAllAsk(page);
		for (Ask ask : asks) {
			int userId = ask.getUserId();
			User user = UserServer.findUserNikNameById(userId);
			if (user == null) {
				user = new User();
			}
			if (ask.getIsPay() == 1) {
				AnswerSale sale = AnswerSaleSer.findSaleByUserIdAndAskId(uId,
						ask.getId());
				if (sale != null || uId == userId) {
					isPurch = 0;
				} else {
					isPurch = 1;
				}
			} else {
				if (uId == userId) {
					isPurch = 0;
				} else {
					isPurch = 1;
				}
			}
			ask.setIsPurch(isPurch);
			users.add(user);
		}
		return JsonUtil.getAskList(asks, users, 1);
	}

}
