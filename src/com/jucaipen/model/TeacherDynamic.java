package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   --------------数据已更新
 * 
 *   讲师动态
 *
 */
@SuppressWarnings("serial")
public class TeacherDynamic implements Serializable{
	/**
	 *  讲师动态id
	 */
	private int id;
	/**
	 * 关联日志ID或直播观点ID或回答问题ID
	 */
	private int relateId;
	/**
	 * 动态类型（0发布日志，1发布直播观点，2回答问题）
	 */
	private int type;
	/**
	 * 发布动态时间
	 */
	private String insertDate;
	/**
	 * 讲师id
	 */
	private int teacherId;
	
	public TeacherDynamic(int id, int relateId, int type, String insertDate,
			int teacherId) {
		this.id = id;
		this.relateId = relateId;
		this.type = type;
		this.insertDate = insertDate;
		this.teacherId = teacherId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRelateId() {
		return relateId;
	}
	public void setRelateId(int relateId) {
		this.relateId = relateId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

}
