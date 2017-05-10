package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  ------------新增数据
 * 充值订单
 */
public class ChargeOrder implements Serializable{
	/**
	 *  当前页数
	 */
	private int page;
	/**
	 * 总页数
	 */
	private int totlePage;
	/**
	 *  id
	 */
	private int id;
	/**
	 * 订单号
	 */
	private String orderCode;
	/**
	 *  用户id
	 */
	private int userId;
	/**
	 *  充值金额
	 */
	private double chargeMoney;
	/**
	 * 充值时间
	 */
	private String insertDate;
	/**
	 *  付款时间
	 */
	private String payDate;
	/**
	 * 虚拟删除，0正常，1删除
	 */
	private int isDel;
	/**
	 *  充值IP
	 */
	private String ip;
    /**
     * 支付方式 1银行 2支付宝 3微信支付4：余额支付
     */
    private int payType;
    /**
     *  支付宝账号
     */
    private String alipayAccount;
    /**
     *  银行编码通道
     */
    private String bankChannel;
    /**
     * 订单状态 1未支付  2已支付 3支付失败
     */
    private int orderState;
    
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(double chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getBankChannel() {
		return bankChannel;
	}
	public void setBankChannel(String bankChannel) {
		this.bankChannel = bankChannel;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
}
