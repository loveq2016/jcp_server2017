package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.SharkDetail;

public interface SharkDetailDao {
	
	/**
	 * @param uId
	 * @return  根据用户id获取摇一摇的信息
	 */
	public List<SharkDetail>   getSharkList(int uId);
	/**
	 * @param uId
	 * @return   获取当前用户摇一摇的总次数
	 */
	public int getSharkCount(int uId);
	/**
	 * @param uId
	 * @return  添加摇一摇结果
	 */
	public int addDetail(SharkDetail detail);
	
	/**
	 * @param min
	 * @param max
	 * @return  获取当前区间摇到的次数
	 */
	public int getMaxCount(int min,int max);

}
