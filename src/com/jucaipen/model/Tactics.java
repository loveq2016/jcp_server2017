package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *  战法
 */
@SuppressWarnings("serial")
public class Tactics implements Serializable{
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
	 *  描述
	 */
	private String desc;
	/**
	 *  订阅人数
	 */
	private int orderNum;
	/**
	 *  封面图片
	 */
	private String imageUrl;
	
	/**
	 *  是否订阅   0     是        1  否        2  过期
	 */
	private int isOrder;
	/**
	 *  讲师id
	 */
	private int teacherId;
	/**
	 *  添加时间
	 */
	private String insertDate;
	/**
	 * 是否一年付款 0是 1否
	 */
	private int isYear;
	/**
	 * 半年价格
	 */
	private int halfYearsMoney;
	/**
	 *  三个月价格
	 */
	private int threeMonthmoney;
	/**
	 *  一年价格
	 */
	public int YearsMoney;
	/**
	 *  每月费用
	 */
	private int mothMoney;
	/**
	 *  是否删除 0正常 1删除
	 */
	private int isDel;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public int getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(int isOrder) {
		this.isOrder = isOrder;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsYear() {
		return isYear;
	}
	public void setIsYear(int isYear) {
		this.isYear = isYear;
	}
	public int getHalfYearsMoney() {
		return halfYearsMoney;
	}
	public void setHalfYearsMoney(int halfYearsMoney) {
		this.halfYearsMoney = halfYearsMoney;
	}
	public int getThreeMonthmoney() {
		return threeMonthmoney;
	}
	public void setThreeMonthmoney(int threeMonthmoney) {
		this.threeMonthmoney = threeMonthmoney;
	}
	public int getYearsMoney() {
		return YearsMoney;
	}
	public void setYearsMoney(int yearsMoney) {
		YearsMoney = yearsMoney;
	}
	public int getMothMoney() {
		return mothMoney;
	}
	public void setMothMoney(int mothMoney) {
		this.mothMoney = mothMoney;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
