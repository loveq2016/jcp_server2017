package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  -------------新增数据 
 *
 *   我的礼品 (赠送 收到礼品记录)            JCP_MyLiPin
 */
@SuppressWarnings("serial")
public class MyGifts implements Serializable{
	private int page;
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  FK_SendUserId
	 */
	private int senderId;
	
	/**
	 *    赠送礼品人昵称
	 */
	private String sendName;
	/**
	 *  FK_ReceiveUserId
	 */
	private int receiverId;
	
	/**
	 *   接受礼品人昵称
	 */
	private String receiverName;
	/**
	 *  FK_LiPinId
	 */
	private int giftId;
	
	private String giftName;
	
	private String giftImage;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  SortId
	 */
	private int sortId;
	/**
	 *   礼品数量    LiPinNum
	 */
	private int giftNum;
	/**
	 *  描述   ReMark
	 */
	private String remark;
	
	/**
	 *   礼品单价
	 */
	private int price;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftImage() {
		return giftImage;
	}
	public void setGiftImage(String giftImage) {
		this.giftImage = giftImage;
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
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getGiftNum() {
		return giftNum;
	}
	public void setGiftNum(int giftNum) {
		this.giftNum = giftNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
