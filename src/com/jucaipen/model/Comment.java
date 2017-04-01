package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ylf      -------------数据已更新   JCP_LogOrLive_Comm
 * 
 *   观点、文字直播 评论      
 */
@SuppressWarnings("serial")
public class Comment implements Serializable {
	/**
	 * 评论总页数
	 */
	private int totlePager;
	
	/**
	 * 评论当前页数
	 */
	private int pager;
	/**
	 * 评论id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int userId;
	
	private String userName;
	
	private String userFace;
	
	private int userLeavel;
	/**
	 * 评论内容
	 */
	private String bodys;
	/**
	 * 评论类型（1观点日志，2文字直播）
	 */
	private int commType;
	/**
	 * 评论时间
	 */
	private String insertDate;
	/**
	 * 是否显示（1不显示   2显示）
	 */
	private int isShow;
	/**
	 * 获赞数量
	 */
	private int goods;
	/**
	 * 回复数量
	 */
	private int repCount;
	/**
	 *  观点或文字直播id
	 */
	private int logOrLiveId;
	
	private String fkName;
	
	private String fkDesc;
	
	/**
	 *   回复评论的id
	 */
	private int parentId;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFace() {
		return userFace;
	}
	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}
	public int getUserLeavel() {
		return userLeavel;
	}
	public void setUserLeavel(int userLeavel) {
		this.userLeavel = userLeavel;
	}
	public String getFkDesc() {
		return fkDesc;
	}
	public void setFkDesc(String fkDesc) {
		this.fkDesc = fkDesc;
	}
	public String getFkName() {
		return fkName;
	}
	public void setFkName(String fkName) {
		this.fkName = fkName;
	}
	public int getTotlePager() {
		return totlePager;
	}
	public void setTotlePager(int totlePager) {
		this.totlePager = totlePager;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
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
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public int getCommType() {
		return commType;
	}
	public void setCommType(int commType) {
		this.commType = commType;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getRepCount() {
		return repCount;
	}
	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}
	public int getLogOrLiveId() {
		return logOrLiveId;
	}
	public void setLogOrLiveId(int logOrLiveId) {
		this.logOrLiveId = logOrLiveId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
