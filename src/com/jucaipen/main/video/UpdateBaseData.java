package com.jucaipen.main.video;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         ���µ����     typeId    0   ��Ƶ�����
 *                             1   ֱ�������
 */
public class UpdateBaseData extends HttpServlet {
	private static final long serialVersionUID = -855260812956018826L;
	private String result;
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
				result = initHits(type, fId);
			}else{
				result=JsonUtil.getRetMsg(2,"����ʧ��:fkId�����쳣");
			}
		}else{
			result=JsonUtil.getRetMsg(3,"����ʧ��:typeId�����쳣");
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initHits(int type, int fkId) {
		int res=0;
		if (type == 0) {
			// ��Ƶ
			int random=RandomUtils.getRandomData(6,0);
			RecoderVideo vide=RecoderVideoServer.getRecoderVideioById(fkId);
			res = RecoderVideoServer.updateRecoderViderHits(fkId, vide.getPlayCount() + 1, vide.getXnPlayCount()
					+ random);
		} else if (type == 1) {
			// ��Ƶֱ��
			int random=RandomUtils.getRandomData(6,0);
			VideoLive live = VideoLiveServer.getRoomInfo(fkId);
			res = VideoLiveServer.updateRenQi(fkId, live.getRenQi() + 1,live.getXnRenQi()+random);
		}
		return res>0 ? JsonUtil.getRetMsg(0, "���³ɹ�") : JsonUtil.getRetMsg(1,"����ʧ��");

	}

}
