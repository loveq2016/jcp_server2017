package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF         ---------------数据已更新
 * 
 *         表情列表
 * 
 */
@SuppressWarnings("serial")
public class ExpressionInfo implements Serializable {
	/**
	 * 表情id
	 */
	private int id;
	/**
	 * 分类id
	 */
	private int classId;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 表情连接地址
	 */
	private String faceurl;
	/**
	 * 表情排序
	 */
	private int sortId;

	public ExpressionInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}


	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}


}
