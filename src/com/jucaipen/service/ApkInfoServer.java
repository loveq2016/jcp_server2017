package com.jucaipen.service;

import com.jucaipen.dao.ApkInfoDao;
import com.jucaipen.daoimp.ApkInfoImp;
import com.jucaipen.model.ApkInfo;

public class ApkInfoServer {
	/**
	 * @param uId
	 * @return 获取最新版本apk信息
	 */
	public static ApkInfo findLastApkInfo(int uId) {
		ApkInfoDao dao = new ApkInfoImp();
		return dao.findApkInfoById(uId);
	}
	
	public static ApkInfo   findInfoByCode(int code){
		ApkInfoDao dao = new ApkInfoImp();
		return dao.findInfoByCode(code);
	}
	
	/**
	 * @param code
	 * @param type
	 * @param url
	 * @return  更新版本号  url    1    webUrl     2   apkUrl
	 */
	public static int updateUrl(int code,int type,String url){
		ApkInfoDao dao = new ApkInfoImp();
		return dao.updateUrl(code, type, url);
	}
		

	/**
	 * @param info
	 * @return 添加APK信息
	 */
	public static int insertApkInfo(ApkInfo info) {
		ApkInfoDao dao = new ApkInfoImp();
		return dao.insertApkInfo(info);
	}

	/**
	 * @return  获取APK信息最大的id
	 */
	public static int querryMaxId(){
		ApkInfoDao dao=new ApkInfoImp();
		return dao.querrtMaxId();
	}

}
