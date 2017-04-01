package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author YLF            --------------数据已更新
 * 
 *    评论bean  0证券知识，1为视频   JCP_User_Comm
 *
 */
@SuppressWarnings("serial")
public class UserComm implements Serializable {
	private int page;
	private int totlePage;
    /**
     *  评论id
     */
    private int id;
    /**
     *  评论用户id
     */
    private int userId;
    
    /**
     *  用户名称
     */
    private String userName;
    /**
     *  用户头像
     */
    private String userFace;
    
    /**
     *   用户等级
     */
    private int userLeavel;
    
    /**
     *  评论内容
     */
    private String bodys;
    /**
     * 父级id
     */
    private int parentId;
    /**
     * 评论类别（0为证券知识，1为视频）
     */
    private int type;
    /**
     * 视频或证券知识ID
     */
    private int novId;
    
    private String fkName;
    
    private String fkDesc;
    
    private String fkImage;
    /**
     *  点赞数量
     */
    private int goods;
    /**
     *  评论时间
     */
    private String insertDate;
    /**
     *  回复数量
     */
    private int replyCount;
    /**
     *  是否显示（0是，1否）
     */
    private int isShow;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFace() {
		return userFace;
	}
	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}
	public int getUserLeavel() {
		return userLeavel;
	}
	public void setUserLeavel(int userLeavel) {
		this.userLeavel = userLeavel;
	}
	public String getFkDesc() {
		return fkDesc;
	}
	public void setFkDesc(String fkDesc) {
		this.fkDesc = fkDesc;
	}
	public String getFkImage() {
		return fkImage;
	}
	public void setFkImage(String fkImage) {
		this.fkImage = fkImage;
	}
	public String getFkName() {
		return fkName;
	}
	public void setFkName(String fkName) {
		this.fkName = fkName;
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
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNovId() {
		return novId;
	}
	public void setNovId(int novId) {
		this.novId = novId;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
    
}
