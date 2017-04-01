package com.jucaipen.model;

/**
 * @author 杨朗飞
 *2017年3月11日  上午11:27:50
 *
 *   直播购买记录表    单次
 */
public class LiveRecoderSale {
	
	/**
	 *   id
	 */
	private int id;
	
	/**
	 *    直播记录id
	 */
	private int liveRecoderId;
	
	/**
	 *   讲师id
	 */
	private int teacherId;
	
	/**
	 *   购买时间
	 */
	private String purchDate;
	
	/**
	 *   支付的聚财币
	 */
	private int payBills;
	
	/**
	 *   购买用户
	 */
	private int userId;
	
	/**
	 *  备注
	 */
	private String remark;
	
	
	
	public LiveRecoderSale() {
	}

	public LiveRecoderSale(int id, int liveRecoderId, int teacherId,
			String purchDate, int payBills, int userId, String remark) {
		this.id = id;
		this.liveRecoderId = liveRecoderId;
		this.teacherId = teacherId;
		this.purchDate = purchDate;
		this.payBills = payBills;
		this.userId = userId;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLiveRecoderId() {
		return liveRecoderId;
	}

	public void setLiveRecoderId(int liveRecoderId) {
		this.liveRecoderId = liveRecoderId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getPurchDate() {
		return purchDate;
	}

	public void setPurchDate(String purchDate) {
		this.purchDate = purchDate;
	}

	public int getPayBills() {
		return payBills;
	}

	public void setPayBills(int payBills) {
		this.payBills = payBills;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
