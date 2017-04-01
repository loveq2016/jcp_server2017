package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.FeedBack;
/**
 * @author Administrator
 *
 *  意见
 */
public interface FeedBackDao {
	
	
	/**
	 * @param feedBack
	 * @return  添加意见
	 */
	public int insertFeedBack(FeedBack feedBack);
	/**
	 * @return  获取所有意见
	 */
	public List<FeedBack> findAllFeedBack();
	/**
	 * @param uId
	 * @return 根据用户id查询意见
	 */
	public List<FeedBack> findFeedBaceByUserId(int uId);
	/**
	 * @param type
	 * @return 根据意见类型查询意见
	 */
	public List<FeedBack> findFeedBackByType(int type);
	/**
	 * @param uId
	 * @param type
	 * @return  根据用户id 和意见状态获取意见信息
	 */
	public List<FeedBack> findFeedBackByUidAndType(int uId,int type);
	/**
	 * @param id
	 * @return 根据意见id，查询意见详细内容
	 */
	public FeedBack findFeedBackById(int id);
	

}
