package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   -------------新增数据
 *
 * 银行信息
 */
public class Bank implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  BankName
	 */
	private String bankName;
	/**
	 *  BankChannel
	 */
	private String bankChannel;
	/**
	 *  BankClass
	 */
	private String bankClass;
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
	public String getBankChannel() {
		return bankChannel;
	}
	public void setBankChannel(String bankChannel) {
		this.bankChannel = bankChannel;
	}
	public String getBankClass() {
		return bankClass;
	}
	public void setBankClass(String bankClass) {
		this.bankClass = bankClass;
	}
}
