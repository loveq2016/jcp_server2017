package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.VideoLive;
/**
 * @author YLF
 * 
 *   直播室
 *
 */
public interface VideoLiveDao {
	
	/**
	 * @return   获取所有房间名称
	 */
	public List<VideoLive> getRoomList();
	
	/**
	 * @param id
	 * @param renQi
	 * @return  更新人气信息
	 */
	public int updateRenQi(int id,int renQi);
	/**
	 * @param id
	 * @return  根据id 获取房间详细信息
	 */
	public VideoLive getRoomInfo(int id);
	
	/**
	 * @param id
	 * @return  根据id 获取视频播放URL
	 */
	public VideoLive  getLiveUrl(int id);
	
	/**
	 * @return   获取所有房间信息
	 */
	public List<VideoLive> getAllRoom(int page);
	
	/**
	 * @param tId
	 * @return  获取讲师下的视频直播
	 */
	public VideoLive  findLiveBytId(int tId);
	
	
	/**
	 * @param isEnd
	 * @return   获取正在直播的视频直播
	 */
	public List<VideoLive>  findLiveByIsEnd(int isEnd);
	
	
	/**
	 * @param top
	 * @return  获取推荐的直播视频
	 */
	public List<VideoLive>  findRecoderLive(int top);

}
