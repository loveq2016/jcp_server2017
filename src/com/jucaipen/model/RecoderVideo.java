package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author 杨朗飞
 *    录播视频
 */
public class RecoderVideo implements Serializable{
	/**
	 *   当前页数
	 */
	private int page;
	
	/**
	 *   总页数
	 */
	private int totlePage;
	/**
	 *   id 
	 */
	private int id;
	
	/**
	 *   是否购买  1  未购买    0  已购买     1  已过期
	 */
	private int isPurch;
	
	/**
	 *   标题
	 */
	private String title;
	
	/**
	 *   直播云id
	 */
	private String yunId;
	
	/**
	 *   讲师id
	 */
	private int teacherId;
	
	/**
	 *   开始时间
	 */
	private String startDate;
	
	/**
	 *   结束时间
	 */
	private String endDate;
	
	/**
	 *   是否收费（1免费，2收费）
	 */
	private int liveIsFree;
	
	/**
	 *   播放价格
	 */
	private int livePrice;
	
	/**
	 *   播放地址1
	 */
	private String liveUrl1;
	/**
	 *   播放地址2
	 */
	private String liveUrl2;
	
	/**
	 *   播放地址3
	 */
	private String liveUrl3;
	
	/**
	 *   持续时间
	 */
	private String duration;
	
	/**
	 *    封面地址
	 */
	private String imageUrl;
	
	/**
	 *   文件大小
	 */
	private String fineLength;
	
	/**
	 *   点赞数
	 */
	private int likeCount;
	
	/**
	 *   点击数
	 */
	private int playCount;
	
	/**
	 *    虚拟点击数 
 	 */
	private int xnPlayCount;
	
	/**
	 *   是否精选（1否  2是）
	 */
	private int isBest;
	
	/**
	 *   插入时间
	 */
	private String insertDate;
	
	/**
	 *   备注
	 */
	private String remark;
	
	/**
	 *   扩展字段1
	 */
	private String ext1;
	
	/**
	 *   扩展字段2
	 */
	private String ext2;
	
	public int getIsPurch() {
		return isPurch;
	}

	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
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

	public RecoderVideo() {
	}

	public RecoderVideo(int id, String title, String yunId, int teacherId,
			String startDate, String endDate, int liveIsFree, int livePrice,
			String liveUrl1, String liveUrl2, String liveUrl3, String duration,
			String imageUrl, String fineLength, int likeCount, int playCount,
			int xnPlayCount, int isBest, String insertDate, String remark,
			String ext1, String ext2) {
		this.id = id;
		this.title = title;
		this.yunId = yunId;
		this.teacherId = teacherId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.liveIsFree = liveIsFree;
		this.livePrice = livePrice;
		this.liveUrl1 = liveUrl1;
		this.liveUrl2 = liveUrl2;
		this.liveUrl3 = liveUrl3;
		this.duration = duration;
		this.imageUrl = imageUrl;
		this.fineLength = fineLength;
		this.likeCount = likeCount;
		this.playCount = playCount;
		this.xnPlayCount = xnPlayCount;
		this.isBest = isBest;
		this.insertDate = insertDate;
		this.remark = remark;
		this.ext1 = ext1;
		this.ext2 = ext2;
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

	public String getYunId() {
		return yunId;
	}

	public void setYunId(String yunId) {
		this.yunId = yunId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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

	public int getLiveIsFree() {
		return liveIsFree;
	}

	public void setLiveIsFree(int liveIsFree) {
		this.liveIsFree = liveIsFree;
	}

	public int getLivePrice() {
		return livePrice;
	}

	public void setLivePrice(int livePrice) {
		this.livePrice = livePrice;
	}

	public String getLiveUrl1() {
		return liveUrl1;
	}

	public void setLiveUrl1(String liveUrl1) {
		this.liveUrl1 = liveUrl1;
	}

	public String getLiveUrl2() {
		return liveUrl2;
	}

	public void setLiveUrl2(String liveUrl2) {
		this.liveUrl2 = liveUrl2;
	}

	public String getLiveUrl3() {
		return liveUrl3;
	}

	public void setLiveUrl3(String liveUrl3) {
		this.liveUrl3 = liveUrl3;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFineLength() {
		return fineLength;
	}

	public void setFineLength(String fineLength) {
		this.fineLength = fineLength;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public int getXnPlayCount() {
		return xnPlayCount;
	}

	public void setXnPlayCount(int xnPlayCount) {
		this.xnPlayCount = xnPlayCount;
	}

	public int getIsBest() {
		return isBest;
	}

	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
}
