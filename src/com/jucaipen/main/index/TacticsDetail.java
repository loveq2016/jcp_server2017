package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Tactics;
import com.jucaipen.model.TacticsDetails;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.TacticsDetailSer;
import com.jucaipen.service.TacticsSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

public class TacticsDetail extends HttpServlet {
	private String result;
	private static final long serialVersionUID = 3987235579047828066L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String detailId=request.getParameter("detailId");
		if(StringUtil.isNotNull(detailId)&&StringUtil.isInteger(detailId)){
			int id=Integer.parseInt(detailId);
			result=initDetail(id);
		}else{
			result=JsonUtil.getRetMsg(1,"detailId ≤Œ ˝“Ï≥£");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initDetail(int id) {
		TacticsDetails detail = TacticsDetailSer.findDetailsById(id);
		int fkId=detail.getFkId();
		Tactics tactics = TacticsSer.findTacticsById(fkId);
		int teacherId=tactics.getTeacherId();
		FamousTeacher teacher = FamousTeacherSer.findTeacherBaseInfo(teacherId);
		return JsonUtil.getDetailInfo(teacher,tactics,detail);
	}

}
