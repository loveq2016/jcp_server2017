package com.jucaipen.main.index.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Knowledge;
import com.jucaipen.model.KnowledgeClass;
import com.jucaipen.service.KnowledgeClassSer;
import com.jucaipen.service.KnowledgetSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *
 *  获取知识列表
 */
public class QuerryKnowList extends HttpServlet {
	private static final long serialVersionUID = -5596448669495264585L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId=request.getParameter("typeId");
		String page=request.getParameter("page");
		if(StringUtil.isNotNull(typeId)){
			if(StringUtil.isInteger(typeId)){
				int type=Integer.parseInt(typeId);
				if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
					int p=Integer.parseInt(page);
					result=initKnowList(type,p);
				}else{
					result=JsonUtil.getRetMsg(3,"page 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(2,"typeId 参数数字格式化异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"typeId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initKnowList(int type, int page) {
		//初始化知识列表
		KnowledgeClass kc=KnowledgeClassSer.findKnowledgeById(type);
		List<Knowledge> knows = KnowledgetSer.findKnowledgeByClassId(type, page);
		return JsonUtil.getKnowList(knows,kc);
	}

}
