package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   -------------------数据已更新
 *
 *  数据库配置信息
 */
@SuppressWarnings("serial")
public class SiteConfig implements Serializable{
	/**
	 * 网站名称
	 */
	private String siteName;
	/**
	 * 网站标题
	 */
	private String siteTitle;
	/**
	 * 新闻访问增加虚拟人数倍数
	 */
	private int newsMom;
	/**
	 * 视频访问增加虚拟人数倍数
	 */
	private int videoMom;
	/**
	 * 关键词
	 */
	private String keyWord;
	/**
	 * 描述 
	 */
	private String descript;
	/**
	 * 备案号
	 */
	private String icp;
	/**
	 * 版权信息
	 */
	private String copyRight;
	/**
	 *  用户评论是否需要审核（0否，1是）
	 */
	private int commType;
	/**
	 * 推荐一人赠送多少积分
	 */
	private int recommIntegeral;
	/**
	 * 评论一条送多少积分
	 */
	private int commIntegeral;
	/**
	 * 签到一次赠送多少积分
	 */
	private int signIntegeral;
	/**
	 * 新用户首次注册赠送多少积分
	 */
	private int regIntegeral;
	/**
	 * 积分兑换聚财币（10积分=1聚财币）
	 */
	private double subRat;
	/**
	 * 讲师收费直播返利比例
	 */
	private double teacherLiveRebat;
	/**
	 * 讲师回答问题返利比例
	 */
	private double teacherAnswerRebat;
	/**
	 * 用户赠送礼品讲师返利比例
	 */
	private double userGiveRebat;
	/**
	 *  是否允许守护
	 */
	private double userIsShouhu;
	/**
	 * 系统设置提问数量
	 */
	private int askNum;
	
	/**
	 *   文字直播试看次数
	 */
	private int liveSeeCont;
	
	/**
	 *  文字直播详情试看次数
	 */
	private int liveDetailCount;
	
	public int getLiveSeeCont() {
		return liveSeeCont;
	}
	public void setLiveSeeCont(int liveSeeCont) {
		this.liveSeeCont = liveSeeCont;
	}
	public int getLiveDetailCount() {
		return liveDetailCount;
	}
	public void setLiveDetailCount(int liveDetailCount) {
		this.liveDetailCount = liveDetailCount;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public int getNewsMom() {
		return newsMom;
	}
	public void setNewsMom(int newsMom) {
		this.newsMom = newsMom;
	}
	public int getVideoMom() {
		return videoMom;
	}
	public void setVideoMom(int videoMom) {
		this.videoMom = videoMom;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getIcp() {
		return icp;
	}
	public void setIcp(String icp) {
		this.icp = icp;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public int getCommType() {
		return commType;
	}
	public void setCommType(int commType) {
		this.commType = commType;
	}
	public int getRecommIntegeral() {
		return recommIntegeral;
	}
	public void setRecommIntegeral(int recommIntegeral) {
		this.recommIntegeral = recommIntegeral;
	}
	public int getCommIntegeral() {
		return commIntegeral;
	}
	public void setCommIntegeral(int commIntegeral) {
		this.commIntegeral = commIntegeral;
	}
	public int getSignIntegeral() {
		return signIntegeral;
	}
	public void setSignIntegeral(int signIntegeral) {
		this.signIntegeral = signIntegeral;
	}
	public int getRegIntegeral() {
		return regIntegeral;
	}
	public void setRegIntegeral(int regIntegeral) {
		this.regIntegeral = regIntegeral;
	}
	public double getSubRat() {
		return subRat;
	}
	public void setSubRat(double subRat) {
		this.subRat = subRat;
	}
	public double getTeacherLiveRebat() {
		return teacherLiveRebat;
	}
	public void setTeacherLiveRebat(double teacherLiveRebat) {
		this.teacherLiveRebat = teacherLiveRebat;
	}
	public double getTeacherAnswerRebat() {
		return teacherAnswerRebat;
	}
	public void setTeacherAnswerRebat(double teacherAnswerRebat) {
		this.teacherAnswerRebat = teacherAnswerRebat;
	}
	public double getUserGiveRebat() {
		return userGiveRebat;
	}
	public void setUserGiveRebat(double userGiveRebat) {
		this.userGiveRebat = userGiveRebat;
	}
	public double getUserIsShouhu() {
		return userIsShouhu;
	}
	public void setUserIsShouhu(double userIsShouhu) {
		this.userIsShouhu = userIsShouhu;
	}
	public int getAskNum() {
		return askNum;
	}
	public void setAskNum(int askNum) {
		this.askNum = askNum;
	}
}
