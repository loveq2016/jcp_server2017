package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  --------------新增数据
 *
 *   我的视频   JCP_MyVideo
 */
@SuppressWarnings("serial")
public class MyVideo implements Serializable{
	private int page;
	private int totlePage;
	
	/**
	 *   是否购买    0    已购买     1    未购买     2   已过期
	 */
	private int isPurch;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  FK_UserId
	 */
	private int userId;
	/**
	 *  FK_VideoId
	 */
	private int videoId;
	
	/**
	 *  购买视频标题
	 */
	private String videoTitle;
	
	/**
	 *  购买视频封面图片
	 */
	private String videoImag;
	
	private String videoUrl;
	
	private int fk_specialId;
	
	private String videoDesc;
	
	private int xnHits;
	
	private int classId;
	
	/**
	 *  InsertDate
	 */
	private String insertDate;
    /**
     *  Remark
     */
    private String remark;
    /**
     *   是否删除（0否，1是） IsDel
     */
    private int isDel;
    /**
     *  是否停止（0否，1是） IsStop
     */
    private int isStop;
    /**
     *  StartDate
     */
    private String startDate;
    /**
     *  EndDate
     */
    private String endDate;
    
    
    
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public int getIsPurch() {
		return isPurch;
	}
	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
	}
	public int getXnHits() {
		return xnHits;
	}
	public void setXnHits(int xnHits) {
		this.xnHits = xnHits;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public int getFk_specialId() {
		return fk_specialId;
	}
	public void setFk_specialId(int fk_specialId) {
		this.fk_specialId = fk_specialId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoImag() {
		return videoImag;
	}
	public void setVideoImag(String videoImag) {
		this.videoImag = videoImag;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
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
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getIsStop() {
		return isStop;
	}
	public void setIsStop(int isStop) {
		this.isStop = isStop;
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
}
