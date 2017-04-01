package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator      --------------------------数据已更新     5.18
 * 
 * 热门观点
 *
 */
@SuppressWarnings("serial")
public class HotIdea implements Serializable{
	/**
	 * 数据当前页数
	 */
	private int page;
	/**
	 * 数据总页数
	 */
	private int totlePgae;
	/**
	 *  观点id
	 */
	private int id;
	/**
	 * 观点标题
	 */
	private String title;
	/**
	 * 观点内容
	 */
	private String bodys;
	/**
	 * 点赞数量
	 */
	private int goods;
	/**
	 * 点击数
	 */
	private int hits;
	/**
	 * 评论数
	 */
	private int commens;
	
	/**
	 *   自己拥有的聚财币
	 */
	private int ownJucaiBills;
	/**
	 * 发表观点时间
	 */
	private String insertDate;
	/**
	 * 分类id
	 */
	private int classId;
	/**
	 *   获得打赏金额
	 */
	private double rewardMoney;
	/**
	 * 发表观点教师id
	 */
	private int teacherId;
	
	private String teacherName;
	
	private String teacherLeavel;
	
	private String teacherFace;
	
	private int teacherIsV;
	
	/**
	 * 相关图片
	 */
	private String logImage;
	/**
	 *  是否推荐   0   否   1  是
	 */
	private int isTuijian;
	/**
	 *  是否置顶    0  否    1是
	 */
	private int top;
	/**
	 *  是否精选   0否    1是
	 */
	private int isBest;
	/**
	 * 
	 */
	private int isIndex;
	/**
	 *  是否免费
	 */
	private int isFree;
	
	/**
	 *   1  未购买      0 已购买
	 */
	private int isPurch;
	/**
	 *   需要支付聚财币
	 */
	private int jucaiBills; 
	/**
	 * 
	 */
	private String freeBody;
	
	/**
	 *   虚拟阅读数
	 */
	private int xnHits;
	
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
	public String getTeacherLeavel() {
		return teacherLeavel;
	}
	public void setTeacherLeavel(String teacherLeavel) {
		this.teacherLeavel = teacherLeavel;
	}
	public String getTeacherFace() {
		return teacherFace;
	}
	public void setTeacherFace(String teacherFace) {
		this.teacherFace = teacherFace;
	}
	public int getTeacherIsV() {
		return teacherIsV;
	}
	public void setTeacherIsV(int teacherIsV) {
		this.teacherIsV = teacherIsV;
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotlePgae() {
		return totlePgae;
	}
	public void setTotlePgae(int totlePgae) {
		this.totlePgae = totlePgae;
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
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getCommens() {
		return commens;
	}
	public void setCommens(int commens) {
		this.commens = commens;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getLogImage() {
		return logImage;
	}
	public void setLogImage(String logImage) {
		this.logImage = logImage;
	}
	public int getIsTuijian() {
		return isTuijian;
	}
	public void setIsTuijian(int isTuijian) {
		this.isTuijian = isTuijian;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getIsBest() {
		return isBest;
	}
	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public int getJucaiBills() {
		return jucaiBills;
	}
	public void setJucaiBills(int jucaiBills) {
		this.jucaiBills = jucaiBills;
	}
	public String getFreeBody() {
		return freeBody;
	}
	public void setFreeBody(String freeBody) {
		this.freeBody = freeBody;
	}

}
