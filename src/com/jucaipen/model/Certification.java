package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator    ------------------新增数据
 *
 *   资格证书     JCP_Certification
 */
public class Certification implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  证书种类名称   TypeName
	 */
	private String typeName;
	/**
	 *  备注说明   Remark
	 */
	private String remark;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
