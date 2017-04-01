package com.jucaipen.base;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.FeedBack;
import com.jucaipen.service.FeedBackSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.MsgCode;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 *     
 *   意见反馈 deviceType 0 android   1 ios
 */
@SuppressWarnings("serial")
public class AddFeedBack extends HttpServlet {
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ip = request.getRemoteAddr();
		String trueName = request.getParameter("trueName");
		String contact = request.getParameter("contact");
		String userId = request.getParameter("userId");
		String bodys = request.getParameter("bodys");
		String deviceType = request.getParameter("deviceType");
		request.getHeaderNames();
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(trueName)) {
					if (StringUtil.isNotNull(contact)) {
						if (StringUtil.isNotNull(bodys)) {
							if (StringUtil.isNotNull(deviceType)
									&& StringUtil.isInteger(deviceType)) {
								int devType = Integer.parseInt(deviceType);
								result = inserAdvice(trueName, contact, uId,
										bodys, ip, devType);
							} else {
								result = MsgCode.CURRENT_VERSION == MsgCode.HISTORY_VISION_1 ? JsonUtil
										.getRetMsg(6, "deviceType 参数异常")
										: JsonUtil
												.getRetMsg(
														MsgCode.RET_FAIL_PARAMERROR_CODE,
														MsgCode.RET_FAIL_PARAMERROR);
							}
						} else {
							result = JsonUtil.getRetMsg(5, "反馈内容不能为空");
						}
					} else {
						result = JsonUtil.getRetMsg(4, "联系方式不能为空");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "真实姓名不能为空");
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

	private String inserAdvice(String trueName, String contact, int uId,
			String bodys, String ip, int devType) {
		FeedBack feedBack = new FeedBack();
		if (devType == 0) {
			feedBack.setBody("android问题反馈:" + bodys);
		} else {
			feedBack.setBody("ios问题反馈:" + bodys);
		}
		feedBack.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		feedBack.setIp(ip);
		feedBack.setMobileNum(contact);
		feedBack.setTrueName(trueName);
		feedBack.setUserId(uId);
		feedBack.setType(0);
		int isSuccess = FeedBackSer.insertFeedBack(feedBack);
		return isSuccess == 1 ? JsonUtil.getRetMsg(MsgCode.RET_SUCCESS_CODE,
				MsgCode.RET_SUCCESS) : JsonUtil.getRetMsg(
				MsgCode.RET_FAIL_CODE, MsgCode.RET_FAIL);
	}

}
