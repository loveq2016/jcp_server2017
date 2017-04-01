package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  ---------------新增数据
 *
 *   JCP_User_Bank    银行卡信息
 */
@SuppressWarnings("serial")
public class UserBank implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  BankName
	 */
	private String bankName;
	/**
	 *  BankCode
	 */
	private String bankCode;
	/**
	 *  户主  BankUserTrueName
	 */
	private String bankUserTrueName;
	/**
	 *  身份证号   BankIdCard
	 */
	private String bankIdCard;
	/**
	 *  预留手机号  BankMobile
	 */
	private String bankMobile;
	/**
	 *  UserId
	 */
	private int userId;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  Remark
	 */
	private String remark;
	/**
	 *  IsDel 是否删除（0正常，1已删除）
	 */
	private int isDel;
	/**
	 *   IsDefault  1默认，0正常
	 */
	private int isDefault;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankUserTrueName() {
		return bankUserTrueName;
	}
	public void setBankUserTrueName(String bankUserTrueName) {
		this.bankUserTrueName = bankUserTrueName;
	}
	public String getBankIdCard() {
		return bankIdCard;
	}
	public void setBankIdCard(String bankIdCard) {
		this.bankIdCard = bankIdCard;
	}
	public String getBankMobile() {
		return bankMobile;
	}
	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
}
