package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF ---------------数据已更新
 * 
 *    邮箱、短信模版
 *
 */
@SuppressWarnings("serial")
public class MessageModel implements Serializable{
	/**
	 *  模版id
	 */
	private int id;
	/**
	 * 模版类型   1   短信    2   邮件
	 */
	private int modelType;
	/**
	 *  备注
	 */
	private String title;
	/**
	 *  模版内容
	 */
	private String modelContent;
	
	public MessageModel() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModelType() {
		return modelType;
	}
	public void setModelType(int modelType) {
		this.modelType = modelType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModelContent() {
		return modelContent;
	}
	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}
	

}
