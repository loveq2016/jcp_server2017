package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *
 *    登录实体类
 */
@SuppressWarnings("serial")
public class LoginJson implements Serializable{
	//{"IsLogin":false,"ActionId":0,"Result":false,"Msg":"密码错误"}
	/**
	 *   是否登录、注册成功
	 */
	private boolean IsLogin;
	/**
	 * 用户ID
	 */
	private int ActionId;
	/**
	 * 是否成功
	 */
	private boolean Result;
	/**
	 * 备注信息
	 */
	private String Msg;
	public LoginJson(boolean isLogin, int actionId, boolean result, String msg) {
		super();
		IsLogin = isLogin;
		ActionId = actionId;
		Result = result;
		Msg = msg;
	}
	public boolean isIsLogin() {
		return IsLogin;
	}
	public void setIsLogin(boolean isLogin) {
		IsLogin = isLogin;
	}
	public int getActionId() {
		return ActionId;
	}
	public void setActionId(int actionId) {
		ActionId = actionId;
	}
	public boolean isResult() {
		return Result;
	}
	public void setResult(boolean result) {
		Result = result;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
	
	

}
