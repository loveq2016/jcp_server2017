package com.jucaipen.model;

/**
 * @author Administrator
 *
 *  Ö±²¥À¸Ä¿
 */
public class VideoColumn {
	/**
	 *  id 
	 */
	private int id;
	/**
	 * À¸Ä¿Ãû³Æ
	 */
	private String name;
	/**
	 *  ¹Ø¼ü´Ê
	 */
	private String keyWord;
	/**
	 * ÃèÊö
	 */
	private String desc;
	/**
	 *  ÅÅÐòid
	 */
	private int sortId;
	/**
	 *  ÊÇ·ñÉ¾³ý 0Õý³£ 1É¾³ý
	 */
	private int isDel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
