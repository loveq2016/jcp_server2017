package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.LiveDetailSale;

/**
 * @author Administrator
 *
 *  购买直播观点信息
 */
public interface LiveDetailSaleDao {
	/**
	 * @param id
	 * @return 通过id获取购买信息
	 */
	public LiveDetailSale findSaleById(int id);
	/**
	 * @param uId
	 * @return 通过用户id获取购买信息
	 */
	public List<LiveDetailSale> findSaleByUid(int uId,int page);
	
	public LiveDetailSale findSaleByUserIdAndTxtIdAndDetailId(int uId,int detailId);
	
	/**
	 * @param sale
	 * @return 添加购买信息
	 */
	public int addSale(LiveDetailSale sale);

}
