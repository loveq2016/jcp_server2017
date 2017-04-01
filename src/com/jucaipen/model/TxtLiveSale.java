package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *   文字直播购买信息
 */
@SuppressWarnings("serial")
public class TxtLiveSale implements Serializable{
	private int page;
	private int totlePage;
	private int id;
	private int userId;
	private int teacherId;
	private int fk_attenNum;
	private String teacherName;
	private String fk_title;
	private String orderCode;
	private int fk_txtHits;
	private int fk_txtIsEnd;
	private int fk_txtId;
	private String insertDate;
	private String startDate;
	private String endDate;
	private String remark;
	private int payPrice;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
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
	private boolean isGurdian;
	
	public boolean isGurdian() {
		return isGurdian;
	}
	public void setGurdian(boolean isGurdian) {
		this.isGurdian = isGurdian;
	}
	public int getFk_attenNum() {
		return fk_attenNum;
	}
	public void setFk_attenNum(int fk_attenNum) {
		this.fk_attenNum = fk_attenNum;
	}
	public int getFk_txtIsEnd() {
		return fk_txtIsEnd;
	}
	public void setFk_txtIsEnd(int fk_txtIsEnd) {
		this.fk_txtIsEnd = fk_txtIsEnd;
	}
	public int getFk_txtHits() {
		return fk_txtHits;
	}
	public void setFk_txtHits(int fk_txtHits) {
		this.fk_txtHits = fk_txtHits;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getFk_title() {
		return fk_title;
	}
	public void setFk_title(String fk_title) {
		this.fk_title = fk_title;
	}
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
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getFk_txtId() {
		return fk_txtId;
	}
	public void setFk_txtId(int fk_txtId) {
		this.fk_txtId = fk_txtId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
}
