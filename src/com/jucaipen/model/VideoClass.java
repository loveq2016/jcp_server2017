package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *   视频分类                  --------------------------已更新     5.18
 */
@SuppressWarnings("serial")
public class VideoClass implements Serializable{
	private int id;
	/**
	 *  分类标题
	 */
	private String typeTitle;
	/**
	 *  关键字
	 */
	private String keyWord;
	/**
	 *  描述
	 */
	private String descript;
	/**
	 * 排序id
	 */
	private int sortId;
	/**
	 * 父级id
	 */
	private int parentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeTitle() {
		return typeTitle;
	}
	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
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
}
