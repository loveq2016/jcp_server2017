package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.LiveDetailSaleDao;
import com.jucaipen.daoimp.LiveDetailSaleImp;
import com.jucaipen.model.LiveDetailSale;

public class LiveDetailSaleSer{

	/**
	 * @param id
	 * @return 通过id获取购买信息
	 */
	public static LiveDetailSale findSaleById(int id) {
		LiveDetailSaleDao dao=new LiveDetailSaleImp();
		return dao.findSaleById(id);
	}

	/**
	 * @param uId
	 * @param page
	 * @return 根据用户id获取购买信息
	 */
	public static List<LiveDetailSale> findSaleByUid(int uId, int page) {
		LiveDetailSaleDao dao=new LiveDetailSaleImp();
		return dao.findSaleByUid(uId, page);
	}
	
	public static LiveDetailSale findSaleByUserIdAndTxtIdAndDetailId(int uId,int detailId){
		LiveDetailSaleDao dao=new LiveDetailSaleImp();
		return dao.findSaleByUserIdAndTxtIdAndDetailId(uId, detailId);
	}

	/**
	 * @param sale
	 * @return 添加购买信息
	 */
	public static int addSale(LiveDetailSale sale) {
		LiveDetailSaleDao dao=new LiveDetailSaleImp();
		return dao.addSale(sale);
	}

}
