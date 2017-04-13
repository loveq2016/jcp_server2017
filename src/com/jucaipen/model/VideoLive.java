package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF
 *
 *
 *   直播室                     ------------------------------数据已更新    5.18
 */
/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class VideoLive implements Serializable{
	private int page;
	private int totlePage;
	
	/**
	 * 是否开通视频直播功能
	 */  
	private boolean isLiveVideo;
	
	/**
	 *  录播id
	 */
	private int recoderId;
	
	/**
	 *   是否收费
	 */
	private boolean isCharge;
	
	/**
	 *   直播价格
	 */
	private int livePrice;
	/**
	 *   是否购买  0   已购买   1   未购买
	 */
	private int isPurch;
	/**
	 *  直播id
	 */
	private int id;
	/**
	 * 直播标题
	 */
	private String title;
	
	private int ownJucaiBills;
	/**
	 * 直播关键字
	 */
	private String keyWord;
	
	/**
	 *   聊天室id
	 */
	private int roomId;
	
	/**
	 *  描述
	 */
	private String descript;
	/**
	 *  直播内容所属分类
	 */
	private int classId;
	/**
	 *  直播URL
	 */
	private String videoUrl;
	/**
	 *  直播封面
	 */
	private String videoImage;
	/**
	 *  直播讲师id
	 */
	private int teacherId;
	
	private String teacherName;
	
	
	/**
	 *   讲师头像
	 */
	private String teacherFace;
	/**
	 * 直播状态 1未开始 2进行中 3已结束
	 */
	private int isEnd;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 *  结束时间
	 */
	private String endDate;
	/**
	 *  直播人气
	 */
	private int renQi;
	
	/**
	 *   虚拟人气
	 */
	private int xnRenQi;
	
	/**
	 *   是否开通守护
	 */
	private boolean isGradian;
	
	public int getXnRenQi() {
		return xnRenQi;
	}
	public void setXnRenQi(int xnRenQi) {
		this.xnRenQi = xnRenQi;
	}
	public int getRecoderId() {
		return recoderId;
	}
	public void setRecoderId(int recoderId) {
		this.recoderId = recoderId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getTeacherFace() {
		return teacherFace;
	}
	public void setTeacherFace(String teacherFace) {
		this.teacherFace = teacherFace;
	}
	public boolean isGradian() {
		return isGradian;
	}
	public void setGradian(boolean isGradian) {
		this.isGradian = isGradian;
	}
	public int getOwnJucaiBills() {
		return ownJucaiBills;
	}
	public void setOwnJucaiBills(int ownJucaiBills) {
		this.ownJucaiBills = ownJucaiBills;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}
	public int getLivePrice() {
		return livePrice;
	}
	public void setLivePrice(int livePrice) {
		this.livePrice = livePrice;
	}
	public int getIsPurch() {
		return isPurch;
	}
	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
	}
	public boolean isLiveVideo() {
		return isLiveVideo;
	}
	public void setLiveVideo(boolean isLiveVideo) {
		this.isLiveVideo = isLiveVideo;
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
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoImage() {
		return videoImage;
	}
	public void setVideoImage(String videoImage) {
		this.videoImage = videoImage;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
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
	public int getRenQi() {
		return renQi;
	}
	public void setRenQi(int renQi) {
		this.renQi = renQi;
	}
}
