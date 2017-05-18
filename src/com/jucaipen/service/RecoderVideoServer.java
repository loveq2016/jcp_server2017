package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.RecoderVideoDao;
import com.jucaipen.daoimp.RecoderVideoImp;
import com.jucaipen.model.RecoderVideo;

public class RecoderVideoServer{

	/**
	 * @param teacherId
	 * @return  ��ȡ�����¼����Ϣ
	 */
	public static RecoderVideo getLastRecoderVideo(int teacherId) {
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getLastRecoderVideo(teacherId);
	}
	
	/**
	 * @param top
	 * @return  ��ȡ����¼����Ƶ
	 */
	public static List<RecoderVideo> getHotVideos(int top){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getHotVideos(top);
	}
	
	/**
	 * @param teacherId
	 * @param page
	 * @return  ��ȡ��ʦ�µ�����¼��
	 */
	public static List<RecoderVideo>  getAllRecoderVideo(int teacherId,int page,double pageSize){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getAllRecoderVideo(teacherId,page,pageSize);
	}
	
	
	/**
	 * @param id
	 * @return  ����id��ȡ¼����Ƶ
	 */
	public static RecoderVideo getRecoderVideioById(int id){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.getRecoderVideioById(id);
	}
	
	/**
	 * @param id
	 * @param hits
	 * @param xnHits
	 * @return  ����¼����Ƶ�����
	 */
	public static int updateRecoderViderHits(int id,int hits,int xnHits){
		RecoderVideoDao dao=new RecoderVideoImp();
		return dao.updateRecoderViderHits(id, hits, xnHits);
	}

}
