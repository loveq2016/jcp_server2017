package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.RecoderVideo;

public interface RecoderVideoDao {
	
	/**
	 * @param teacherId
	 * @return  获取讲师最近的一条录播信息
	 */
	public RecoderVideo getLastRecoderVideo(int teacherId);
	
	
	/**
	 * @param teacherId
	 * @return  获取所有讲师录播视频
	 */
	public List<RecoderVideo>   getAllRecoderVideo(int teacherId,int page);
	
	
	/**
	 * @param id
	 * @return  根据id获取录播视频
	 */
	public RecoderVideo getRecoderVideioById(int id);
	
	
	/**
	 * @param top
	 * @return  获取热门录播视频
	 */
	public List<RecoderVideo>   getHotVideos(int top);
	
	
	/**
	 * @param id
	 * @param hits
	 * @return  更新录播视频点击数
	 */
	public int updateRecoderViderHits(int id,int hits,int xnHits);
	
	

}
