package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator     ------------------数据已更新
 * 
 *   商品订单详细信息
 *
 */
@SuppressWarnings("serial")
public class OrderDetail implements Serializable{
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 总页数
	 */
	private int totlePage;
	/**
	 * id 
	 */
	private int id;
	/**
	 *  订单ID
	 */
	private String orderId;
	/**
	 *   关联订单ID 
	 */
	private int fk_OrderId;
	/**
	 * 原价
	 */
	private String price;
	/**
	 * 现价
	 */
	private String nowPrice;
	/**
	 *   购买数量
	 */
	private int buyCount;
	/**
	 *   总金额
	 */
	private String allMoney;
	/**
	 *  商品标题
	 */
	private String productTitle;
	/**
	 * 此字段为续约性产品专用字段（值为续约年数）
	 */
	private int xuYueYear;
	/**
	 * 此字段为续约性产品专用字段（值为续约月数）
	 */
	private int xuYueMonth;
	/**
	 * 此字段为续约性产品专用字段（值为续约天数）
	 */
	private int xuYueDay;
	/**
	 * 
	 */
	private int productType;
	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 * 产品状态（1：正常；4：删除）
	 */
	private int isDelete;
	public OrderDetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getFk_OrderId() {
		return fk_OrderId;
	}
	public void setFk_OrderId(int fk_OrderId) {
		this.fk_OrderId = fk_OrderId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public int getXuYueYear() {
		return xuYueYear;
	}
	public void setXuYueYear(int xuYueYear) {
		this.xuYueYear = xuYueYear;
	}
	public int getXuYueMonth() {
		return xuYueMonth;
	}
	public void setXuYueMonth(int xuYueMonth) {
		this.xuYueMonth = xuYueMonth;
	}
	public int getXuYueDay() {
		return xuYueDay;
	}
	public void setXuYueDay(int xuYueDay) {
		this.xuYueDay = xuYueDay;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotlePage() {
		return totlePage;
	}
	public void setTotlePage(int totlePage) {
		this.totlePage = totlePage;
	}

	

}
