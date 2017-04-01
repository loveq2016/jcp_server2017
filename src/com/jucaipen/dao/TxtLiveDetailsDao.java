package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TxtLiveDetails;

public interface TxtLiveDetailsDao {
	
	/**
	 * @param details
	 * @return  添加文字直播详细内容
	 */
	public int insertTextDetaile(TxtLiveDetails details);

	/**
	 * @param id
	 * @return 根据id获取直播详细信息
	 */
	public TxtLiveDetails findTextDetaileById(int id);
	/**
	 * @param type
	 * @return  根据类型获取直播详细信息
	 */
	public List<TxtLiveDetails> findTextDetaileByType(int type);
	/**
	 * @return  获取所有直播详细信息
	 */
	public List<TxtLiveDetails> findAllTextDetaile();
	/**
	 * @param liveId
	 * @return  根据直播id查询直播详细信息
	 */
	public List<TxtLiveDetails> findTextDetaileByLiveId(int liveId,int type);
	
	
	public List<TxtLiveDetails> findLaseDetaileByLiveId(int liveId,int count,int type);
	
	/**
	 * @param liveId
	 * @param maxId
	 * @return   查询实时更新的今日观点
	 */
	public List<TxtLiveDetails> findPullTextDetaileByLiveId(int liveId,int maxId);
	/**
	 * @param titleId
	 * @return   根据标题id获取文字直播信息
	 */
	public List<TxtLiveDetails> findTextDetaileByTitleId(int titleId);
}
