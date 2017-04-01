package com.jucaipen.dao;

import com.jucaipen.model.SharkInfo;

public interface SharkInfoDao {
	
	/**
	 * @return   获取当前的摇一摇信息
	 */
	public SharkInfo getSharkInfo();
	
	/**
	 * @param data
	 * @return  更新剩余额度
	 */
	public int updatePrice(int rest);
	
	/**
	 * @param date
	 * @return   查询已经结束还是还没开始
	 */
	public SharkInfo getSharkByIsEnd(String date);
	
	
	

}
