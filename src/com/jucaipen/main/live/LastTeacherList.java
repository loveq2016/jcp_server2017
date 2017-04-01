package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Rebate;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.RebateSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞
 */
public class LastTeacherList extends HttpServlet {
	private static final long serialVersionUID = 2779291546693682575L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if (StringUtil.isNotNull(type) && StringUtil.isInteger(type)) {
			int typeId = Integer.parseInt(type);
			result = getTeacherLastList(typeId);
		}
		out.println(result);
		out.flush();
		out.close();
	}
	/**
	 * @param typeId
	 * @param p
	 * @return 获取最近的讲师排行榜
	 */
	private String getTeacherLastList(int typeId) {
		List<Rebate> rebates;
		if (typeId == 0) {
			rebates = RebateSer.findLastTeacherLast("year");
		} else if (typeId == 1) {
			rebates = RebateSer.findLastTeacherLast("month");
		} else {
			rebates = RebateSer.findLastTeacherLast("day");
		}
		for (Rebate rebate : rebates) {
			int teacherId = rebate.getTeacherId();
			FamousTeacher teacher = FamousTeacherSer
					.findTeacherBaseInfo(teacherId);
			rebate.setFromName(teacher.getNickName());
			rebate.setFromFace(teacher.getHeadFace());
			rebate.setLeavel(teacher.getLevel());
			rebate.setIntroduce(teacher.getIntroduce());
			rebate.setFromId(teacherId);
		}
		return JsonUtil.getLastTeacherLast(rebates);
	}
}
