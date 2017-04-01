package com.jucaipen.model;

/**
 * @author Administrator
 *
 *  新闻一级分类
 */
public class NewsBigClass {
	
	private int id;
	
	private String bigName;
	
	private String keyWord;
	
	private String desc;
	
	
	private String tempName;
	
	private String filePath;
	
	private String linkUrl;
	
	private int pxId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
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

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getPxId() {
		return pxId;
	}

	public void setPxId(int pxId) {
		this.pxId = pxId;
	}

}
