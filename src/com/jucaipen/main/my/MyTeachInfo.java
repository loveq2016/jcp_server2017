package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Bank;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.service.BankSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *   获取我的讲师信息
 */
public class MyTeachInfo extends HttpServlet {
	private String result;
	private static final long serialVersionUID = 1432461456369747536L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String teacherId=request.getParameter("teacherId");
		if(StringUtil.isNotNull(teacherId)&&StringUtil.isInteger(teacherId)){
			int tId=Integer.parseInt(teacherId);
			result = getMyTeacherInfo(tId);
		}else{
			result=JsonUtil.getRetMsg(1,"teacherId  参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String  getMyTeacherInfo(int tId) {
		FamousTeacher teacher=FamousTeacherSer.findFamousTeacherById(tId);
		if(teacher!=null){
			Bank bank=BankSer.findBankById(teacher.getBankId());
			if(bank!=null){
				teacher.setBankName(bank.getBankName());
			}
		}
		
		return JsonUtil.getMyTeachInfo(teacher);
	}

}
