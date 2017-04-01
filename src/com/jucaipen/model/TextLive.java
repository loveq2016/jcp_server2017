package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 *   文字直播信息              -----------------------数据已更新     5.18
 *
 */
@SuppressWarnings("serial")
public class TextLive implements Serializable{
	/**
	 * 文字直播当前页数
	 */
	private int page;
	/**
	 *  文字直播总页数
	 */
	private int totlePage;
	private boolean isCharge;
	
	private int txtPrice;
	
	private int isPurch;
	/**
	 * 直播id
	 */
	private int id;
	/**
	 * 直播标题
	 */
	private String title;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 点赞数量
	 */
	private int goods;
	/**
	 * 
	 */
	private int hits;
	/**
	 *  虚拟点击数
	 */
	private int xnHits;
	/**
	 * 讲师id
	 */
	private int teacherId;
	
	private int ownJucaiBills;
	/**
	 * 是否结束（1未开始，2进行中，3已结束）   
	 */
	private int isEnd;
	
	/**
	 *   是否守护
	 */
	private boolean isGuardian;
	
	
	public boolean isGuardian() {
		return isGuardian;
	}
	public void setGuardian(boolean isGuardian) {
		this.isGuardian = isGuardian;
	}
	public int getOwnJucaiBills() {
		return ownJucaiBills;
	}
	public void setOwnJucaiBills(int ownJucaiBills) {
		this.ownJucaiBills = ownJucaiBills;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}
	public int getTxtPrice() {
		return txtPrice;
	}
	public void setTxtPrice(int txtPrice) {
		this.txtPrice = txtPrice;
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

}
