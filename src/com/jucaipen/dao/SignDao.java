package com.jucaipen.dao;

import com.jucaipen.model.Sign;

/**
 * @author Administrator
 *
 *  签到
 */
public interface SignDao {
	
	/**
	 * @param userId
	 * @return 根据用户id获取签到信息
	 */
	public Sign findSignByUid(int userId);
	
	/**
	 * @param sign
	 * @return 添加签到信息
	 */
	public int addSign(Sign sign);
	
	/**
	 * @param sign
	 * @return  更新签到总表
	 */
	public int updateSign(Sign sign);
	
	/**
	 * @param uId
	 * @return  获取签到次数
	 */
	public int findSignCount(int uId);

}
