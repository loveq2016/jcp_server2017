package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  --------------------新增数据
 *   
 *    打赏    JCP_MarkerWord_Dateil
 *
 */
@SuppressWarnings("serial")
public class Marker implements Serializable {
	private int page;
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  打赏类型（0聚财币,1积分） MarkerType
	 */
	private int type;
	/**
	 *  FK_UserId
	 */
	private int userId;
	/**
	 *  FK_LogId
	 */
	private int ideaId;
	/**
	 *   MaekerCount  打赏金额
	 */
	private int  markerCount;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  IP
	 */
	private String ip;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}
	public int getMarkerCount() {
		return markerCount;
	}
	public void setMarkerCount(int markerCount) {
		this.markerCount = markerCount;
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
}
