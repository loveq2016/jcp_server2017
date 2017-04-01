package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Account;

/**
 * @author Administrator
 *
 *  账户信息
 */
public interface AccountDao {
	
	/**
	 * @return  获取所有账户信息
	 */
	public List<Account> findAllAccount();
	
	/**
	 * @param id
	 * @return  通过id获取账户信息
	 */
	public Account findAccountById(int id);
	/**
	 * @param uId
	 * @return  通过用户id获取账户信息
	 */
	public Account findAccountByUserId(int uId);
	
	/**
	 * @param uId
	 * @return  修改账户聚财币数量
	 */
	public int updateBills(int uId,int newBills);
	
	/**
	 * @param uId
	 * @param integeral
	 * @return  修改积分信息
	 */
	public int updateIntegeral(int uId,int integeral);
	
	/**
	 * @param account
	 * @return  添加账户信息
	 */
	public int addAccount(Account account);
	

}
