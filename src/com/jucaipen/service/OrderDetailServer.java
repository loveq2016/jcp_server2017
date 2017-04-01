package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.OrderDetailDao;
import com.jucaipen.daoimp.OrderDetailImp;
import com.jucaipen.model.OrderDetail;

public class OrderDetailServer {

	/**
	 * @return 获取全部订单信息
	 */
	public static List<OrderDetail> findAllPayProductList(int page) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findAllPayProductList(page);
	}

	/**
	 * @param uId
	 * @return 根据用户ID获取订单详细信息
	 */
	public static List<OrderDetail> findPayProductByUid(int uId, int page) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductByUid(uId, page);
	}

	/**
	 * @param productId
	 * @return 根据商品ID获取订单详细信息
	 */
	public static List<OrderDetail> findPayProductByProductId(int productId, int page) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductByProductId(productId, page);
	}

	/**
	 * @param orderId
	 * @return 根据订单ID获取订单详细信息
	 */
	public static OrderDetail findPayProductByOrderId(String orderId) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductByOrderId(orderId);
	}

	/**
	 * @param isDeleted
	 * @return 根据状态获取订单详细信息
	 */
	public static List<OrderDetail> findPayProductByIsDeleted(int isDeleted, int page) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductByIsDeleted(isDeleted, page);
	}

	/**
	 * @param productType
	 * @return 根据商品类型获取订单详细信息
	 */
	public static List<OrderDetail> findPayProductByProductType(int productType,
			int page) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductByProductType(productType, page);
	}

	/**
	 * @param id
	 * @return 根据ID获取订单详细信息
	 */
	public static OrderDetail findPayProductById(int id) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findPayProductById(id);
	}

	/**
	 * @param lastCount
	 * @return 获取最近几条订单详细信息
	 */
	public static List<OrderDetail> findLastPayProduct(int lastCount) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.findLastPayProduct(lastCount);
	}
	
	/**
	 * @param lastCount
	 * @return 添加订单详细信息
	 */
	public static int addPayProductInfo(OrderDetail payProduct) {
		OrderDetailDao dao = new OrderDetailImp();
		return dao.addPayProductInfo(payProduct);
	}
	
	/**
	 * @param id
	 * @return 删除订单详细信息
	 */
	public static int deleteDetail(int id){
		OrderDetailDao dao=new OrderDetailImp();
		return dao.deleteDetail(id);
	}
	
	/**
	 * @param userId
	 * @param type
	 * @param page
	 * @return  
	 */
	public static List<OrderDetail> findDetailByUIdAndType(int userId, int type,
			int page){
		OrderDetailDao dao=new OrderDetailImp();
		return dao.findDetailByUIdAndType(userId, type, page);
	}
	

}
