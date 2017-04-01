package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF         ----------------------数据已更新
 * 
 *    邮箱信息
 *
 */
@SuppressWarnings("serial")
public class MailMessage implements Serializable{
	/**
	 * 信息id
	 */
	private int id;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 激活码
	 */
	private String actionCode;
	/**
	 * 消息内容
	 */
	private String messageContent;
	/**
	 * 状态:(1：未验证；2：已验证；3：已失效；4：删除)
	 */
	private int msgType;
	/**
	 * 发送时间
	 */
	private String sendDate;
	/**
	 * 验证时间
	 */
	private String checkDate;
	/**
	 * 用户id
	 */
	private int userId;
	
	public MailMessage() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
