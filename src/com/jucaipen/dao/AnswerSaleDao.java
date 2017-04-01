package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.AnswerSale;

/**
 * @author Administrator
 *
 * 回答销售
 */
public interface AnswerSaleDao {
	/**
	 * @param id
	 * @return  根据id获取回答销售信息
	 */
	public AnswerSale findSaleById(int id);
	
	/**
	 * @param userId
	 * @return 根据用户id获取销售信息
	 */
	public List<AnswerSale> findSaleByUid(int userId,int page);
	
	/**
	 * @param orderCode
	 * @return  根据订单编号获取销售信息
	 */
	public AnswerSale findSaleByOrderCode(String orderCode);
	
	/**
	 * @param teacherId
	 * @param userId
	 * @return 查询购买的讲师下的销售信息
	 */
	public List<AnswerSale> findSaleByTeacherAndUid(int teacherId,int userId);
	
	/**
	 * @param uId
	 * @param askId
	 * @return 根据用户id和提问id获取销售信息
	 */
	public AnswerSale findSaleByUserIdAndAskId(int uId,int askId);

}
