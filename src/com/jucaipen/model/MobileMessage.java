package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF    ---------数据已更新
 * 
 *   短信信息
 *
 */
@SuppressWarnings("serial")
public class MobileMessage implements Serializable{
	/**
	 *  短信id
	 */
	private int id;
	/**
	 *  发送的手机号
	 */
	private String telPhone;
	/**
	 * 激活码
	 */
	private String actionCode;
	/**
	 *  短信内容
	 */
	private String messageContent;
	/**
	 *  发送时间
	 */
	private String sendDate;
	/**
	 *  发送设备   1 网站    2 APP
	 */
	private int sendDev;
	/**
	 *  短信类型     1  --未验证     2  ---以验证     3  ---失效     4  ---删除
	 */
	private int msgType;
	/**
	 * 验证时间
	 */
	private String checkDate;
	/**
	 * 相应时间
	 */
	private String resptime;
	/**
	 * 相应状态
	 */
	private int respstatus;
	/**
	 * 返回信息id
	 */
	private String msgid;
	/**
	 * 发送ip
	 */
	private String SendIp;
	/**
	 * 备注
	 */
	private String remark;
	
	public MobileMessage() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public int getSendDev() {
		return sendDev;
	}
	public void setSendDev(int sendDev) {
		this.sendDev = sendDev;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getResptime() {
		return resptime;
	}
	public void setResptime(String resptime) {
		this.resptime = resptime;
	}
	public int getRespstatus() {
		return respstatus;
	}
	public void setRespstatus(int respstatus) {
		this.respstatus = respstatus;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getSendIp() {
		return SendIp;
	}
	public void setSendIp(String sendIp) {
		SendIp = sendIp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
