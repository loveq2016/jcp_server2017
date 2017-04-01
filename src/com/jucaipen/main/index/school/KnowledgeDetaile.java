package com.jucaipen.main.index.school;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Knowledge;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.service.KnowledgetSer;
import com.jucaipen.service.ResourceFromServer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *
 *   获取知识详细信息
 */
public class KnowledgeDetaile extends HttpServlet {
	private static final long serialVersionUID = 6627453816722910025L;
	private String result;      
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String knowId=request.getParameter("knowId");
		if(StringUtil.isNotNull(knowId)){
			if(StringUtil.isInteger(knowId)){
				int kId=Integer.parseInt(knowId);
					result=initKnowDetails(kId);
			}else{
				result=JsonUtil.getRetMsg(2,"knowId 参数数字格式化异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1, "knowId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initKnowDetails(int kId) {
		//初始化知识详细信息
		Knowledge knowledge=KnowledgetSer.findKnowledgeById(kId);
		if(knowledge!=null){
			//获取来源
			int fromId=knowledge.getFromId();
			String from = ResourceFromServer.getRSources(fromId);
			knowledge.setFrom(from);
		}
		initHits(kId,knowledge.getHits(),knowledge.getXnHits());
		return JsonUtil.getKnowDetails(knowledge);
	}

	private void initHits(int kId, int hits, int xnHits) {
		SiteConfig config = SiteConfigSer.findSiteConfig();
		KnowledgetSer.addHits(kId, hits+1, xnHits+config.getNewsMom());
		
	}
}
