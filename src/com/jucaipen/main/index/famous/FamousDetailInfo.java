package com.jucaipen.main.index.famous;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *    获取讲师详细信息
 */
@SuppressWarnings("serial")
public class FamousDetailInfo extends HttpServlet {
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String teacherId=request.getParameter("teacherId");
		if(StringUtil.isNotNull(teacherId)){
			if(StringUtil.isInteger(teacherId)){
				int tId=Integer.parseInt(teacherId);
				result=initDetail(tId);
			}else{
				result=JsonUtil.getRetMsg(2,"teacherId 参数数字格式化异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"teacherId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initDetail(int tId) {
		//初始化讲师详细信息
		Object cached = DataManager.getCached(Constant.TEACHER_CACHE, "teacherDetail"+tId);
		if(cached!=null){
			return cached.toString();
		}
		FamousTeacher teacher = FamousTeacherSer.findFamousTeacherById(tId);
		String detail = JsonUtil.getTeacherDetail(teacher);
		new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherDetail"+tId, detail);
		return detail;
	}
}
