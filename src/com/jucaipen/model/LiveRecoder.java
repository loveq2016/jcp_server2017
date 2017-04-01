package com.jucaipen.model;

/**
 * @author 杨朗飞
 *2017年3月11日  上午11:26:27
 *
 *   直播记录
 */
public class LiveRecoder {
	/**
	 *  id
	 */
	private int id;
	
	/**
	 *   直播id
	 */
	private int liveId;
	
	/**
	 *   讲师id
	 */
	private int teacherId;
	
	/**
	 *   直播编号
	 */
	private String liveNo;
	
	/**
	 *   开始时间
	 */
	private String startDate;
	
	/**
	 *   直播状态  (1   正在直播     2   已经结束)
	 */
	private int liveState;
	
	/**
	 *    直播结束时间
	 */
	private String endDate;
	
	/**
	 *   备注
	 */
	private String remark;
	
	

	public LiveRecoder(int id, int liveId, int teacherId, String liveNo,
			String startDate, int liveState, String endDate, String remark) {
		this.id = id;
		this.liveId = liveId;
		this.teacherId = teacherId;
		this.liveNo = liveNo;
		this.startDate = startDate;
		this.liveState = liveState;
		this.endDate = endDate;
		this.remark = remark;
	}

	public int getLiveId() {
		return liveId;
	}



	public void setLiveId(int liveId) {
		this.liveId = liveId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getLiveNo() {
		return liveNo;
	}

	public void setLiveNo(String liveNo) {
		this.liveNo = liveNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getLiveState() {
		return liveState;
	}

	public void setLiveState(int liveState) {
		this.liveState = liveState;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
