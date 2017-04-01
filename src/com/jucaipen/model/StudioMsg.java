package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  ------------新增数据
 *
 *  演播室消息  
 */
@SuppressWarnings("serial")
public class StudioMsg implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  SendUserId
	 */
	private int sendUserId;
	/**
	 *  SendName
	 */
	private String sendName;
	
	private String sendFace;
	/**
	 *  Msg
	 */
	private String msg;
	/**
	 *  shenhe
	 */
	private int shenhe;
	/**
	 *  ReceiverId
	 */
	private int receiverId;
	/**
	 *  ReceiverName
	 */
	private String receiverName;
	
	private String receiverFace;
	/**
	 *  Fk_VideoLiveId
	 */
	private int videoLiveId;
	/**
	 *  BuyProductId  守护等级
	 */
	private int buyProductId;
	/**
	 *  IsSysAdmin
	 */
	private int isSysAdmin;
	/**
	 *  IsRoomAdmin
	 */
	private int isRoomAdmin;
	/**
	 *  SendDate
	 */
	private String sendDate;
	private int isTeacher;
	private int isShouhu;
	private int isChatAdmin;
	private int userLeavel;
	private int isServer;
	
	/**
	 *  IP
	 */
	private String ip;
	
	
	
	
	
	public String getSendFace() {
		return sendFace;
	}
	public void setSendFace(String sendFace) {
		this.sendFace = sendFace;
	}
	public String getReceiverFace() {
		return receiverFace;
	}
	public void setReceiverFace(String receiverFace) {
		this.receiverFace = receiverFace;
	}
	public int getIsServer() {
		return isServer;
	}
	public void setIsServer(int isServer) {
		this.isServer = isServer;
	}
	public int getUserLeavel() {
		return userLeavel;
	}
	public void setUserLeavel(int userLeavel) {
		this.userLeavel = userLeavel;
	}
	public int getIsTeacher() {
		return isTeacher;
	}
	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}
	public int getIsShouhu() {
		return isShouhu;
	}
	public void setIsShouhu(int isShouhu) {
		this.isShouhu = isShouhu;
	}
	public int getIsChatAdmin() {
		return isChatAdmin;
	}
	public void setIsChatAdmin(int isChatAdmin) {
		this.isChatAdmin = isChatAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(int sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getShenhe() {
		return shenhe;
	}
	public void setShenhe(int shenhe) {
		this.shenhe = shenhe;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public int getVideoLiveId() {
		return videoLiveId;
	}
	public void setVideoLiveId(int videoLiveId) {
		this.videoLiveId = videoLiveId;
	}
	public int getBuyProductId() {
		return buyProductId;
	}
	public void setBuyProductId(int buyProductId) {
		this.buyProductId = buyProductId;
	}
	public int getIsSysAdmin() {
		return isSysAdmin;
	}
	public void setIsSysAdmin(int isSysAdmin) {
		this.isSysAdmin = isSysAdmin;
	}
	public int getIsRoomAdmin() {
		return isRoomAdmin;
	}
	public void setIsRoomAdmin(int isRoomAdmin) {
		this.isRoomAdmin = isRoomAdmin;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
