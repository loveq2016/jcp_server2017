package com.jucaipen.model;

import java.io.Serializable;
/**
 * @author ylf           ---------数据已更新    5.18
 * 
 *   视频
 *
 */
@SuppressWarnings("serial")
public class Video implements Serializable{
	/**
	 *   视频id
	 */
	private int id;
	/**
	 *   播放视频类型     1   趣看     2   乐视
	 */
	private int playType;
	
	private String vid;
	
	private String uid;
	/**
	 *  是否收费
	 */
	private boolean isCharge;
	
	/**
	 * 视频标题
	 */
	private String title;
	/**
	 *    是否购买    0  有购买     1    未购买      2  已过期
	 */
	private int isPurch;
	/**
	 *   是否属于专辑
	 */
	private boolean isSpecial;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 点击量
	 */
	private int HitCount;
	/**
	 *  虚拟播放数
	 */
	private int XnHitCount;
	/**
	 * 插入时间
	 */
	private String InsertDate;
	/**
	 * 视频发布日期
	 */
	private String VideoDate;
	/**
	 * 分类id
	 */
	private int classId;
	/**
	 *   视频分类   0 免费    1  付费
	 */
	private int videoType;
	/**
	 *   应付金额
	 */
	private String allMoney;
	/**
	 *   实付金额
	 */
	private String payMoney;
	/**
	 *  所属讲师
	 */
	private int teacherId;
	/**
	 * 是否置顶　（　１　置顶　　０　非置顶　）
	 */
	private int IsTop;
	/**
	 *   是否精选   0  否   1  是
	 */
	private int isBest;
	/**
	 *  点赞数
	 */
	private int goods;
	/**
	 * 视频URL
	 */
	private String VideoUrl;
	/**
	 * 评论数
	 */
	private int CommCount;
	/**
	 * 关键字
	 */
	private String keyWords;
	/**
	 * 图片
	 */
	private String Images;
	/**
	 *  所属视频类型
	 */
	private int typeId;
	/**
	 *  所属内容分类，关联新闻大类
	 */
	private int contentId;
	/**
	 * 是否是本站视频　（　１　是　０　不是　）
	 */
	private int IsMySiteVideo;
	/**
	 *  所属专辑
	 */
	private int pecialId;
	/**
	 *  排序id
	 */
	private int sortId;
	/**
	 *   静态页面URL
	 */
	private String htmlUrl;	
	/**
	 * 　是否首页显示　（１　首页　　１　非首页显示）
	 */
	private int IsIndex;
	/**
	 * 是否推荐　（　１　推荐　　　　０　非推荐　）
	 */
	private int IsTuijian;
	
	/**
	 *   月付价格
	 */
	private int mothPrice;
	/**
	 *   季付价格
	 */
	private int quartPrice;
	/**
	 * 年付价格
	 */
	private int yearPrice;
	private int page;
	private int totlePage;
	
	
	public Video() {
		super();
	}
	public Video(int id, String title) {
		this.id = id;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayType() {
		return playType;
	}
	public void setPlayType(int playType) {
		this.playType = playType;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsPurch() {
		return isPurch;
	}
	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
	}
	public boolean isSpecial() {
		return isSpecial;
	}
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getHitCount() {
		return HitCount;
	}
	public void setHitCount(int hitCount) {
		HitCount = hitCount;
	}
	public int getXnHitCount() {
		return XnHitCount;
	}
	public void setXnHitCount(int xnHitCount) {
		XnHitCount = xnHitCount;
	}
	public String getInsertDate() {
		return InsertDate;
	}
	public void setInsertDate(String insertDate) {
		InsertDate = insertDate;
	}
	public String getVideoDate() {
		return VideoDate;
	}
	public void setVideoDate(String videoDate) {
		VideoDate = videoDate;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getVideoType() {
		return videoType;
	}
	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}
	public String getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getIsTop() {
		return IsTop;
	}
	public void setIsTop(int isTop) {
		IsTop = isTop;
	}
	public int getIsBest() {
		return isBest;
	}
	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public String getVideoUrl() {
		return VideoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		VideoUrl = videoUrl;
	}
	public int getCommCount() {
		return CommCount;
	}
	public void setCommCount(int commCount) {
		CommCount = commCount;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getImages() {
		return Images;
	}
	public void setImages(String images) {
		Images = images;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getIsMySiteVideo() {
		return IsMySiteVideo;
	}
	public void setIsMySiteVideo(int isMySiteVideo) {
		IsMySiteVideo = isMySiteVideo;
	}
	public int getPecialId() {
		return pecialId;
	}
	public void setPecialId(int pecialId) {
		this.pecialId = pecialId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public int getIsIndex() {
		return IsIndex;
	}
	public void setIsIndex(int isIndex) {
		IsIndex = isIndex;
	}
	public int getIsTuijian() {
		return IsTuijian;
	}
	public void setIsTuijian(int isTuijian) {
		IsTuijian = isTuijian;
	}
	public int getMothPrice() {
		return mothPrice;
	}
	public void setMothPrice(int mothPrice) {
		this.mothPrice = mothPrice;
	}
	public int getQuartPrice() {
		return quartPrice;
	}
	public void setQuartPrice(int quartPrice) {
		this.quartPrice = quartPrice;
	}
	public int getYearPrice() {
		return yearPrice;
	}
	public void setYearPrice(int yearPrice) {
		this.yearPrice = yearPrice;
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
