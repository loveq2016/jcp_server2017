package com.jucaipen.main.video;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         更新点击数     typeId    0   视频点击数
 *                             1   直播点击数
 */
public class UpdateBaseData extends HttpServlet {
	private static final long serialVersionUID = -855260812956018826L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId = request.getParameter("typeId");
		String fkId = request.getParameter("fkId");
		if (StringUtil.isNotNull(typeId) && StringUtil.isInteger(typeId)) {
			int type = Integer.parseInt(typeId);
			if (StringUtil.isNotNull(fkId) && StringUtil.isInteger(fkId)) {
				int fId = Integer.parseInt(fkId);
				initHits(type, fId);
			}
		}
		out.flush();
		out.close();
	}

	private void initHits(int type, int fkId) {
		if (type == 0) {
			// 视频
			RecoderVideo vide=RecoderVideoServer.getRecoderVideioById(fkId);
			SiteConfig config = SiteConfigSer.findSiteConfig();
			RecoderVideoServer.updateRecoderViderHits(fkId, vide.getPlayCount() + 1, vide.getXnPlayCount()
					+ config.getVideoMom());
		} else if (type == 1) {
			// 视频直播
			VideoLive live = VideoLiveServer.getRoomInfo(fkId);
			VideoLiveServer.updateRenQi(fkId, live.getRenQi() + 1);
		}

	}

}
