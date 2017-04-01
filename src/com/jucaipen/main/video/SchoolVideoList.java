package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.Special;
import com.jucaipen.model.Video;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.MyVideoSer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 * 
 *         获取学院视频教程列表
 */
public class SchoolVideoList extends HttpServlet {
	private static final long serialVersionUID = 9030455552900154278L;
	private String result;
	private int isPurch;

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
			String userId=request.getParameter("userId");
			result=initTeachList(userId);
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}
	
	private String initTeachList(String userId) {
		int uId=0;
		if(!StringUtil.isNotNull(userId)||!StringUtil.isInteger(userId)){
			isPurch=1;
		}else{
			uId=Integer.parseInt(userId);
			if(uId<=0){
				isPurch=1;
			}
		}
		
		// 初始化视频教程
		List<Video> videos=VideoServer.findVideoByClassIdLast(4, 9);
		if(videos!=null){
			for(Video video : videos){
				//是否为付费视频  0为免费视频，1为付费视频
				createVideo(video, uId);
			}
		}
		return JsonUtil.getTeachVideList(videos);
	}
	
	public void createVideo(Video video,int uId){
		int specialId=video.getPecialId();
		int videoType=video.getVideoType();
		video.setCharge(videoType==1);
		
		if(specialId>0){
			Special special = SpecialSer.findSpecialById(specialId);
			video.setCharge(special.getIsFree()==2);
		}
		//0  有购买     1    未购买      2  已过期
		if(uId>0&&specialId>0){
			MySpecial mySpecial=MySpecialSer.getIsMySpecial(uId, specialId);
			if(mySpecial!=null){
				if(TimeUtils.isLive(mySpecial.getStartDate(), mySpecial.getEndDate())){
					isPurch=0;
				}else{
					isPurch=2;
				}
			}else{
				isPurch=1;
			}
		}
		
		if(uId>0&&specialId<=0){
			MyVideo myVideo=MyVideoSer.findIsMyVideo(uId, video.getId());
			if(myVideo!=null){
				if(TimeUtils.isLive(myVideo.getStartDate(), myVideo.getEndDate())){
					isPurch=0;
				}else{
					isPurch=2;
				}
			}else{
				isPurch=1;
			}
		}
		
		video.setIsPurch(isPurch);
		
	}

}
