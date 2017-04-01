package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.OrderDetail;

/**
 * @author Administrator
 * 
 * 
 *         订单详细信息
 */
public interface OrderDetailDao {

	/**
	 * @return 获取订单详细信息列表
	 */
	public List<OrderDetail> findAllPayProductList(int page);

	/**
	 * @param uId
	 * @return 根据用户ID，获取订单详细信息
	 */
	public List<OrderDetail> findPayProductByUid(int uId,int page);
	
	/**
	 * @param userId
	 * @param type
	 * @param page
	 * @return 获取用户下相对应的订单信息
	 */
	public List<OrderDetail> findDetailByUIdAndType(int userId,int type,int page);
	
	/**
	 * @param id
	 * @return 删除订单信息
	 */
	public int deleteDetail(int id);

	/**
	 * @param productId
	 * @return 根据商品ID获取订单信息
	 */
	public List<OrderDetail> findPayProductByProductId(int productId,int page);

	/**
	 * @param orderId
	 * @return 根据订单ID获取订单详细信息
	 */
	public OrderDetail findPayProductByOrderId(String orderId);

	/**
	 * @param isDeleted
	 * @return 根据IsDelete
	 */
	public List<OrderDetail> findPayProductByIsDeleted(int isDeleted,int page);

	/**
	 * @param productType
	 * @return 根据商品类型获取订单详细信息
	 */
	public List<OrderDetail> findPayProductByProductType(int productType,int page);

	/**
	 * @param id
	 * @return 根据ID获取订单详细信息
	 */
	public OrderDetail findPayProductById(int id);

	/**
	 * @param lastCount
	 * @return 获取最近几条订单详细信息
	 */
	public List<OrderDetail> findLastPayProduct(int lastCount);
	
	
	/**
	 * @param payProduct
	 * @return   添加订单信息
	 */
	public int addPayProductInfo(OrderDetail payProduct);

}
