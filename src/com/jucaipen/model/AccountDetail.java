package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   ----------新增数据
 *
 *   账户详细信息
 */
public class AccountDetail implements Serializable{
	private int page;
	private int totlePage;
	/**
	 *  id
	 */
	private int id;
	/**
	 * 订单编号
	 */
	private String orderCode;
	/**
	 *  消费或支出金额
	 */
	private double detailMoney;
	/**
	 * 订单类型 0收入，1消费
	 */
	private int detailType;
	/**
	 * 0金额，1积分，2聚财币
	 */
	private int state;
	/**
	 *  备注
	 */
	private String remark;
	/**
	 * 添加时间
	 */
	private String insertDate;
	/**
	 * 0正常，1已删除
	 */
	private int isDel;
	/**
	 *  用户id
	 */
	private int userId;
	
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
	
	
	public AccountDetail() {
		
		
	}
	public AccountDetail(int id, String orderCode, double detailMoney,
			int detailType, int state, String remark, String insertDate,
			int isDel, int userId) {
		this.id = id;
		this.orderCode = orderCode;
		this.detailMoney = detailMoney;
		this.detailType = detailType;
		this.state = state;
		this.remark = remark;
		this.insertDate = insertDate;
		this.isDel = isDel;
		this.userId = userId;
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
	public double getDetailMoney() {
		return detailMoney;
	}
	public void setDetailMoney(double detailMoney) {
		this.detailMoney = detailMoney;
	}
	public int getDetailType() {
		return detailType;
	}
	public void setDetailType(int detailType) {
		this.detailType = detailType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
