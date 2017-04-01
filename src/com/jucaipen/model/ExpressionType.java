package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF     -----------------数据已更新
 * 
 *    表情分类信息
 */
@SuppressWarnings("serial")
public class ExpressionType implements Serializable {
	/**
	 *   表情分类id
	 */
	private int id;
	/**
	 *  分类名称
	 */
	private String className;
	/**
	 *  排序
	 */
	private int sortId;
	/**
	 *  是否删除    0否   1   是
	 */
	private int isDel;
	public ExpressionType() {
		super();
	}
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
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
