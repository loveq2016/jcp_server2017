package com.jucaipen.model;

import com.jucaipen.utils.StringUtil;

public class ClientOsInfo {
	/***
	 * 比如 Android_3.0
	 */
	private String osTypeVersion;
	/***
	 * Pad或Phone
	 */
	private String deviceType;
	/***
	 * 只是版本号,例如"4.1.1"
	 */
	private String version;
	
	/**
	 *  手机基于平台
	 */
	private String mainSys;
	/**
	 *  手机型号
	 */
	private String phoneType;
	
	public String getMainSys() {
		return mainSys;
	}
	public void setMainSys(String mainSys) {
		this.mainSys = mainSys;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getOsTypeVersion() {
		return osTypeVersion;
	}
	public void setOsTypeVersion(String osTypeVersion) {
		this.osTypeVersion = osTypeVersion;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	/***
	 * 是否是移动设备
	 * @return
	 */
	public boolean isMobile(){
		return (!StringUtil.isNullOrEmpty(this.deviceType));
	}
}
