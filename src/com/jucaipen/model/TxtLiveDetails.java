package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator          --------------数据已更新
 *   
 *   文字直播详情
 *
 */

@SuppressWarnings("serial")
public class TxtLiveDetails implements Serializable{
	/**
	 * id
	 */
	private int id;
	
	private int ownJucaiBills;
	/**
	 * 相关直播id
	 */
	private int fk_liveId;
	/**
	 * 相关评论
	 */
	private int fk_commId;
	/**
	 * 内容
	 */
	private String bodys;
	/**
	 * 图片路径
	 */
	private String image;
	/**
	 * 直播类型（0观点，1回复网友互动）
	 */
	private int liveType;
	/**
	 * 上传时间
	 */
	private String insertDate;
	
	private int isPurch;
	
	/**
	 * 是否收费     1   否     2  是
	 */
	private int isFree;
	/**
	 * 收费价格
	 */
	private int livePrice;
	/**
	 *  内容分类id
	 */
	private int fk_contentId;
	public int getOwnJucaiBills() {
		return ownJucaiBills;
	}
	public void setOwnJucaiBills(int ownJucaiBills) {
		this.ownJucaiBills = ownJucaiBills;
	}
	public int getIsPurch() {
		return isPurch;
	}
	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFk_liveId() {
		return fk_liveId;
	}
	public void setFk_liveId(int fk_liveId) {
		this.fk_liveId = fk_liveId;
	}
	public int getFk_commId() {
		return fk_commId;
	}
	public void setFk_commId(int fk_commId) {
		this.fk_commId = fk_commId;
	}
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLiveType() {
		return liveType;
	}
	public void setLiveType(int liveType) {
		this.liveType = liveType;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	
	public int getLivePrice() {
		return livePrice;
	}
	public void setLivePrice(int livePrice) {
		this.livePrice = livePrice;
	}
	public int getFk_contentId() {
		return fk_contentId;
	}
	public void setFk_contentId(int fk_contentId) {
		this.fk_contentId = fk_contentId;
	}

}
