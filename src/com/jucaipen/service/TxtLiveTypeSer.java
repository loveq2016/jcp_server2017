package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TxtLiveTypeDao;
import com.jucaipen.daoimp.TxtLiveTypeImp;
import com.jucaipen.model.TxtLiveType;

public class TxtLiveTypeSer {

	/**
	 * @return 获取所有文字直播分类信息
	 */
	public static List<TxtLiveType> findAll() {
		TxtLiveTypeDao dao=new TxtLiveTypeImp();
		return dao.findAll();
	}

	/**
	 * @param id
	 * @return 根据id获取文字直播分类信息
	 */
	public static  TxtLiveType findTypeById(int id) {
		TxtLiveTypeDao dao=new TxtLiveTypeImp();
		return dao.findTypeById(id);
	}

}
