package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.UserBank;

public interface UserBankDao {
	
	/**
	 * @param userId
	 * @return  获取用户绑定的所有银行卡信息
	 */
	public List<UserBank> findAllBankInfo(int userId);
	
	/**
	 * @param bank
	 * @return 添加银行卡信息
	 */
	public int addUserBank(UserBank bank);
	
	/**
	 * @param id
	 * @return 解除银行卡信息
	 */
	public int removeUserBank(int id);

}
