package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author 杨朗飞
 *2017年1月23日  下午1:31:36
 *
 *   摇一摇当前信息
 */
public class SharkInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *  id
	 */
	private int id;
	/**
	 *   发放额度
	 */
	private int price;
	/**
	 * 剩余额度
	 */
	private int syPrice;
	/**
	 *   开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 昨天发放剩余金额（此处显示昨天发放用户未抢完金额，当天额度发放完成后读取该字段是否大于0，如果大于0则开始发放改
	 */
	private int addPeice;
	/**
	 *  昨天发放剩余可发放金额
	 */
	private int sYAddPeice;
	/**
	 *   备注说明
	 */
	private String remark;
	
	public SharkInfo() {
		
	}
	public SharkInfo(int id, int price, int syPrice, String startDate,
			String endDate, int addPeice, int sYAddPeice, String remark) {
		this.id = id;
		this.price = price;
		this.syPrice = syPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.addPeice = addPeice;
		this.sYAddPeice = sYAddPeice;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSyPrice() {
		return syPrice;
	}
	public void setSyPrice(int syPrice) {
		this.syPrice = syPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAddPeice() {
		return addPeice;
	}
	public void setAddPeice(int addPeice) {
		this.addPeice = addPeice;
	}
	public int getsYAddPeice() {
		return sYAddPeice;
	}
	public void setsYAddPeice(int sYAddPeice) {
		this.sYAddPeice = sYAddPeice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
