package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   -------------------新增数据
 *
 *  礼品 商店    JCP_LiPin 
 */
@SuppressWarnings("serial")
public class Gifts implements Serializable{
	/**
	 *  当前页数 
	 */
	private int page;
	/**
	 *  总页数
	 */
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  礼品名称  Title
	 */
	private String title;
	/**
	 * 礼品分类  ClassId
	 */
	private int classId;
	/**
	 *  礼品价格  Price
	 */
	private int price;
	/**
	 *  单位  Company
	 */
	private String company;
	/**
	 * 缩略图 Thumbnail
	 */
	private String thumbnail;
	/**
	 * 原始图   ImagesUrl
	 */
	private String imageUrl;
	/**
	 *  描述  Bodys
	 */
	private String desc;
	/**
	 *  销售数量  SaleCount
	 */
	private int saleCount;
	/**
	 * 销售状态（1：正常销售；2：暂停销售；3：暂无库存）   SaleType
	 */
	private int saleState;
	/**
	 * 是否删除(0正常，1已删除）  IsDel
	 */
	private int isDel;
	/**
	 *  是否置顶（0不置顶，1置顶）  IsTop
	 */
	private int isTop;
	/**
	 * 是否推荐（0不推荐，1推荐）   IsTuiJian
	 */
	private int isRecomm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	public int getSaleState() {
		return saleState;
	}
	public void setSaleState(int saleState) {
		this.saleState = saleState;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public int getIsRecomm() {
		return isRecomm;
	}
	public void setIsRecomm(int isRecomm) {
		this.isRecomm = isRecomm;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotlePage() {
		return totlePage;
	}
	public void setTotlePage(int totlePage) {
		this.totlePage = totlePage;
	}
}
