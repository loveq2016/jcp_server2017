package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  -------------新增数据
 *
 *  礼品分类   JCP_LiPin_Class
 */
@SuppressWarnings("serial")
public class GiftClass implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  ClassName
	 */
	private String className;
	/**
	 *  ParentId
	 */
	private int parentId;
	/**
	 *  SortId
	 */
	private int sortId;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}
