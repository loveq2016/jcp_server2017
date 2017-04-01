package com.jucaipen.main.index.famous;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Fans;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.FansSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *  获取讲师基本信息    baseinfo
 */
@SuppressWarnings("serial")
public class QuerryBaseTeacherInfo extends HttpServlet {
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String teacherId=request.getParameter("teacherId");
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(teacherId)){
			if(StringUtil.isInteger(teacherId)){
				int tId=Integer.parseInt(teacherId);
				if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
					int uId=Integer.parseInt(userId);
					result=initTeacherBaseInfo(tId,uId);
				}else{
					result=JsonUtil.getRetMsg(1,"userId 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(1,"teacherId 参数数字格式化异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"teacherId 不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initTeacherBaseInfo(int tId,int uId) {
		FamousTeacher teacher=FamousTeacherSer.findFamousTeacherById(tId);
		Fans fans = FansSer.findIsFans(uId, tId);
		teacher.setAttention(fans!=null);
		return JsonUtil.getTeacherBaseInfo(teacher);
	}

}
