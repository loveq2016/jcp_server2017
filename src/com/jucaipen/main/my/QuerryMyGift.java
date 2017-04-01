package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Gifts;
import com.jucaipen.model.MyPresent;
import com.jucaipen.service.GiftsSer;
import com.jucaipen.service.MyPresentSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取我拥有的礼品
 */
public class QuerryMyGift extends HttpServlet {
	private static final long serialVersionUID = -300386616176322445L;
	private String result;
	private List<Gifts> gifts = new ArrayList<Gifts>();
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
			String userId = request.getParameter("userId");
			String page = request.getParameter("page");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						result = initMyGift(uId, p);
					} else {
						result = JsonUtil.getRetMsg(3, "page 参数异常");
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

	private String initMyGift(int uId, int page) {
		// 初始化我的礼品
		gifts.clear();
		List<MyPresent> presents = MyPresentSer.findPresentByUserId(uId, page);
		if(presents!=null){
			for (MyPresent present : presents) {
				int presentId = present.getPresentId();
				Gifts g = GiftsSer.findGiftById(presentId);
				present.setPrentName(g.getTitle());
				present.setPrentImage(g.getImageUrl());
			}
		}
		return JsonUtil.getMyPresentList(presents);

	}

}
