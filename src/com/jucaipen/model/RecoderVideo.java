package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author ���ʷ�
 *    ¼����Ƶ
 */
public class RecoderVideo implements Serializable{
	/**
	 *   ��ǰҳ��
	 */
	private int page;
	
	/**
	 *   ��ҳ��
	 */
	private int totlePage;
	/**
	 *   id 
	 */
	private int id;
	
	/**
	 *   �Ƿ���  1  δ����    0  �ѹ���     1  �ѹ���
	 */
	private int isPurch;
	
	/**
	 *   ����
	 */
	private String title;
	
	/**
	 *   ֱ����id
	 */
	private String yunId;
	
	/**
	 *   ��ʦid
	 */
	private int teacherId;
	
	/**
	 *   ��ʼʱ��
	 */
	private String startDate;
	
	/**
	 *   ����ʱ��
	 */
	private String endDate;
	
	/**
	 *   �Ƿ��շѣ�1��ѣ�2�շѣ�
	 */
	private int liveIsFree;
	
	/**
	 *   ���ż۸�
	 */
	private int livePrice;
	
	/**
	 *   ���ŵ�ַ1
	 */
	private String liveUrl1;
	/**
	 *   ���ŵ�ַ2
	 */
	private String liveUrl2;
	
	/**
	 *   ���ŵ�ַ3
	 */
	private String liveUrl3;
	
	/**
	 *   ����ʱ��
	 */
	private String duration;
	
	/**
	 *    �����ַ
	 */
	private String imageUrl;
	
	/**
	 *   �ļ���С
	 */
	private String fineLength;
	
	/**
	 *   ������
	 */
	private int likeCount;
	
	/**
	 *   �����
	 */
	private int playCount;
	
	/**
	 *    �������� 
 	 */
	private int xnPlayCount;
	
	/**
	 *   �Ƿ�ѡ��1��  2�ǣ�
	 */
	private int isBest;
	
	/**
	 *   ����ʱ��
	 */
	private String insertDate;
	
	/**
	 *   ��ע
	 */
	private String remark;
	
	/**
	 *   ��չ�ֶ�1
	 */
	private String ext1;
	
	/**
	 *   ��չ�ֶ�2
	 */
	private String ext2;
	
	public int getIsPurch() {
		return isPurch;
	}

	public void setIsPurch(int isPurch) {
		this.isPurch = isPurch;
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

	public RecoderVideo() {
	}

	public RecoderVideo(int id, String title, String yunId, int teacherId,
			String startDate, String endDate, int liveIsFree, int livePrice,
			String liveUrl1, String liveUrl2, String liveUrl3, String duration,
			String imageUrl, String fineLength, int likeCount, int playCount,
			int xnPlayCount, int isBest, String insertDate, String remark,
			String ext1, String ext2) {
		this.id = id;
		this.title = title;
		this.yunId = yunId;
		this.teacherId = teacherId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.liveIsFree = liveIsFree;
		this.livePrice = livePrice;
		this.liveUrl1 = liveUrl1;
		this.liveUrl2 = liveUrl2;
		this.liveUrl3 = liveUrl3;
		this.duration = duration;
		this.imageUrl = imageUrl;
		this.fineLength = fineLength;
		this.likeCount = likeCount;
		this.playCount = playCount;
		this.xnPlayCount = xnPlayCount;
		this.isBest = isBest;
		this.insertDate = insertDate;
		this.remark = remark;
		this.ext1 = ext1;
		this.ext2 = ext2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYunId() {
		return yunId;
	}

	public void setYunId(String yunId) {
		this.yunId = yunId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getLiveIsFree() {
		return liveIsFree;
	}

	public void setLiveIsFree(int liveIsFree) {
		this.liveIsFree = liveIsFree;
	}

	public int getLivePrice() {
		return livePrice;
	}

	public void setLivePrice(int livePrice) {
		this.livePrice = livePrice;
	}

	public String getLiveUrl1() {
		return liveUrl1;
	}

	public void setLiveUrl1(String liveUrl1) {
		this.liveUrl1 = liveUrl1;
	}

	public String getLiveUrl2() {
		return liveUrl2;
	}

	public void setLiveUrl2(String liveUrl2) {
		this.liveUrl2 = liveUrl2;
	}

	public String getLiveUrl3() {
		return liveUrl3;
	}

	public void setLiveUrl3(String liveUrl3) {
		this.liveUrl3 = liveUrl3;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFineLength() {
		return fineLength;
	}

	public void setFineLength(String fineLength) {
		this.fineLength = fineLength;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public int getXnPlayCount() {
		return xnPlayCount;
	}

	public void setXnPlayCount(int xnPlayCount) {
		this.xnPlayCount = xnPlayCount;
	}

	public int getIsBest() {
		return isBest;
	}

	public void setIsBest(int isBest) {
		this.isBest = isBest;
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

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
}
