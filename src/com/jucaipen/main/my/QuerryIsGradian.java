package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Guardian;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  获取是否是讲师的守护者
 */
public class QuerryIsGradian extends HttpServlet {
	private static final long serialVersionUID = -6583898775815524969L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String teacherId=request.getParameter("teacherId");
		if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
			int uId=Integer.parseInt(userId);
			if(uId>0){
				if(StringUtil.isNotNull(teacherId)&&StringUtil.isInteger(teacherId)){
					int tId=Integer.parseInt(teacherId);
					result=initIsGuadian(uId,tId);
				}else{
					result=JsonUtil.getRetMsg(3,"teacherId 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(4,"用户还没登录");
			}
		}else{
			result=JsonUtil.getRetMsg(3,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initIsGuadian(int uId, int tId) {
		Guardian guardian = GuardianSer.findIsGuardian(tId, uId);
		return guardian!=null ? JsonUtil.getRetMsg(0, "已经成为该讲师的守护者") : JsonUtil.getRetMsg(1, "还没成为该讲师的守护者");
	}

}
