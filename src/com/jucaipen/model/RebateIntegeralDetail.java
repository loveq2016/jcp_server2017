package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator  ----------------新增数据
 *
 *  返利积分记录    JCP_RebateIntegral_Detail
 */
@SuppressWarnings("serial")
public class RebateIntegeralDetail implements Serializable{
	private int page;
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  返利用户id  FK_UserId
	 */
	private int userId;
	/**
	 *  IntegralNum   返利积分数
	 */
	private int integralNum;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  ReMark
	 */
	private String remark;
	/**
	 *  Type  利记录类型（0推荐用户注册返利，1推荐申请讲师返利,2新用户注册返利，3用户评论返利,4用户签到返利）
	 */
	private int type;
    /**
     *  FK_FromId   来源id
     */
    private int fromId;
    
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIntegralNum() {
		return integralNum;
	}
	public void setIntegralNum(int integralNum) {
		this.integralNum = integralNum;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
}
