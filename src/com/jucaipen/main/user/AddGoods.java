package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.JcpNews;
import com.jucaipen.model.Knowledge;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.Video;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.JcpNewsSer;
import com.jucaipen.service.KnowledgetSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *    更新点赞数
 *    1 观点点赞数 2 知识点赞数 3 文字直播点赞数 4 资讯点赞数 5 视频点赞数 6 视频评论点赞
 * 
 */
public class AddGoods extends HttpServlet {
	private static final long serialVersionUID = -9204248834484763251L;
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId = request.getParameter("typeId");
		String fkId = request.getParameter("fkId");
		String parentId = request.getParameter("parentId");
		if (StringUtil.isNotNull(typeId) && StringUtil.isInteger(typeId)) {
			int type = Integer.parseInt(typeId);
			if (StringUtil.isNotNull(fkId) && StringUtil.isInteger(fkId)) {
				int fId = Integer.parseInt(fkId);
				result = initGoods(type, fId, parentId);
			} else {
				result = JsonUtil.getRetMsg(2, "fkId 参数异常");
			}

		} else {
			result = JsonUtil.getRetMsg(1, "typeId 参数异常");
		}

		out.println(result);
		out.flush();
		out.close();     
	}

	private String initGoods(int type, int fId, String parentId) {
		int isSuccess = 0;
		if (type == 1) {
			// 观点
			HotIdea idea = HotIdeaServ.findGoodAndHitAndComm(fId);
			isSuccess = HotIdeaServ.addGood(fId, idea.getGoods() + 1);
		} else if (type == 2) {
			// 知识
			Knowledge knowledge = KnowledgetSer.findHitAndGoods(fId);
			isSuccess = KnowledgetSer.addGoods(fId, knowledge.getGoods() + 1);
		} else if (type == 3) {
			// 文字直播
			TextLive live = TxtLiveSer.findHitsAndGoods(fId);
			isSuccess = TxtLiveSer.addGoods(fId, live.getGoods() + 1);
		} else if (type == 4) {
			// 资讯
			JcpNews news = JcpNewsSer.findHitsAndGoods(fId);
			isSuccess = JcpNewsSer.addGoods(fId, news.getZan() + 1);
		} else if (type == 5) {
			// 视频
			Video video=VideoServer.findHitsAndGoods(fId);
            isSuccess=VideoServer.updateGoods(fId, video.getGoods()+1);
		}
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "点赞成功") : JsonUtil
				.getRetMsg(1, "点赞失败");
	}

}
