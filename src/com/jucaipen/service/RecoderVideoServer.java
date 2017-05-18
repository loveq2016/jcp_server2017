package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.RecoderVideoDao;
import com.jucaipen.daoimp.RecoderVideoImp;
import com.jucaipen.model.RecoderVideo;

public class RecoderVideoServer{

	/**
	 * @param teacherId
	 * @return  获取最近的录制信息
	 */
	public static RecoderVideo getLastRecoderVideo(int teacherId) {
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getLastRecoderVideo(teacherId);
	}
	
	/**
	 * @param top
	 * @return  获取热门录播视频
	 */
	public static List<RecoderVideo> getHotVideos(int top){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getHotVideos(top);
	}
	
	/**
	 * @param teacherId
	 * @param page
	 * @return  获取讲师下的所有录播
	 */
	public static List<RecoderVideo>  getAllRecoderVideo(int teacherId,int page,double pageSize){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getAllRecoderVideo(teacherId,page,pageSize);
	}
	
	
	/**
	 * @param id
	 * @return  根据id获取录播视频
	 */
	public static RecoderVideo getRecoderVideioById(int id){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getRecoderVideioById(id);
	}
	
	/**
	 * @param id
	 * @param hits
	 * @param xnHits
	 * @return  更新录播视频点击数
	 */
	public static int updateRecoderViderHits(int id,int hits,int xnHits){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.updateRecoderViderHits(id, hits, xnHits);
	}

}
