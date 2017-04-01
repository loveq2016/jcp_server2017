package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Bank;

/**
 * @author Administrator
 *
 *  银行信息
 */
public interface BankDao {
	
	/**
	 * @return 获取所有银行信息
	 */
	public List<Bank> findAllBank();
	
	/**
	 * @param id
	 * @return 根据id获取银行信息
	 */
	public Bank findBankById(int id);

}
