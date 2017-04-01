package com.jucaipen.service;

import com.jucaipen.dao.SharkInfoDao;
import com.jucaipen.daoimp.SharkInfoImp;
import com.jucaipen.model.SharkInfo;

public class SharkInfoServer  {

	/**
	 * @return  获取摇一摇信息
	 */
	public static SharkInfo getSharkInfo() {
		SharkInfoDao dao=new SharkInfoImp();
		return dao.getSharkInfo();
	}
	
	/**
	 * @param date
	 * @param rest
	 * @return  更新剩余额度
	 */
	public static int updatePrice(int rest){
		SharkInfoDao dao=new SharkInfoImp();
		return dao.updatePrice( rest);
	}
	
	/**
	 * @param date
	 * @return  查询今日的摇一摇活动
	 */
	public static SharkInfo getSharkByIsEnd(String date){
		SharkInfoDao dao=new SharkInfoImp();
		return dao.getSharkByIsEnd(date);
	}

}
