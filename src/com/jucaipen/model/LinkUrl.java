package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   ---------------新增数据
 *
 *  连接URL  JCP_LinkUrl
 */
@SuppressWarnings("serial")
public class LinkUrl implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  LinkName
	 */
	private String linkName;
	/**
	 *  LinkUrl
	 */
	private String linkUrl;
	/**
	 *  SortId
	 */
	private int sortId;
	/**
	 *  Remark
	 */
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
