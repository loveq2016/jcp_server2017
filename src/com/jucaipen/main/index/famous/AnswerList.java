package com.jucaipen.main.index.famous;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jucaipen.model.Answer;
import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.User;
import com.jucaipen.service.AnswerSer;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         type 1 获取提问、追问 2 获取回答
 */
@SuppressWarnings("serial")
public class AnswerList extends HttpServlet {
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId = request.getParameter("typeId");
		String userId = request.getParameter("userId");
		String fkId = request.getParameter("fkId");
		String askUserId = request.getParameter("askUserId");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (StringUtil.isNotNull(askUserId)
					&& StringUtil.isInteger(askUserId)) {
				int askUid = Integer.parseInt(askUserId);
				if (StringUtil.isNotNull(fkId) && StringUtil.isInteger(fkId)) {
					int fId = Integer.parseInt(fkId);
					if (StringUtil.isNotNull(typeId)
							&& StringUtil.isInteger(typeId)) {
						int type = Integer.parseInt(typeId);
						result = initQuestion(uId, fId, askUid, type);
					} else {
						result = JsonUtil.getRetMsg(4, "typeId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "askId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "askUserId 参数异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initQuestion(int uId, int fId, int askUid, int type) {
		if (type == 1) {
			// 提问 追问
			List<Ask> asks;
			if (uId == askUid) {
				// 获取所有提问 追问
				asks = AskSer.findAskByParentId(fId);
			} else {
				// 获取主提问
				asks = AskSer.findAskByParentId(fId);
			}
			if (asks != null) {
				for (Ask ask : asks) {
					int userId = ask.getUserId();
					User user = UserServer.findBaseInfoById(userId);
					if (user == null) {
						user = new User();
					}
					ask.setUserFace(user.getFaceImage());
					ask.setUserName(user.getNickName());
					ask.setUserLeavel(user.getUserLeval());
				}
			}
			return JsonUtil.getAskDetailList(asks);
		} else {
			// 回答
			List<Answer> answers;
			if (uId == askUid) {
				// 获取所有回答
				answers = AnswerSer.findAnswerByAskId(fId);
			} else {
				// 获取主回复
				answers = AnswerSer.findAnswerByAskId(fId);
			}

			if (answers != null) {
				for (Answer answer : answers) {
					int tId = answer.getTeacherId();
					FamousTeacher teacher = FamousTeacherSer
							.findFamousTeacherById(tId);
					if (teacher == null) {
						teacher = new FamousTeacher();
					}
					answer.setTeacherFace(teacher.getHeadFace());
					answer.setTeacherLeavel(teacher.getLevel());
					answer.setTeacherName(teacher.getNickName());
				}
			}

			return JsonUtil.getAnswerDetailList(answers);
		}
	}

	// 主回复
	public void recleAnswer(List<Answer> answers) {
		for (Answer answer : answers) {
			// 追问主回复
			List<Ask> asks = AskSer.findAskByParentId(answer.getId());
			JsonArray reAsk = new JsonArray();
			if (asks != null) {
				for (Ask ask : asks) {
					JsonObject ask1 = new JsonObject();
					String askBody = ask.getAskBody();
					ask1.addProperty("ask1Body", askBody);

					if (ask.getIsReply() == 2) {
						// 回复追问
						JsonArray reAns = new JsonArray();
						List<Answer> ans = AnswerSer.findAnswerByAskId(ask
								.getId());
						if (ans != null) {
							for (Answer a : ans) {
								JsonArray sAsk = new JsonArray();
								List<Ask> as = AskSer.findAskByParentId(a
										.getId());
								if (as != null) {
									for (Ask ak : as) {
										JsonArray sA = new JsonArray();
										List<Answer> aws = AnswerSer
												.findAnswerByAskId(ak.getId());
									}
								}
							}
						}
						recleAnswer(ans);
					}
				}
			}
		}

	}

}
