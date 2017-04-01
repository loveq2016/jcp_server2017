package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.GiftClassDao;
import com.jucaipen.daoimp.GiftClassImp;
import com.jucaipen.model.GiftClass;

public class GiftClassSer{

	/**
	 * @return 获取所有礼品分类信息
	 */
	public static  List<GiftClass> findAllClass() {
		GiftClassDao dao=new GiftClassImp();
		return dao.findAllClass();
	}

	/**
	 * @param id
	 * @return 根据id获取分类信息
	 */
	public static GiftClass findIdByClass(int id) {
		GiftClassDao dao=new GiftClassImp();
		return dao.findIdByClass(id);
	}
	
	public static List<GiftClass> findTopClass(int top) {
		GiftClassDao dao=new GiftClassImp();
		return dao.findTopClass(top);
	}

}
