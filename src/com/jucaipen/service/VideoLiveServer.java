package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoLiveDao;
import com.jucaipen.daoimp.VideoLiveImp;
import com.jucaipen.model.VideoLive;

/**
 * @author Administrator 直播室
 */
public class VideoLiveServer {

	/**
	 * @return 获取所有直播间列表
	 */
	public static List<VideoLive> getRoomList() {
		VideoLiveDao dao = new VideoLiveImp();
		return dao.getRoomList();
	}

	/**
	 * @param id
	 * @return 根据id 获取直播间视频信息
	 */
	public static VideoLive getRoomLiveUrl(int id) {
		VideoLiveDao dao = new VideoLiveImp();
		return dao.getLiveUrl(id);
	}

	/**
	 * @param page
	 * @return 获取所有的直播信息
	 */
	public static List<VideoLive> findAll(int page,double pageSize) {
		VideoLiveDao dao = new VideoLiveImp();
		return dao.getAllRoom(page,pageSize);
	}
	
	
	/**
	 * @param top
	 * @return  获取推荐的直播
	 */
	public static List<VideoLive>  findRecoderLive(int top){
		VideoLiveDao dao = new VideoLiveImp();
		return dao.findRecoderLive(top);
	}
	
	/**
	 * @param id
	 * @return  根据id获取直播间信息
	 */
	public static VideoLive getRoomInfo(int id){
		VideoLiveDao dao=new VideoLiveImp();
		return dao.getRoomInfo(id);
	}
	
	/**
	 * @param tId
	 * @return  获取讲师下的直播信息
	 */
	public static VideoLive findLiveBytId(int tId){
		VideoLiveDao dao=new VideoLiveImp();
		return dao.findLiveBytId(tId);
	}
	
	public static int updateRenQi(int id,int renQi,int xnRenQi){
		VideoLiveDao dao=new VideoLiveImp();
		return dao.updateRenQi(id, renQi,xnRenQi);
	}
	
	
	/**
	 * @param isEnd
	 * @return  获取正在直播的视频
	 */
	public static List<VideoLive>  findLiveByIsEnd(int isEnd){
		VideoLiveDao dao=new VideoLiveImp();
		return dao.findLiveByIsEnd(isEnd);
	}
	
}
