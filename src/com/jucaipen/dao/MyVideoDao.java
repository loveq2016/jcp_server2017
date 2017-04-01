package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MyVideo;

/**
 * @author Administrator
 *
 *  我的视频
 */
public interface MyVideoDao {
	
	/**
	 * @param id
	 * @return  通过id获取视频信息
	 */
	public MyVideo findVideoById(int id);
	
	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取我的视频信息
	 */
	public List<MyVideo> findMyVideoByUserId(int userId,int page);
	
	/**
	 * @param video
	 * @return 添加我的视频信息
	 */
	public int addMyVideo(MyVideo video);
	/**
	 * @param id
	 * @return 删除我的视频记录
	 */
	public int removeMyVideo(int id);
	
	public MyVideo findIsMyVideo(int uId,int vId);
	
	/**
	 * @param id
	 * @return  获取购买视频数量
	 */
	public int getPurchVideoNum(int id);
	

}
