package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF
 * 
 *         视频分类                      ----------------数据已更新     5.18
 * 
 */
@SuppressWarnings("serial")
public class VideoType implements Serializable {
	/**
	 *   视频id
	 */
	private int id;
	/**
	 *   关键字
	 */
	private String keyWord;
	/**
	 *   描述
	 */
	private String descript;
	/**
	 *  分类名称
	 */
	private String title;
	/**
	 *   排序id
	 */
	private int sortId;
	
	public VideoType(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}


}
