package com.jucaipen.service;

import com.jucaipen.dao.LiveRecoderDao;
import com.jucaipen.daoimp.LiveRecoderImp;
import com.jucaipen.model.LiveRecoder;

/**
 * @author 杨朗飞
 *2017年3月11日  下午2:22:28
 *
 *  直播记录操作
 */
public class LiveRecoderSer {

	/**
	 * @return  获取最近的直播记录
	 */
	public static LiveRecoder getRecoderByRect() {
		LiveRecoderDao dao=new LiveRecoderImp();
		return dao.getRecoderByRect();
	}
	
	/**
	 * @param liveId
	 * @return  根据直播id获取直播记录信息
	 */
	public static LiveRecoder getRecoderByLiveId(int liveId){
		LiveRecoderDao dao=new LiveRecoderImp();
		return dao.getRecoderByLiveId(liveId);
	}

}
