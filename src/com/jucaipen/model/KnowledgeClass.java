package com.jucaipen.model;

/**
 * @author Administrator   -------------新增数据
 *
 *  知识分类   
 */
public class KnowledgeClass {
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  ClassName
	 */
	private String className;
	/**
	 *  KeyWord
	 */
	private String keyWord;
	/**
	 *  Description
	 */
	private String desc;
	/**
	 *   分类id   SortId
	 */
	private int sortId;
	/**
	 *   ParentId
	 */
	private int parentId;
	/**
	 *  LinkUrl
	 */
	private String linkUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
