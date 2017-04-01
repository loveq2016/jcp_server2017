package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ylf
 * 
 *   信息来源      ---------------数据已更新
 *
 */
@SuppressWarnings("serial")
public class ResourceSources implements Serializable {
	/**
	 * 信息来源当前页
	 */
	private int pager;
	/**
	 * 信息来源总页数
	 */
	private int totlePager;
	/**
	 * 来源id
	 */
	private int id;
	/**
	 * 信息来源名称
	 */
	private String fromName;
	/**
	 * 信息来源网页
	 */
	private String fromUrl;
	/**
	 * 排序
	 */
	private int sortId;
	
	public ResourceSources(int id, String fromName, String fromUrl, int sortId) {
		this.id = id;
		this.fromName = fromName;
		this.fromUrl = fromUrl;
		this.sortId = sortId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	public String getFromUrl() {
		return fromUrl;
	}
	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
	}
	public int getTotlePager() {
		return totlePager;
	}
	public void setTotlePager(int totlePager) {
		this.totlePager = totlePager;
	}
	
	

}
