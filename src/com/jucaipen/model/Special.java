package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator ------------新增数据
 * 
 *         专辑 JCP_Special
 */
@SuppressWarnings("serial")
public class Special implements Serializable {
	private int page;
	private int totlePage;
	/**
	 * Id
	 */
	private int id;
	/**
	 *  SpecialName
	 */
	private String name;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  Description
	 */
	private String description;
	/**
	 *  SortId
	 */
	private int sortId;
	/**
	 *  IsDel  是否删除（0正常，1已删除）
	 */
	private int isDel;
	
	/**
	 *  IsFree  是否为付费专辑（1否，2是） 
	 */
	private int isFree;
	/**
	 *  AmountMoney 购买专辑应付金额
	 */
	private int allMoney;
	/**
	 *  ShiFuMoney   实际支付金额
	 */
	private int mothMoney;
	
	private int quarBills;
	
	private int yearBills;
	
	public int getQuarBills() {
		return quarBills;
	}
	public void setQuarBills(int quarBills) {
		this.quarBills = quarBills;
	}
	public int getYearBills() {
		return yearBills;
	}
	public void setYearBills(int yearBills) {
		this.yearBills = yearBills;
	}
	public void setAllMoney(int allMoney) {
		this.allMoney = allMoney;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public double getAllMoney() {
		return allMoney;
	}
	public int getMothMoney() {
		return mothMoney;
	}
	public void setMothMoney(int mothMoney) {
		this.mothMoney = mothMoney;
	}
}
