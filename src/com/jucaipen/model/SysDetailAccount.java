package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 *         系统详细账户
 */
@SuppressWarnings("serial")
public class SysDetailAccount implements Serializable {
	private int id;
	private int userId;
	/**
	 * 记录类型：1用户充值，2系统赠送，3、用户提现，4，礼品消费，5、直播观点，6购买日志，7、购买问答，8、开通守护，9、订阅战法，10、购买视频，
	 * 11购买专辑，12，系统赠送产品 13,打赏
	 */
	private int type;
	/**
	 * 1收入，2支出
	 */
	private int recoderType;
	private int orderId;
	private int price;
	private String insertDate;
	private String remark;
	private String ip;
	/**
	 * 是否删除（0否 1是）
	 */
	private int isDel;

	public SysDetailAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRecoderType() {
		return recoderType;
	}

	public void setRecoderType(int recoderType) {
		this.recoderType = recoderType;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
