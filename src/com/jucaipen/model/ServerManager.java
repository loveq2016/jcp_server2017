package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 *   服务器管理员
 *
 */
@SuppressWarnings("serial")
public class ServerManager implements Serializable{
	/**
	 *   管理者id
	 */
	private int id;
	/**
	 * 管理者姓名
	 */
	private String name;
	/**
	 * 管理者手机号
	 */
	private String telPhone;
	/**
	 * 管理者用户名
	 */
	private String userName;
	/**
	 * 管理者登录密码
	 */
	private String password;
	public ServerManager() {
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
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
