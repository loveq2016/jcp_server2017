package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.ApkInfo;

public interface ApkInfoDao {

	/**
	 * @param id
	 * @return  获取APK信息
	 */
	public ApkInfo findApkInfoById(int id);

	/**
	 * @return  查询所有APK版本信息
	 */
	public List<ApkInfo> findAll();

	/**
	 * @return  添加APK
	 */
	public int insertApkInfo(ApkInfo info);

	/**
	 * @return   删除APK
	 */
	public int deleteApkInfo();
	
	public int updateUrl(int code,int type,String url);
	
	/**
	 * @param code
	 * @return  根据  code 获取版本信息
	 */
	public ApkInfo findInfoByCode(int code);
	/**
	 * @return  获取APK最大id
	 */
	public int querrtMaxId();
}
