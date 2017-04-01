package com.jucaipen.model;

/**
 * @author Administrator
 *
 *  ÊÔ¿´¼ÇÂ¼
 */
public class SaleRecoder {
	private int id;
	private int userId;
	private int type;
	private int liveId;
	private int readCount;
	private String insertDate;
	private String remark;
	private int isDel;
	private int guandianId;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLiveId() {
		return liveId;
	}
	public void setLiveId(int liveId) {
		this.liveId = liveId;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getGuandianId() {
		return guandianId;
	}
	public void setGuandianId(int guandianId) {
		this.guandianId = guandianId;
	}
}
