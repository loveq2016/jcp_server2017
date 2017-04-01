package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TxtLiveDetailsDao;
import com.jucaipen.daoimp.TxtLiveDetaileImp;
import com.jucaipen.model.TxtLiveDetails;

public class TxtLiveDetaileSer{
	
	
	/**
	 * @param details
	 * @return  添加文字直播详细内容
	 */
	public static int insertTextDetaile(TxtLiveDetails details){
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.insertTextDetaile(details);
	}

	/**
	 * @param id
	 * @return   根据id获取文字直播详细内容
	 */
	public static TxtLiveDetails findTextDetaileById(int id) {
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findTextDetaileById(id);
	}
	
	/**
	 * @param liveId
	 * @return  根据直播id获取文字直播详细内容
	 */
	public static List<TxtLiveDetails> findTextDetaileByLiveId(int liveId,int type){
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findTextDetaileByLiveId(liveId,type);
	}
	
	/**
	 * @param liveId
	 * @param maxId
	 * @return  获取实时更新的今日观点
	 */
	public static List<TxtLiveDetails> findPullTextDetaileByLiveId(int liveId,int maxId){
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findPullTextDetaileByLiveId(liveId, maxId);
	}
	
	public static List<TxtLiveDetails> findLaseDetaileByLiveId(int liveId,int count,int type){
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findLaseDetaileByLiveId(liveId,count,type);
	}
	
	
	/**
	 * @param titleId
	 * @return  根据标题id获取文字直播详细内容
	 */
	public static List<TxtLiveDetails> findTextDetaileByTitleId(int titleId){
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findTextDetaileByTitleId(titleId);
	}

	/**
	 * @param type
	 * @return  根据状态获取文字直播详细内容
	 */
	public static List<TxtLiveDetails> findTextDetaileByType(int type) {
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findTextDetaileByType(type);
	}

	/**
	 * @return  获取所有的文字直播详细信息
	 */
	public static List<TxtLiveDetails> findAllTextDetaile() {
		TxtLiveDetailsDao dao=new TxtLiveDetaileImp();
		return dao.findAllTextDetaile();
	}

}
