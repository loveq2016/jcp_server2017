package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.GiftsDao;
import com.jucaipen.daoimp.GiftsImp;
import com.jucaipen.model.Gifts;

public class GiftsSer{

	/**
	 * @param page
	 * @return 获取商店所有礼品   分页
	 */
	public static List<Gifts> findAllGift(int page) {
		GiftsDao dao=new GiftsImp();
		return dao.findAllGift(page);
	}
	
	/**
	 * @param page
	 * @return 获取商店所有礼品
	 */
	public static List<Gifts> findAllGifts() {
		GiftsDao dao=new GiftsImp();
		return dao.findAllGifts();
	}

	/**
	 * @param page
	 * @param classId
	 * @return 根据分类id获取商店礼品
	 */
	public static List<Gifts> findGiftByClassId(int classId) {
		GiftsDao dao=new GiftsImp();
		return dao.findGiftByClassId( classId);
	}

	/**
	 * @param id
	 * @return 根据id获取礼品信息
	 */
	public static Gifts findGiftById(int id) {
		GiftsDao dao=new GiftsImp();
		return dao.findGiftById(id);
	}
	
	public static List<Gifts> findIsTuijian(int IsTuiJian){
		GiftsDao dao=new GiftsImp();
		return dao.findIsTuijian(IsTuiJian);
	}
	
	public static List<Gifts> findIsTuijian(int IsTuiJian,int page){
		GiftsDao dao=new GiftsImp();
		return dao.findIsTuijian(IsTuiJian,page);
	}
}
