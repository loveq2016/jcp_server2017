package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator       --------------数据已更新
 * 
 *   意见
 *
 */
@SuppressWarnings("serial")
public class FeedBack implements Serializable{
	/**
	 * 
	 */
	private int id;
	/**
	 * 用户id(未登录用户Id为0)
	 */
	private int userId;
	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 联系电话
	 */
	private String mobileNum;
	/**
	 * 意见内容
	 */
	private String body;
	/**
	 * 插入时间
	 */
	private String insertDate;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 状态（已处理（1）或者未处理（0））
	 */
	private int type;
	/**
	 *  处理结果
	 */
	private String handleResult;
	/**
	 * 处理时间
	 */
	private String handleDate;
	/**
	 *  处理人
	 */
	private String handleMan;
	
	
	public FeedBack() {
		super();
	}
	public FeedBack(int id, int userId, String trueName, String mobileNum,
			String body, String insertDate, String ip, int type) {
		this.id = id;
		this.userId = userId;
		this.trueName = trueName;
		this.mobileNum = mobileNum;
		this.body = body;
		this.insertDate = insertDate;
		this.ip = ip;
		this.type = type;
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
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public String getHandleMan() {
		return handleMan;
	}
	public void setHandleMan(String handleMan) {
		this.handleMan = handleMan;
	}

}
