package com.jucaipen.main.news;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.JcpNews;
import com.jucaipen.service.JcpNewsSer;
import com.jucaipen.service.ResourceFromServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取相关新闻
 */
public class RelateNews extends HttpServlet {
	private static final long serialVersionUID = 2672914910809261749L;
	private String result;
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
			String newsId = request.getParameter("id");
			if (StringUtil.isInteger(newsId)) {
				int id = Integer.parseInt(newsId);
				result=initReleData(id);
			} else {
				result = JsonUtil.getRetMsg(1, "新闻id参数数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initReleData(int id) {
		// 初始化数据 
		List<JcpNews> news = JcpNewsSer.findRelatedNewsById(id, 4);
		if (news != null) {
			for (JcpNews n : news) {
				int fromId = n.getComeFrom();
				String from = ResourceFromServer.getRSources(fromId);
				n.setFrom(from);
			}
		}
		return  JsonUtil.getIndxKnownList(news);

	}

}
