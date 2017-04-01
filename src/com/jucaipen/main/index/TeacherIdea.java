package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.HotIdea;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取讲师观点 isIndex 0 首页 1 全部 2 根据讲师id查询
 */
public class TeacherIdea extends HttpServlet {
	private static final long serialVersionUID = 6097347924293457150L;
	private String result;
	private List<HotIdea> hotIdeas;

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
			String isIndex = request.getParameter("isIndex");
			String page = request.getParameter("page");
			if (StringUtil.isNotNull(isIndex)) {
				if (StringUtil.isInteger(isIndex)) {
					int index = Integer.parseInt(isIndex);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						if (index == 0) {
							// 首页信息
							result = initIndexIdea();
						} else if (index == 1) {
							// 全部信息
							result = initAllIdea(p);
						} else if (index == 2) {
							// 根据讲师id查询
							String teacherId = request
									.getParameter("teacherId");
							if (StringUtil.isNotNull(teacherId)
									&& StringUtil.isInteger(teacherId)) {
								int tId = Integer.parseInt(teacherId);
								result = initIdeaByTeacherId(tId, p);
							} else {
								result = JsonUtil.getRetMsg(4, "讲师id参数异常");
							}
						}
					} else {
						result = JsonUtil.getRetMsg(3, "page 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "isIndex 参数数字格式化异常");
				}

			} else {
				result = JsonUtil.getRetMsg(1, "isIndex参数不能为空");
			}

		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initIdeaByTeacherId(int tId, int page) {
		// 根据讲师id获取观点
		FamousTeacher teacher = FamousTeacherSer.findFamousTeacherById(tId);
		hotIdeas = HotIdeaServ.findIdeaByTeacherId(tId, page);
		for (HotIdea idea : hotIdeas) {
			if (teacher == null) {
				teacher = new FamousTeacher();
			}
			idea.setTeacherFace(teacher.getHeadFace());
			idea.setTeacherIsV(teacher.getIsV());
			idea.setTeacherLeavel(teacher.getLevel());
			idea.setTeacherName(teacher.getNickName());
		}
		return JsonUtil.getAllHotIdeaList(hotIdeas);
	}

	private String initIndexIdea() {
		// 获取首页热门观点信息 首页 精选
		hotIdeas = HotIdeaServ.findIndexIdea(3);
		return JsonUtil.getIndexList(hotIdeas);
	}

	private String initAllIdea(int page) {
		// 初始化全部讲师观点
		hotIdeas = HotIdeaServ.findAllHotIdea(page);
		for (HotIdea idea : hotIdeas) {
			int teacherId = idea.getTeacherId();
			FamousTeacher teacher = FamousTeacherSer
					.findFamousTeacherById(teacherId);
			if (teacher == null) {
				teacher = new FamousTeacher();
			}
			idea.setTeacherFace(teacher.getHeadFace());
			idea.setTeacherIsV(teacher.getIsV());
			idea.setTeacherLeavel(teacher.getLevel());
			idea.setTeacherName(teacher.getNickName());
		}
		return JsonUtil.getAllHotIdeaList(hotIdeas);
	}

}
