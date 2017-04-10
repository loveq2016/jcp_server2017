package com.jucaipen.dao;

import com.jucaipen.model.LiveRecoder;

/**
 * @author 杨朗飞
 *2017年3月11日  下午1:44:37
 *
 *   直播记录
 */
public interface LiveRecoderDao {
	
	/**
	 * @return  获取最近的直播信息
	 */
	public LiveRecoder getRecoderByRect();
	
	/**
	 * @param liveId
	 * @return    根据直播id获取直播记录信息
	 */
	public LiveRecoder getRecoderByLiveId(int recoderId);

}
