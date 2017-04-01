package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Order;

/**
 * @author Administrator
 * 
 * 
 *         订单信息
 */
public interface OrderDao {

	/**
	 * @return 获取订单列表
	 */
	public List<Order> findAllPayOrderList(int page);

	/**
	 * @param uId
	 * @return 根据用户ID，获取订单信息
	 */
	public List<Order> findPayOrderByUid(int uId, int page);

	/**
	 * @param payState
	 * @return 根据付款状态获取订单信息
	 */
	public List<Order> findPayOrderByPayState(int payState, int page);

	/**
	 * @param orderState
	 * @return 根据订单状态获取订单信息
	 */
	public List<Order> findPayOrderByOrderState(int orderState, int page);

	/**
	 * @param teacherId
	 * @return 根据讲师ID获取订单信息
	 */
	public List<Order> findPayOrderByTeacherId(int teacherId, int page);

	/**
	 * @param productState
	 * @return 根据商品状态获取订单信息
	 */
	public List<Order> findPayOrderByProductState(int productState, int page);

	/**
	 * @param lastCount
	 * @return 获取最近几条订单信息
	 */
	public List<Order> findLastPayOrder(int lastCount);

	/**
	 * @param id
	 * @return 根据ID获取订单信息
	 */
	public Order findPayOrderById(int id);
	
	
	/**
	 * @param payOrder
	 * @return   添加订单信息
	 */
	public int addPayOrder(Order payOrder);
	
	/**
	 * @param payType
	 * @param payDate
	 * @return    改变支付状态
	 */
	public int changePayState(int payType,String payDate,String orderCode);
}
