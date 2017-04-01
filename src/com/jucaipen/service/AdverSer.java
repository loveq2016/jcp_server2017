package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.AdverticeDao;
import com.jucaipen.daoimp.AdverticeImp;
import com.jucaipen.model.Advertive;

public class AdverSer {
	public static List<Advertive> findAll() {
		return null;

	}

	/** 
	 * @param pId
	 * @return   根据幻灯id 获取相应广告
	 */
	public static List<Advertive> findAdverByPid(int pId) {
		AdverticeDao dao = new AdverticeImp();
		return dao.findAdByPid(pId);
	}
	
	/** 
	 * @param pId
	 * @return   根据幻灯id 获取相应广告
	 */
	public static int updateState(int classId, String urls,String state) {
		AdverticeDao dao = new AdverticeImp();
		return dao.updateState(classId, urls, state);
	}

}
