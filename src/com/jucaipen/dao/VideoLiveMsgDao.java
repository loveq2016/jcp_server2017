package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.VideoLiveMsg;

/**
 * @author Administrator
 *
 *  直播互动消息
 */
public interface VideoLiveMsgDao {
	
	/**
	 * @param count
	 * @param liveId
	 * @return  获取指定直播聊天最近的几条记录
	 */
	public List<VideoLiveMsg> findLastLiveMsg(int count,int liveId,boolean isCheck);
	/**
	 * @param maxId
	 * @param liveId
	 * @param isCheck
	 * @return  根据最大的id实时获取最新的聊天信息
	 */
	public List<VideoLiveMsg>  findLiveMsgByMaxId(int maxId,int liveId,boolean isCheck);
	
	/**
	 * @param msg
	 * @return  添加聊天信息
	 */
	public int addMsg(VideoLiveMsg msg);
	

}
