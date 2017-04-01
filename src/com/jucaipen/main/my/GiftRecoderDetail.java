package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.MyGifts;
import com.jucaipen.model.User;
import com.jucaipen.service.MyGiftsSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *    获取赠送礼品详细信息
 */
public class GiftRecoderDetail extends HttpServlet {
	private static final long serialVersionUID = -7658211294843728568L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String giftId = request.getParameter("giftId");
		String page = request.getParameter("page");
		String typeId = request.getParameter("typeId");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(giftId)
						&& StringUtil.isInteger(giftId)) {
					int pId = Integer.parseInt(giftId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						if (StringUtil.isNotNull(typeId)
								&& StringUtil.isInteger(typeId)) {
							int type = Integer.parseInt(typeId);
							result = initSendRecofderDetail(uId, pId, type, p);
						}else{
							result=JsonUtil.getRetMsg(5,"typeId 参数异常");
						}
					}else{
						result=JsonUtil.getRetMsg(4, "page 参数异常");
					}
				}else{
					result=JsonUtil.getRetMsg(3, "giftId 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(2, "用户没有登录");
			}
		}else{
			result=JsonUtil.getRetMsg(1, "userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initSendRecofderDetail(int uId, int pId, int type, int p) {
		List<MyGifts> gifts;
		if (type == 0) {
			// 我送出的礼品
			gifts = MyGiftsSer.findMyGiftDetailBySenderId(uId, pId, p);
		} else {
			// 我收到的礼品
			gifts = MyGiftsSer.findMyGiftDetailByReceiverId(uId, pId, p);
		}
		if (gifts != null) {
			for (MyGifts gif : gifts) {
				if (type == 0) {
					int receiverId = gif.getReceiverId();
					User user = UserServer.findBaseInfoById(receiverId);
					gif.setReceiverName(user.getUserName());
				} else {
					int senderId = gif.getSenderId();
					User user = UserServer.findBaseInfoById(senderId);
					gif.setSendName(user.getUserName());
				}

			}
		}
		return JsonUtil.getSendGiftDetail(gifts);
	}

}
