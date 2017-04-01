package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF         -------------数据已更新
 * 
 *   新闻总分类
 *
 */
@SuppressWarnings("serial")
public class NewsClass implements Serializable{
	/**
	 * 总分类id
	 */
	private int id;
	/**
	 * 总分类名称
	 */
	private String className;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 排序id
	 */
	private int sortId;
	/**
	 *   连接地址
	 */
	private String linkUrl;
	/**
	 *  父级id
	 */
	private int parentId;
	
	
	public NewsClass(int id, String className, String keyWord,
			String descript, String linkUrl, int sortId) {
		this.id = id;
		this.className = className;
		this.keyWord = keyWord;
		this.linkUrl=linkUrl;
		this.descript = descript;
		this.sortId = sortId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
	

}
