package com.jucaipen.dao;

import com.jucaipen.model.SysAccount;

public interface SysAccountDao {
	
	/**
	 * @return  充值  更新账户
	 */
	public int updatePurchInfo(int sysAccount,int childAccount,int userAccount);
	
	/**
	 * @param count
	 * @return   获取总账户信息
	 */
	public SysAccount findAccountInfo();
	
	/**
	 * @param sysAccount
	 * @param childAccount
	 * @param userAccount
	 * @return   守护  更新总账户信息
	 */
	public int updateGurdianInfo(int sysAccount,int childAccount,int userAccount,int gurdianAccount);

}
