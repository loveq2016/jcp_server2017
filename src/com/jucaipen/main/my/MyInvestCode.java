package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.User;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *
 *  获取我的邀请码
 */
public class MyInvestCode extends HttpServlet {
	private static final long serialVersionUID = 7176672891083433139L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
			int uId=Integer.parseInt(userId);
			if(uId>0){
				result=getMyInvestCode(uId);
			}else{
				result=JsonUtil.getRetMsg(1,"用户没没登录");
			}
		}else{
			result=JsonUtil.getRetMsg(2,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String getMyInvestCode(int uId) {
		User user=UserServer.findBaseInfoById(uId);
		SiteConfig config=SiteConfigSer.findSiteConfig();
		int integeral=config.getRegIntegeral();
		return JsonUtil.getInvestCode(user,integeral);
	}

}
