package com.jucaipen.model;

/**
 * @author Administrator
 * 
 *         ÑÝ²¥ÊÒ¼Î±ö JCP_StudioGuest
 */
public class StudioGuest {
	private int page;
	private int totlePage;
	/**
	 * id
	 */
	private int id;
	/**
	 *  ¼Î±öÃû³Æ
	 */
	private String name;
	/**
	 * ¼Î±öÍ·Ïñ
	 */
	private String headFace;
	/**
	 * ¼Î±öÍ·ÏÎ
	 */
	private String leavl;
	/**
	 * ¼Î±ö¼ò½é
	 */
	private String jianjie;
	/**
	 * ¼Î±öÉÃ³¤
	 */
	private String hoby;
	/**
	 * ËùÊôÑÝ²¥ÊÒid
	 */
	private int studioId;
	/**
	 * ÅÅÐòid
	 */
	private int sortId;
	
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
	public String getHeadFace() {
		return headFace;
	}
	public void setHeadFace(String headFace) {
		this.headFace = headFace;
	}
	public String getLeavl() {
		return leavl;
	}
	public void setLeavl(String leavl) {
		this.leavl = leavl;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public String getHoby() {
		return hoby;
	}
	public void setHoby(String hoby) {
		this.hoby = hoby;
	}
	public int getStudioId() {
		return studioId;
	}
	public void setStudioId(int studioId) {
		this.studioId = studioId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}
