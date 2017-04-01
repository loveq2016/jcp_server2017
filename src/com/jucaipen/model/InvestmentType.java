package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  ------------------新增数据
 *  
 *  投资风格    JCP_InvestmentType
 */
@SuppressWarnings("serial")
public class InvestmentType implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  TypeName
	 */
	private String typeName;
	/**
	 * SortId
	 */
	private int sortId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}
