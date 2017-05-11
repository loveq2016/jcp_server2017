package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 *         APK 文件信息
 * 
 */
@SuppressWarnings("serial")
public class ApkInfo implements Serializable {
	/**
	 * APK包名
	 */
	private String pkgName;
	/**
	 * APK版本号
	 */
	private int versionCode;
	/**
	 * APK 版本id
	 */
	private int id;
	/**
	 *  版本名称
	 */
	private String vsionName;
	/**
	 *  APK下载路径
	 */
	private String apkPath;
	/**
	 * APK更新时间
	 */
	private String updateDate;
	/**
	 *   是否强制更新    0  是     1  否
	 */
	private int isForce;
	
	/**
	 *   备注说明
	 */
	private String remark;
	private  String length;
	
	public String webUrl;
	
	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}



	public int getIsForce() {
		return isForce;
	}



	public void setIsForce(int isForce) {
		this.isForce = isForce;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}



	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	public String getVsionName() {
		return vsionName;
	}

	public void setVsionName(String vsionName) {
		this.vsionName = vsionName;
	}

	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
