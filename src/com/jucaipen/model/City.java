package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ylf          ---------------数据已更新
 * 
 *   城市
 *
 */
@SuppressWarnings("serial")
public class City implements Serializable {
	/**
	 * 城市信息当前页数
	 */
	private int pager;
	/**
	 * 城市信息总页数
	 */
	private int totlePager;
	/**
	 * 城市id
	 */
	private int id;
	/**
	 * 城市所属省份id
	 */
	private int provinceId;
	/**
	 * 城市名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private int sortId;
	/**
	 *  备注
	 */
	private String remark;
	
	public City(int id, int provinceId, String name,
			int sortId, String remark) {
		this.id = id;
		this.provinceId = provinceId;
		this.name = name;
		this.sortId = sortId;
		this.remark = remark;
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
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
