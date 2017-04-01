package com.jucaipen.model;

/**
 * @author Administrator
 *
 *   演播室   JCP_Studio
 */
public class Studio {
	private int page;
	private int totlePage;
	/**
	 *  id
	 */
	private int id;
	/**
	 *  标题
	 */
	private String title;
	/**
	 * 关键词
	 */
	private String keyWord;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 简介
	 */
	private String jianjie;
	/**
	 * 封面图片
	 */
	private String imageUrl;
	/**
	 * 点赞
	 */
	private int goods;
	/**
	 * 人气
	 */
	private int renQi;
	/**
	 *  发布时间
	 */
	private String insertDate;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 *  结束时间
	 */
	private String endDate;
	/**
	 *  原创视频id
	 */
	private int originalId;
	/**
	 *  视频直播id
	 */
	private int videoLiveId;
	/**
	 *  视频栏目id
	 */
	private int videoColumnId;
	/**
	 *  节目地址
	 */
	private String studioUrl;
	/**
	 *  是否结束  1未开始  2进行中 3结束
	 */
	private int isEnd;
	/**
	 *   是否删除 0正常 1删除
	 */
	private int isDel;
	/**
	 *  排序id
	 */
	private int sortId;
	/**
	 *  开播时间 （周一到周末分别对应（1~7））
	 */
	private String beginShowDate;
	
	public String getBeginShowDate() {
		return beginShowDate;
	}
	public void setBeginShowDate(String beginShowDate) {
		this.beginShowDate = beginShowDate;
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
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getRenQi() {
		return renQi;
	}
	public void setRenQi(int renQi) {
		this.renQi = renQi;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public int getVideoLiveId() {
		return videoLiveId;
	}
	public void setVideoLiveId(int videoLiveId) {
		this.videoLiveId = videoLiveId;
	}
	public int getVideoColumnId() {
		return videoColumnId;
	}
	public void setVideoColumnId(int videoColumnId) {
		this.videoColumnId = videoColumnId;
	}
	public String getStudioUrl() {
		return studioUrl;
	}
	public void setStudioUrl(String studioUrl) {
		this.studioUrl = studioUrl;
	}
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}
