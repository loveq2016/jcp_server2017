package com.jucaipen.dao;

import com.jucaipen.model.SysAccount;

public interface SysAccountDao {
	
	/**
	 * @return  充值  更新账户
	 */
	public int updatePurchInfo(int sysAccount,int childAccount,int userAccount);
	
	
	/**
	 * @param child
	 * @param user
	 * @param daShang
	 * @return  更新打赏数据
	 */
	public int updateDaShangInfo(int child,int user,int daShang);
	
	
	/**
	 * @param teacherAccount
	 * @param rebateAccount
	 * @return  更新讲师返利数据
	 */
	public int updateTeacherRebate(int teacherAccount,int rebateAccount);
	
	
	/**
	 * @param child
	 * @param user
	 * @param gift
	 * @return   购买礼品数据更新
	 */
	public int updateGiftInfo(int child,int user,int gift);
	
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
