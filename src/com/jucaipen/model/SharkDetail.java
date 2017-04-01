package com.jucaipen.model;

import java.io.Serializable;

public class SharkDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private int id;
	/**
	 *   用户id
	 */
	private int userId;
	/**
	 *   获奖金额
	 */
	private int price;
	/**
	 *  获奖途径（1手机网页端，2手机安卓端，3手机IOS端）
	 */
	private int pathWay;
	/**
	 *   获奖时间
	 */
	private String insertDate;
	/**
	 * 备注
	 */
	private String remark;
	
	public SharkDetail() {
	}

	public SharkDetail(int id, int userId, int price, int pathWay,
			String insertDate, String remark) {
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.pathWay = pathWay;
		this.insertDate = insertDate;
		this.remark = remark;
	}

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPathWay() {
		return pathWay;
	}

	public void setPathWay(int pathWay) {
		this.pathWay = pathWay;
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
}
