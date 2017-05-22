package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.RecoderVideo;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;

/**
 * @author ���ʷ�
 *   ����ֱ���ۿ�����
 */
public class UpdataWatchNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext context = request.getServletContext();
		String recoderId=request.getParameter("recoderId");
		if(StringUtil.isNotNull(recoderId)&&StringUtil.isInteger(recoderId)){
			int rId=Integer.parseInt(recoderId);
			RecoderVideo recoderVideo = RecoderVideoServer.getRecoderVideioById(rId);
			if(recoderVideo!=null){
				int teacherId = recoderVideo.getTeacherId();
				if(context.getAttribute("onLine"+teacherId)!=null){
					int num=(Integer) context.getAttribute("onLine"+teacherId);
					if(num>0){
						int hits=recoderVideo.getPlayCount()+num;
						int random=RandomUtils.getRandomData(6,0);
						int xnHits=recoderVideo.getXnPlayCount()+random;
						int viderHits = RecoderVideoServer.updateRecoderViderHits(rId, hits, xnHits);
						if(viderHits>0){
							result=JsonUtil.getRetMsg(0, "�������ݳɹ�");
							context.removeAttribute("onLine"+teacherId);
						}else{
							result=JsonUtil.getRetMsg(1, "��������ʧ��");
						}
					}else{
						result=JsonUtil.getRetMsg(4, "�ۿ����������");
					}
				}else{
					result=JsonUtil.getRetMsg(4, "�ۿ����������");
				}
			}else{
				result=JsonUtil.getRetMsg(2, "��ȡ¼����Ƶʧ��");
			}
		}else{
			result=JsonUtil.getRetMsg(3, "recoderId �����쳣");
		}
		out.print(result);
		out.flush();
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 this.doPost(req, resp);
	}

}
