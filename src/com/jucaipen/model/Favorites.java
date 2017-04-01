package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ylf          -------------数据已更新
 * 
 *   收藏 视频  /知识
 *
 */
@SuppressWarnings("serial")
public class Favorites implements Serializable {
	/**
	 *  新闻收藏当前页
	 */
	private int pager;
	/**
	 *  新闻收藏信息总页数
	 */
	private int totlePager;
	/**
	 * 收藏id
	 */
	private int id;
	/**
	 * 收藏者id
	 */
	private int uId;
	/**
	 * 视频或知识id
	 */
	private int fk_Id;
	
	private boolean isCharge;
	
	
	private boolean isSpecial;
	
	/**
	 *   是否购买    0   是    1   否    2   过期
	 */
	private int isPurch;
	
	/**
	 * 收藏时间
	 */
	private String date;
	/**
	 * 1为收藏视频 2为收藏知识
	 * 
	 * 
	 */
	private int type;
	
	
	
	
	public int getIsPurch() {
		return isPurch;
	}
	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}
	public boolean isSpecial() {
		return isSpecial;
	}
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
	}
	public int getTotlePager() {
		return totlePager;
	}
	public void setTotlePager(int totlePager) {
		this.totlePager = totlePager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getFk_Id() {
		return fk_Id;
	}
	public void setFk_Id(int fk_Id) {
		this.fk_Id = fk_Id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
