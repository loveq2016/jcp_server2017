package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.OrderDao;
import com.jucaipen.daoimp.OrderImp;
import com.jucaipen.model.Order;

public class OrderServer {

	/**
	 * @return 获取全部的订单信息
	 */
	public static List<Order> findAllPayOrderList(int page) {
		OrderDao dao = new OrderImp();
		return dao.findAllPayOrderList(page);
	}

	/**
	 * @param uId
	 * @return 根据用户ID获取订单信息
	 */
	public static List<Order> findPayOrderByUid(int uId, int page) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderByUid(uId, page);
	}

	/**
	 * @param payState
	 * @return 根据支付状态获取订单信息
	 */
	public static List<Order> findPayOrderByPayState(int payState, int page) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderByPayState(payState, page);
	}

	/**
	 * @param orderState
	 * @return 根据订单状态获取订单信息
	 */
	public static List<Order> findPayOrderByOrderState(int orderState, int page) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderByOrderState(orderState, page);
	}

	/**
	 * @param teacherId
	 * @return 根据讲师ID获取订单信息
	 */
	public static List<Order> findPayOrderByTeacherId(int teacherId, int page) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderByTeacherId(teacherId, page);
	}

	/**
	 * @param productState
	 * @return 根据商品状态获取订单信息
	 */
	public static List<Order> findPayOrderByProductState(int productState, int page) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderByProductState(productState, page);
	}

	/**
	 * @param lastCount
	 * @return 获取最近几个订单
	 */
	public static List<Order> findLastPayOrder(int lastCount) {
		OrderDao dao = new OrderImp();
		return dao.findLastPayOrder(lastCount);
	}

	/**
	 * @param id
	 * @return 根据ID获取订单信息
	 */
	public static Order findPayOrderById(int id) {
		OrderDao dao = new OrderImp();
		return dao.findPayOrderById(id);
	}
	
	/**
	 * @param id
	 * @return 添加订单信息
	 */
	public static int addPayOrder(Order payOrder) {
		OrderDao dao = new OrderImp();
		return dao.addPayOrder(payOrder);
	}
	
	/**
	 * @param lastCount
	 * @return 修改订单支付状态
	 */
	public static int changePayState(int payType,String payDate,String orderCode) {
		OrderDao dao = new OrderImp();
		return dao.changePayState(payType, payDate, orderCode);
	}

}
