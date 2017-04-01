package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *  系统总账户
 */
@SuppressWarnings("serial")
public class SysAccount implements Serializable{
	/**
	 *  系统总账户（用户充值增加  赠送聚财币增加，讲师提现时扣除）
	 */
	private int sysAccount;
	/**
	 *  系统子账户（初始0，用户消费时增加，讲师提现时扣除）消费总账户
	 */
	private int sysChildAccount;
	
	/**
	 *  用户剩余金额
	 */
	private int userAccount;
	
	/**
	 *   礼品消费账户
	 */
	private int giftAccount;
	
	/**
	 *  直播观点消费账户
	 */
	private int txtLiveAccount;
	/**
	 *  购买日志消费账户
	 */
	private int payLogAccount;
	/**
	 *   购买问答消费账户
	 */
	private int payAskAccount;
	/**
	 *  开通守护消费账户
	 */
	private int openGurdianAccount;
	/**
	 *  订阅战法消费账户
	 */
	private int tactivsAccount;
	/**
	 *  购买视频消费总账户
	 */
	private int payVideoAccount;
	
	/**
	 *  购买专辑消费总账户
	 */
	private int paySpecialAccount;
	/**
	 *  讲师返利总账户
	 */
	private int teacherRebateAccount;
	/**
	 *  系统返利总账户
	 */
	private int sysRebateAccount;
	/**
	 *  系统赠送总账户
	 */
	private int sysDobateAccount;
	
	/**
	 *   打赏账户
	 */
	private int daShangAccount;
	

	public SysAccount() {
	}

	public int getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(int sysAccount) {
		this.sysAccount = sysAccount;
	}

	public int getSysChildAccount() {
		return sysChildAccount;
	}

	public void setSysChildAccount(int sysChildAccount) {
		this.sysChildAccount = sysChildAccount;
	}

	public int getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(int userAccount) {
		this.userAccount = userAccount;
	}

	public int getGiftAccount() {
		return giftAccount;
	}

	public void setGiftAccount(int giftAccount) {
		this.giftAccount = giftAccount;
	}

	public int getTxtLiveAccount() {
		return txtLiveAccount;
	}

	public void setTxtLiveAccount(int txtLiveAccount) {
		this.txtLiveAccount = txtLiveAccount;
	}

	public int getPayLogAccount() {
		return payLogAccount;
	}

	public void setPayLogAccount(int payLogAccount) {
		this.payLogAccount = payLogAccount;
	}

	public int getPayAskAccount() {
		return payAskAccount;
	}

	public void setPayAskAccount(int payAskAccount) {
		this.payAskAccount = payAskAccount;
	}

	public int getOpenGurdianAccount() {
		return openGurdianAccount;
	}

	public void setOpenGurdianAccount(int openGurdianAccount) {
		this.openGurdianAccount = openGurdianAccount;
	}

	public int getTactivsAccount() {
		return tactivsAccount;
	}

	public void setTactivsAccount(int tactivsAccount) {
		this.tactivsAccount = tactivsAccount;
	}

	public int getPayVideoAccount() {
		return payVideoAccount;
	}

	public void setPayVideoAccount(int payVideoAccount) {
		this.payVideoAccount = payVideoAccount;
	}

	public int getPaySpecialAccount() {
		return paySpecialAccount;
	}

	public void setPaySpecialAccount(int paySpecialAccount) {
		this.paySpecialAccount = paySpecialAccount;
	}

	public int getTeacherRebateAccount() {
		return teacherRebateAccount;
	}

	public void setTeacherRebateAccount(int teacherRebateAccount) {
		this.teacherRebateAccount = teacherRebateAccount;
	}

	public int getSysRebateAccount() {
		return sysRebateAccount;
	}

	public void setSysRebateAccount(int sysRebateAccount) {
		this.sysRebateAccount = sysRebateAccount;
	}

	public int getSysDobateAccount() {
		return sysDobateAccount;
	}

	public void setSysDobateAccount(int sysDobateAccount) {
		this.sysDobateAccount = sysDobateAccount;
	}

	public int getDaShangAccount() {
		return daShangAccount;
	}

	public void setDaShangAccount(int daShangAccount) {
		this.daShangAccount = daShangAccount;
	}
}
