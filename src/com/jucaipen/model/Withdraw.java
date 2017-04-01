package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator ----------新增数据
 * 
 *         提现 JCP_TiXian
 */
@SuppressWarnings("serial")
public class Withdraw implements Serializable {
	private int page;
	private int totlePage;
	/**
	 * Id
	 */
	private int id;
	/**
	 *  OrderCode
	 */
	private String orderCode;
	/**
	 *  UserId
	 */
	private int userId;
	/**
	 *  提现账号  BankCode
	 */
	private String bankCode;
	/**
	 *  BankType  账号类型（1支付宝，2银行，0所有）
	 */
	private int bankType;
	/**
	 *  户主名称 BankUserTrueName
	 */
	private String bankUserTrueName;
	/**
	 *  银行名称  BankName
	 */
	private String bankName;
	/**
	 *  提现金额
	 */
	private double outMoney;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  Type  状态:0为所有,1为新申请的未审核,2提现成功,3提现失败
	 */
	private int type;
	/**
	 *  ShenHeDate  审核时间
	 */
	private String checkDate;
	/**
	 *  IsPay  是否已经扣除过账户余额0是未扣除,1已经扣除
	 */
	private int isPay;
	/**
	 *  IsDel  0表示正常，1表示用户删除，2表示客服删除
	 */
	private int isDel;
	/**
	 *  Remark  
	 */
	private String remark;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotlePage() {
		return totlePage;
	}
	public void setTotlePage(int totlePage) {
		this.totlePage = totlePage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public int getBankType() {
		return bankType;
	}
	public void setBankType(int bankType) {
		this.bankType = bankType;
	}
	public String getBankUserTrueName() {
		return bankUserTrueName;
	}
	public void setBankUserTrueName(String bankUserTrueName) {
		this.bankUserTrueName = bankUserTrueName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(double outMoney) {
		this.outMoney = outMoney;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
