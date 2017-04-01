package com.jucaipen.model;

/**
 * @author Administrator
 *
 *
 *   广告bean           ----数据已更新   5.18
 */
public class Advertive {
	/**
	 *   广告id
	 */
	private int id;
	/**
	 * 所属幻灯id
	 */
	private int typeId;
	/**
	 *  标题
	 */
	private String title;
	/**
	 * 连接 URL
	 */
	private String linkUrl;
	/**
	 * 图片URL
	 */
	private String imageUrl;
	/**
	 * 排序
	 */
	private int sortId;
	public Advertive(int id, int typeId, String title, String linkUrl,
			String imageUrl, int sortId) {
		this.id = id;
		this.typeId = typeId;
		this.title = title;
		this.linkUrl = linkUrl;
		this.imageUrl = imageUrl;
		this.sortId = sortId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	
	
	

}
