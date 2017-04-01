package com.jucaipen.base;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.utils.EmailUtils;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator 发送邮件
 */
@SuppressWarnings("serial")
public class SendEmail extends HttpServlet {
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			String userId = request.getParameter("userId");
			String email = request.getParameter("email");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(email) && StringUtil.isMail(email)) {
							String code = RandomUtils.getRandomData(4);
							result = EmailUtils.send(email, "<h1>点击下方的链接，绑定聚财盆账号</h1>"+"<h3><a href='http://baidu.com?code="+code+"'>"+"http://baidu.com?code="+code+"</a></h3>",
									"聚财盆绑定邮箱激活邮件");
						} else {
							result = JsonUtil.getRetMsg(4, "email　参数异常");
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
		}else{
			result=StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

}
