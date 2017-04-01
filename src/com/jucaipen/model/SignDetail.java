package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  -------------新增数据
 *
 *   签到详细信息    JCP_QianDao_Detail
 */
@SuppressWarnings("serial")
public class SignDetail implements Serializable{
	private int page;
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  UserId
	 */
	private int userId;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  Ip
	 */
	private String ip;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
