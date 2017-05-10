package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *  //   opType    1   上线       2    聊天      3   下线
	// {"fromId":1,"liveId:1","opType":2,"toId":0,msg:"hello"}
 */
public class ChatMsgObject implements Serializable{
	private int id;
	private int fromId;
	private String fromName;
	private int shenhe;
	private int receiverId;
	private String receiverName;
	private int isSysAdmin;
	private int isRoomAdmin;
	private int isTeacher;
	private int isShouhu;
	private int isChatAdmin;
	private int userLeavel;
	private int serverId;
	private String sendDate;
	private String sendFace;
	private int liveId;
	private int opType;
	private int toId;
	private String msg;
	private int teacherId;
	private int msgType;
	
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getSendFace() {
		return sendFace;
	}
	public void setSendFace(String sendFace) {
		this.sendFace = sendFace;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
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
	public int getUserLeavel() {
		return userLeavel;
	}
	public void setUserLeavel(int userLeavel) {
		this.userLeavel = userLeavel;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public int getLiveId() {
		return liveId;
	}
	public void setLiveId(int liveId) {
		this.liveId = liveId;
	}
	public int getOpType() {
		return opType;
	}
	public void setOpType(int opType) {
		this.opType = opType;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
