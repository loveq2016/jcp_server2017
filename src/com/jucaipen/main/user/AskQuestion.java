package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.Ask;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         咨询名师 同一个问题只能问三次，之后需要聚财币购买
 * 
 */
public class AskQuestion extends HttpServlet {
	private static final long serialVersionUID = -373303338941959681L;
	private int isSuccess;
	private String result;
	private String ip;
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
			String askType = request.getParameter("questionType");
			String userId = request.getParameter("userId");
			String askBodys = request.getParameter("askBodys");
			String teacherId = request.getParameter("teacherId");
			ip = request.getRemoteAddr();
			if (StringUtil.isInteger(userId)) {
				// 用户id格式正确
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isInteger(teacherId)) {
						// 讲师id数字格式化正确
						int tId = Integer.parseInt(teacherId);

						// 判断提问数量是否超出限制的提问数
						if (initMaxAskNum(uId, tId)) {
							if (tId > 0) {
								if (StringUtil.isInteger(askType)) {
									// 提问类型数字格式化正确
									if (StringUtil.isNotNull(askBodys)) {
										int type = Integer.parseInt(askType);
										if (type > 0) {
											result = createAskModel(uId, tId,
													type, askBodys);
										} else {
											result = JsonUtil.getRetMsg(6,
													"分类id找不到");
										}
									} else {
										result = JsonUtil.getRetMsg(5,
												"咨询内容不能为空");
									}
								} else {
									// 提问类型数字格式化异常
									result = JsonUtil.getRetMsg(2,
											"咨询分类参数数字格式化异常");
								}

							} else {
								result = JsonUtil.getRetMsg(7, "讲师id异常");
							}

						} else {
							result = JsonUtil.getRetMsg(8, "您的提问数已经超出限制");
						}

					} else {
						// 讲师id数字格式化异常
						result = JsonUtil.getRetMsg(3, "讲师id数字格式化异常");
					}
				} else {
					result = JsonUtil.getRetMsg(5, "用户还没登录");
				}

			} else {
				// 用户id数字格式化异常
				result = JsonUtil.getRetMsg(4, "用户id数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private boolean initMaxAskNum(int uId, int tId) {
		// 获取最大提问数和当前已经提问的数量
		int maxNum = 0;
		if (tId == -1) {
			SiteConfig config = SiteConfigSer.findSiteConfig();
			maxNum = config.getAskNum();
		} else {
			maxNum = FamousTeacherSer.findMaxAsk(tId);
		}
		List<Ask> asks = AskSer.findAskByUidAndTeacherId(uId, tId);
		return asks.size() < maxNum;

	}

	private String createAskModel(int uId, int tId, int type, String askBodys) {
		Ask ask = new Ask();
		ask.setUserId(uId);
		ask.setTeacherId(tId);
		ask.setClassId(type);
		ask.setAskBody(askBodys);
		ask.setAskDate(sdf.format(new Date()));
		ask.setHits(0);
		ask.setIp(ip);
		ask.setIsPay(0);
		ask.setIsReply(1);
		ask.setParentId(0);
		ask.setAskState(1);
		ask.setAskFrom(2);
		ask.setJucaiBills(0);
		ask.setIsReturnJcb(0);
		isSuccess = AskSer.insertAsk(ask);
		if (isSuccess == 1&&tId>0) {
			FamousTeacher teacher = FamousTeacherSer.findTeacherBaseInfo(tId);
			FamousTeacherSer.updateAskNum(teacher.getAskNum() + 1, tId);
		}
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "提问信息提交成功") : JsonUtil
				.getRetMsg(1, "提问信息提交失败");
	}

}
