package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator -------------------新增数据
 * 
 *         我的礼品(个人拥有的) JCP_MyPresent
 */
@SuppressWarnings("serial")
public class MyPresent implements Serializable {
	private int page;
	private int totlePage;
	/**
	 * Id
	 */
	private int id;
	/**
	 * FK_UserId
	 */
	private int userId;
	/**
	 * 礼品数量
	 */
	private int presentNum;
	/**
	 * FK_LiPinId
	 */
	private int presentId;
	private String prentName;
	
	private String prentImage;
	
	

	public String getPrentName() {
		return prentName;
	}

	public void setPrentName(String prentName) {
		this.prentName = prentName;
	}

	public String getPrentImage() {
		return prentImage;
	}

	public void setPrentImage(String prentImage) {
		this.prentImage = prentImage;
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

	public int getPresentNum() {
		return presentNum;
	}

	public void setPresentNum(int presentNum) {
		this.presentNum = presentNum;
	}

	public int getPresentId() {
		return presentId;
	}

	public void setPresentId(int presentId) {
		this.presentId = presentId;
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
	
}
