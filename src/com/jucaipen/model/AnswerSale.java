package com.jucaipen.model;

/**
 * @author Administrator  ---------新增数据
 *
 *  回答销售
 */
public class AnswerSale {
	/**
	 *  当前页数
	 */
	private int page;
	/**
	 *  总页数
	 */
	private int totlePage;
	
	/**
	 *  提问内容
	 */
	private String askBody;
	
	private String askDate;
	
	private int replyCount;
	
	/**
	 *  回复内容
	 */
	private String answerBody;
	
	/**
	 *   回复讲师昵称
	 */
	private String askUserName;
	
	private String userFace;
	
	/**
	 *  id   Id
	 */
	private int id;
	/**
	 *  用户id   FK_UserId
	 */
	private int userId;
	/**
	 *  讲师id   FK_TearchId
	 */
	private int teacherId;
	/**
	 *  订单编号   OrderCode
	 */
	private String orderCode;
	/**
	 *  提问id   FK_AskId
	 */
	private int askId;
	/**
	 *  添加时间  InsertDate
	 */
	private String insetDate;
	
	public String getAskDate() {
		return askDate;
	}
	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getAskBody() {
		return askBody;
	}
	public void setAskBody(String askBody) {
		this.askBody = askBody;
	}
	public String getAnswerBody() {
		return answerBody;
	}
	public void setAnswerBody(String answerBody) {
		this.answerBody = answerBody;
	}
	
	public String getAskUserName() {
		return askUserName;
	}
	public void setAskUserName(String askUserName) {
		this.askUserName = askUserName;
	}
	public String getUserFace() {
		return userFace;
	}
	public void setUserFace(String userFace) {
		this.userFace = userFace;
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
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getAskId() {
		return askId;
	}
	public void setAskId(int askId) {
		this.askId = askId;
	}
	public String getInsetDate() {
		return insetDate;
	}
	public void setInsetDate(String insetDate) {
		this.insetDate = insetDate;
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
