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
 * @author 杨朗飞
 *   更新直播观看人数
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
							result=JsonUtil.getRetMsg(0, "更新数据成功");
							context.removeAttribute("onLine"+teacherId);
						}else{
							result=JsonUtil.getRetMsg(1, "更新数据失败");
						}
					}else{
						result=JsonUtil.getRetMsg(4, "观看数无需更新");
					}
				}else{
					result=JsonUtil.getRetMsg(4, "观看数无需更新");
				}
			}else{
				result=JsonUtil.getRetMsg(2, "获取录播视频失败");
			}
		}else{
			result=JsonUtil.getRetMsg(3, "recoderId 参数异常");
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
