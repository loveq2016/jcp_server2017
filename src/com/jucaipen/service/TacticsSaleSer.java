package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TacticsSaleDao;
import com.jucaipen.daoimp.TacticsSaleImp;
import com.jucaipen.model.TacticsSale;

/**
 * @author Administrator
 * 
 *         战法销售信息
 */
public class TacticsSaleSer {

	/**
	 * @param id
	 * @return 根据id获取战法销售信息
	 */
	public static TacticsSale findSaleById(int id) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.findSaleById(id);
	}

	/**
	 * @param uId
	 * @param page
	 * @return 根据用户id获取战法购买信息
	 */
	public static List<TacticsSale> findSaleByUserId(int uId, int page) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.findSaleByUserId(uId, page);
	}

	/**
	 * @param sale
	 * @return 添加战法销售信息
	 */
	public static int addSale(TacticsSale sale) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.addSale(sale);
	}

	/**
	 * @param id
	 * @return 删除战法销售信息
	 */
	public static int delSale(int id) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.delSale(id);
	}

	public static TacticsSale findTacticsIsSale(int uId, int tacticsId) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.findTacticsIsSale(uId, tacticsId);
	}
	
	public static List<TacticsSale> findTacticsIsSale(int tacticsId) {
		TacticsSaleDao dao = new TacticsSaleImp();
		return dao.findTacticsIsSale(tacticsId);
	}

}
