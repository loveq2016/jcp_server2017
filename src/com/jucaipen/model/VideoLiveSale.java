package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *  视频直播购买信息
 */
@SuppressWarnings("serial")
public class VideoLiveSale implements Serializable{
	private int id;
	private int userId;
	private int teacherId;
	private String orderCode;
	private int fk_liveId;
	private String insertDate;
	private String startDate;
	private String endDate;
	private int isStop;
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
	public int getFk_liveId() {
		return fk_liveId;
	}
	public void setFk_liveId(int fk_liveId) {
		this.fk_liveId = fk_liveId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
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
	public int getIsStop() {
		return isStop;
	}
	public void setIsStop(int isStop) {
		this.isStop = isStop;
	}
}
