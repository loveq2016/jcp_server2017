package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author 杨朗飞
 * 
 *     等级
 */
public class Grade implements Serializable{
	private static final long serialVersionUID = -6836715504703559801L;

	/**
	 *   id
	 */
	private int id;
	
	/**
	 *   等级名称
	 */
	private String   gradeName;
	
	/**
	 *   等级图标
	 */
	private String  gradeFace;
	
	/**
	 *    等级
	 */
	private int grade;
	
	/**
	 *   最小积分
	 */
	private int minIntegeral;
	/**
	 *   最大积分
	 */
	private  int maxIntegeral;
	
	/**
	 *   排序id
	 */
	private int sortId;
	
	/**
	 *    插入时间
	 */ 
	private String insertDate;
	
	
	/**
	 *   备注信息
	 */
	private String remark;
	
	
	public String getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGradeName() {
		return gradeName;
	}


	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}


	public String getGradeFace() {
		return gradeFace;
	}


	public void setGradeFace(String gradeFace) {
		this.gradeFace = gradeFace;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getMinIntegeral() {
		return minIntegeral;
	}


	public void setMinIntegeral(int minIntegeral) {
		this.minIntegeral = minIntegeral;
	}


	public int getMaxIntegeral() {
		return maxIntegeral;
	}


	public void setMaxIntegeral(int maxIntegeral) {
		this.maxIntegeral = maxIntegeral;
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
