package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.SignDetail;

public interface SignDetailDao {
	
	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取签到信息  分页
	 */
	public List<SignDetail> findSignDetailByUserId(int userId,int page);
	
	/**
	 * @param userId
	 * @return  根据用户id获取签到信息   月数据
	 */
	public List<SignDetail> findMothSignDetailByUserId(int userId);
	/**
	 * @param detail
	 * @return 添加签到详细信息
	 */
	public int addSignDetail(SignDetail detail);

}
