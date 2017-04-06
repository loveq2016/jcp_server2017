package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   ------------新增数据
 *
 *   贡献 榜   JCP_Contribute
 */
@SuppressWarnings("serial")
public class Contribute implements Serializable{
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  贡献用户id FK_UserId
	 */
	private int userId;
	/**
	 *  所属讲师 FK_TearchId
	 */
	private int teacherId;
	
	private String fromName;
	
	private String fromFace;
	
	private String leavel;
	
	private String introduce;
	
	/**
	 *   直播是否结束
	 */
	private int isEnd;
	
	/**
	 *  关联id  FK_Id
	 */
	private int fk_id;
	/**
	 *  赠送时间  InsertDate
	 */
	private String insertDate;
	/**
	 *  总聚财币   贡献    AllJucaibi
	 */
	private int allJucaiBills;
	
	/**
	 *  贡献类型 1赠送礼品  3开通守护 4购买问答 5打赏老师 6购买直播付费观点 7阅读付费问答 8购买付费日志  9打赏日志      ComType
	 */
	private int comType;
	
	
	
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromFace() {
		return fromFace;
	}
	public void setFromFace(String fromFace) {
		this.fromFace = fromFace;
	}
	public String getLeavel() {
		return leavel;
	}
	public void setLeavel(String leavel) {
		this.leavel = leavel;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
	public int getFk_id() {
		return fk_id;
	}
	public void setFk_id(int fk_id) {
		this.fk_id = fk_id;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getAllJucaiBills() {
		return allJucaiBills;
	}
	public void setAllJucaiBills(int allJucaiBills) {
		this.allJucaiBills = allJucaiBills;
	}
	public int getComType() {
		return comType;
	}
	public void setComType(int comType) {
		this.comType = comType;
	}
}
