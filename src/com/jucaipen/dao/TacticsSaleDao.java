package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TacticsSale;

/**
 * @author Administrator
 *  战法销售信息
 */
public interface TacticsSaleDao {
	
	/**
	 * @param id
	 * @return  根据id获取战法销售信息
	 */
	public TacticsSale findSaleById(int id);
	
	/**
	 * @param uId
	 * @param page
	 * @return   根据用户id获取战法购买信息
	 */
	public List<TacticsSale> findSaleByUserId(int uId,int page);
	
	
	/**
	 * @param sale
	 * @return  添加战法购买信息
	 */
	public int addSale(TacticsSale sale);
	
	/**
	 * @param id
	 * @return  删除战法购买信息
	 */
	public int delSale(int id);
	
	public TacticsSale findTacticsIsSale(int uId,int tacticsId);
	
	public List<TacticsSale> findTacticsIsSale(int tacticsId);
	
	
	
	
	
	

}
