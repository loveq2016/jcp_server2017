package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.RecoderVideo;

public interface RecoderVideoDao {
	
	/**
	 * @param teacherId
	 * @return  ��ȡ��ʦ�����һ��¼����Ϣ
	 */
	public RecoderVideo getLastRecoderVideo(int teacherId);
	
	
	/**
	 * @param teacherId
	 * @return  ��ȡ���н�ʦ¼����Ƶ
	 */
	public List<RecoderVideo>   getAllRecoderVideo(int teacherId,int page);
	
	
	/**
	 * @param id
	 * @return  ����id��ȡ¼����Ƶ
	 */
	public RecoderVideo getRecoderVideioById(int id);
	
	
	/**
	 * @param top
	 * @return  ��ȡ����¼����Ƶ
	 */
	public List<RecoderVideo>   getHotVideos(int top);
	
	
	/**
	 * @param id
	 * @param hits
	 * @return  ����¼����Ƶ�����
	 */
	public int updateRecoderViderHits(int id,int hits,int xnHits);
	
	

}
