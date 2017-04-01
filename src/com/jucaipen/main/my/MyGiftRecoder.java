package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Gifts;
import com.jucaipen.model.MyGifts;
import com.jucaipen.service.GiftsSer;
import com.jucaipen.service.MyGiftsSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取礼品记录 type 0 我收到的 1 我送出的
 */
public class MyGiftRecoder extends HttpServlet {
	private static final long serialVersionUID = 3319864705221420762L;
	private String result;
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
			String userId = request.getParameter("userId");
			String page = request.getParameter("page");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
							if (StringUtil.isNotNull(type)
									&& StringUtil.isInteger(type)) {
								int t = Integer.parseInt(type);
								result = initMyGiftRecoder(t, uId, p);
							} else {
								result = JsonUtil.getRetMsg(5, "type 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "page 参数异常");
						}

					} else {
						result = JsonUtil.getRetMsg(3, "该用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}

			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @param t
	 * @param uId
	 * @param p
	 * @return 初始化我的礼品记录
	 */
	private String initMyGiftRecoder(int t, int uId, int p) {
		List<MyGifts> gifts;
		if (t == 0) {
			// 我送出的礼品
			gifts = MyGiftsSer.findMyGiftBySenderId(uId, p);
		} else {
			// 我收到的礼品
			gifts = MyGiftsSer.findMyGiftByReceiverId(uId, p);
		}
		if(gifts!=null){
			for (MyGifts g : gifts) {
				int gId = g.getGiftId();
				Gifts gs = GiftsSer.findGiftById(gId);
				g.setGiftName(gs.getTitle());
				g.setGiftImage(gs.getImageUrl());
			}
		}
		return JsonUtil.getGiftRecoder(gifts);
	}

}
