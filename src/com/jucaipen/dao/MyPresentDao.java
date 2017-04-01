package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MyPresent;

/**
 * @author Administrator
 *
 *  礼品 （自己拥有的）
 */
public interface MyPresentDao {
	/**
	 * @param uId
	 * @param page
	 * @return  获取我的礼品 
	 */
	public List<MyPresent> findPresentByUserId(int uId,int page);
	
	/**
	 * @param id
	 * @return 通过id获取我的礼品
	 */
	public MyPresent findPresentById(int id);
	/**
	 * @param present
	 * @return 添加礼品
	 */
	public int addPresent(MyPresent present);
	/**
	 * @param present
	 * @return  赠送礼品  （修改礼品数量）
	 */
	public int sendPresent(MyPresent present);
	
	/**
	 * @param id
	 * @param num
	 * @return 修改礼品信息
	 */
	public int sendPresent(int id,int num);

	
	/**
	 * @param uId
	 * @param pId
	 * @return  获取用户某个礼品数量
	 */
	public MyPresent findParentByUid(int uId,int pId);

}
