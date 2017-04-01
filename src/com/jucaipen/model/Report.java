package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  -------------新增数据
 *
 *  举报   JCP_Report 
 */
@SuppressWarnings("serial")
public class Report implements Serializable{
	private int page;
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 * 举报内容  bodys
	 */
	private String bodys;
	/**
	 *  举报内容所属 1日志 2问答 3观点 4视频直播   Fk_type
	 */
	private int type;
	/**
	 *  Fk_Id   举报所属id
	 */
	private int fk_id;
	/**
	 *  UserId
	 */
	private int userId;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  IP
	 */
	private String ip;
	/**
	 *  是否处理(0未处理，1已处理)  Ishandle
	 */
	private int ishandle;
	/**
	 *  handleRemabrk
	 */
	private String handleRemabrk;
	/**
	 * handleDate
	 */
	private String handleDate;
	/**
	 * 处理人
	 */
	private String handleRen;
	
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
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getFk_id() {
		return fk_id;
	}
	public void setFk_id(int fk_id) {
		this.fk_id = fk_id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getIshandle() {
		return ishandle;
	}
	public void setIshandle(int ishandle) {
		this.ishandle = ishandle;
	}
	public String getHandleRemabrk() {
		return handleRemabrk;
	}
	public void setHandleRemabrk(String handleRemabrk) {
		this.handleRemabrk = handleRemabrk;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public String getHandleRen() {
		return handleRen;
	}
	public void setHandleRen(String handleRen) {
		this.handleRen = handleRen;
	}

}
