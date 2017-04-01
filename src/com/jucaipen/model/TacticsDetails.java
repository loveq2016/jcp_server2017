package com.jucaipen.model;

/**
 * @author Administrator
 *
 *  战法详细信息  
 */
public class TacticsDetails {
	/**
	 *  id
	 */
	private int id;
	/**
	 * 详细标题
	 */
	private String title;
	/**
	 *  详细信息
	 */
	private String body;
	/**
	 *  关联战法ID
	 */
	private int fkId;
	/**
	 *  添加时间
	 */
	private String insertDate;
	/**
	 * 是否删除 0正常 1删除
	 */
	private int isDel;
	private int page;
	private int totlePage;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getFkId() {
		return fkId;
	}
	public void setFkId(int fkId) {
		this.fkId = fkId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
