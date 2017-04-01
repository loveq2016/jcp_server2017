package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF     -------------------数据已更新
 * 
 *   登录日志
 *
 */
@SuppressWarnings("serial")
public class LoginLog implements Serializable{
	/**
	 * 登录
	 */
	private int pager;
	/**
	 *  登录日志总页数
	 */
	private int totlePager;
	/**
	 *  登录日志id
	 */
	private int id;
	/**
	 * 登录用户id
	 */
	private int userId;
	/**
	 * 登录账号
	 */
	private String account;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 登录时间
	 */
	private String loginDate;
	/**
	 * 登录浏览器名
	 */
	private String browserName;
	/**
	 * 操作系统
	 */
	private String osName;
	/**
	 * 登录结果  1  失败   0成功
	 */
	private int loginResult;
	/**
	 * 登录设备ip
	 */
	private String loginIp;
	
	/**
	 * 登录描述信息（失败原因）
	 */
	private String remark;
	/**
	 *  登录类型（1PC网站、2手机网站、3App等）
	 */
	private int loginType;
	/**
	 * 登录提交地址
	 */
	private String loginUrl;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public int getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(int loginResult) {
		this.loginResult = loginResult;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	

}
