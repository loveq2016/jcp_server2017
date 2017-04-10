package com.jucaipen.main.play;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.Special;
import com.jucaipen.model.Video;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 *  获取往期演播信息
 */
public class LastPlay extends HttpServlet {
	private static final long serialVersionUID = -3226704185189432878L;
	private String result;
	private int isPurch;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String studioId=request.getParameter("studioId");
		String isMore=request.getParameter("isMore");
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(studioId)&&StringUtil.isInteger(studioId)){
			int sId=Integer.parseInt(studioId);
			if(StringUtil.isNotNull(isMore)&&StringUtil.isInteger(isMore)){
				int more=Integer.parseInt(isMore);
				result=initLastPlayList(more,sId,request,userId);
			}else{
				result=JsonUtil.getRetMsg(2, "isMore 参数异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1, "studioId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initLastPlayList(int isMore, int sId,HttpServletRequest req, String userId) {
       //0 已购买 1 未购买 2 已过期
		int uId = 0;
		if(!StringUtil.isNotNull(userId)||!StringUtil.isInteger(userId)){
			isPurch=1;
		}else{
			uId=Integer.parseInt(userId);
			if(uId<=0){
				isPurch=1;
			}
		}
		
		if(isMore==0){
			List<Video> videos = VideoServer.findLastVideoByCommId(sId, 3);
			for(Video v : videos){
				createVideo(v, uId);
			}     
			return JsonUtil.getLastPlayList(videos);
		}else{
			String page=req.getParameter("page");
			if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
				int p=Integer.parseInt(page);
				List<Video> videos=VideoServer.findAllVideoByCommId(sId, p);
				for(Video v : videos){
					createVideo(v, uId);
				}
				return JsonUtil.getLastPlayList(videos);
			}else{
				return JsonUtil.getRetMsg(1, "page 参数异常");
			}
			
		}
		
	}
	
	
	public void createVideo(Video video,int uId){
		int specialId=video.getPecialId();
		Special special=SpecialSer.findSpecialById(specialId);
		video.setSpecial(special!=null);
		video.setCharge(video.getVideoType()==1);
		//0 已购买 1 未购买 2 已过期
				if(uId>0&&specialId>0){
					MySpecial mySpecial=MySpecialSer.getIsMySpecial(uId, specialId);
					if(mySpecial!=null){
						if(TimeUtils.isLive(mySpecial.getStartDate(), mySpecial.getStartDate())){
							isPurch=0;
						}else{
							isPurch=2;
						}
					}else{
						isPurch=1;
					}
				}
				if(uId>0&&specialId<=0){
					//MyVideo myVideo=MyVideoSer.findIsMyVideo(uId, video.getId());
					/*if(myVideo!=null){
						if(TimeUtils.isLive(myVideo.getStartDate(), myVideo.getStartDate())){
							isPurch=0;
						}else{
							isPurch=2;
						}
					}else{
						isPurch=1;
					}*/
				}
				video.setIsPurch(isPurch);
	}

}
