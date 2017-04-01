package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator    -------------新增数据
 *
 *   账户信息
 */
@SuppressWarnings("serial")
public class Account implements Serializable{
	/**
	 *  id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 积分
	 */
	private int integeral;
	/**
	 *  聚财币
	 */
	private int jucaiBills;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIntegeral() {
		return integeral;
	}
	public void setIntegeral(int integeral) {
		this.integeral = integeral;
	}
	public int getJucaiBills() {
		return jucaiBills;
	}
	public void setJucaiBills(int jucaiBills) {
		this.jucaiBills = jucaiBills;
	}
}
