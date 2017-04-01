package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Withdraw;

public interface WithdrawDao {
	
	/**
	 * @param id
	 * @return 通过id获取提现信息
	 */
	public Withdraw  findWithDrawById(int id);
	
	/**
	 * @param userId
	 * @param page
	 * @return 通过用户获取提现信息
	 */
	public List<Withdraw> findWithDrawByUserId(int userId,int page);
	
	/**
	 * @param withdraw
	 * @return 添加提现信息
	 */
	public int addWithDraw(Withdraw withdraw);

}
