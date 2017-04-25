package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ���ʷ�
 * 
 *     �ȼ�
 */
public class Grade implements Serializable{
	private static final long serialVersionUID = -6836715504703559801L;

	/**
	 *   id
	 */
	private int id;
	
	/**
	 *   �ȼ�����
	 */
	private String   gradeName;
	
	/**
	 *   �ȼ�ͼ��
	 */
	private String  gradeFace;
	
	/**
	 *    �ȼ�
	 */
	private int grade;
	
	/**
	 *   ��С����
	 */
	private int minIntegeral;
	/**
	 *   ������
	 */
	private  int maxIntegeral;
	
	/**
	 *   ����id
	 */
	private int sortId;
	
	/**
	 *    ����ʱ��
	 */ 
	private String insertDate;
	
	
	/**
	 *   ��ע��Ϣ
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
