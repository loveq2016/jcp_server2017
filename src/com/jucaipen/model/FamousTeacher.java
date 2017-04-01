package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator --------------------------数据已更新 5.18
 * 
 *         推荐名师
 */
@SuppressWarnings("serial")
public class FamousTeacher implements Serializable {
	/**
	 * 讲师当前页数
	 */
	private int page;
	/**
	 * 讲师总页数
	 */
	private int totlePage;
	/**
	 * 教师id
	 */
	private int id;

	/**
	 * 关联用户id
	 */
	private int fk_UserId;
	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 是否关注
	 */
	private boolean isAttention;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像
	 */
	private String headFace;
	/**
	 * 头衔
	 */
	private String level;
	/**
	 * 是否加V
	 */
	private int isV;
	/**
	 * 粉丝数量
	 */
	private int fans;
	/**
	 * 直播人气
	 */
	private int liveFans;
	/**
	 * 回复问题数
	 */
	private int answerCount;
	/**
	 * 日志数
	 */
	private int articleCount;
	/**
	 * 视频直播是否结束
	 */
	private int liveIsEnd;

	/**
	 * 日志阅读量
	 */
	private int articleReadCount;
	/**
	 * 日志点赞数
	 */
	private int articleGood;
	/**
	 * 公告
	 */
	private String notice;
	/**
	 * 教师简介
	 */
	private String introduce;
	/**
	 * 擅长领域
	 */
	private String hoby;
	/**
	 * 手机号
	 */
	private String telPhone;
	/**
	 * 签约人数
	 */
	private int signCount;
	/**
	 * 账号状态（0正常，1已锁定，2已删除，3未启用（针对审核通过但未完善信息的讲师））
	 */
	private int state;
	/**
	 * 加入时间
	 */
	private String joinDate;
	/**
	 * 直播互动是否需要审核
	 */
	private int liveGbookIsPass;
	/**
	 * 提问人数
	 */
	private int askNum;
	/**
	 * 回答问题是否需要付费 0 否 1 是
	 */
	private int isPay;
	/**
	 * 回答收费价格
	 */
	private int answerPrice;
	/**
	 * 成为守护者年价格
	 */
	private int yaerPrice;
	/**
	 * 成为守护者月价格
	 */
	private int mothPrice;
	/**
	 * 成为守护者季度价格
	 */
	private int qulaterPrice;
	/**
	 * 成为守护者天价格
	 */
	private int dayPrice;

	/**
	 * 成为守护周费用
	 */
	private int weekPrice;
	/**
	 * 是否推荐 0 否 1 是
	 */
	private int isTuijian;
	/**
	 * 周人气
	 */
	private int weekRenQi;
	/**
	 * 月人气
	 */
	private int mothRenQi;
	/**
	 * 直播视频URL
	 */
	/*private String videoLiveUrl;*/
	/**
	 *  是否开通视频直播
	 */
	private int isUserLiveUrl;
	/**
	 * 银行id
	 */
	private int bankId;

	private String bankName;
	/**
	 * 银行账号
	 */
	private String bankAccount;
	/**
	 * 返现率
	 */
	private double returnRate;
	/**
	 * 文字直播收费价格
	 */
	private int txtLivePrice;

	/**
	 * 直播收费价格
	 */
	private int livePrice;

	/**
	 * 文字直播是否收费  0 否    1是
	 */
	private int txtLiveFree;

	/**
	 * 直播是否收费  0   否   1  是
	 */
	private int liveFree;

	/**
	 * 虚拟直播人气
	 */
	private int xnLiveRenQi;

	/**
	 * 虚拟文章阅读数
	 */
	private int xnArticleReadNum;

/*	private String mobileLiveUrl;*/
	
	/**
	 *   app 直播 url
	 */
	private String appLiveUrl;
	

	public int getWeekPrice() {
		return weekPrice;
	}

	public void setWeekPrice(int weekPrice) {
		this.weekPrice = weekPrice;
	}

	/*public String getMobileLiveUrl() {
		return mobileLiveUrl;
	}

	public void setMobileLiveUrl(String mobileLiveUrl) {
		this.mobileLiveUrl = mobileLiveUrl;
	}*/

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getTxtLivePrice() {
		return txtLivePrice;
	}

	public void setTxtLivePrice(int txtLivePrice) {
		this.txtLivePrice = txtLivePrice;
	}

	public int getLivePrice() {
		return livePrice;
	}

	public void setLivePrice(int livePrice) {
		this.livePrice = livePrice;
	}

	public int getTxtLiveFree() {
		return txtLiveFree;
	}

	public void setTxtLiveFree(int txtLiveFree) {
		this.txtLiveFree = txtLiveFree;
	}

	public int getLiveFree() {
		return liveFree;
	}

	public void setLiveFree(int liveFree) {
		this.liveFree = liveFree;
	}

	public int getXnLiveRenQi() {
		return xnLiveRenQi;
	}

	public void setXnLiveRenQi(int xnLiveRenQi) {
		this.xnLiveRenQi = xnLiveRenQi;
	}

	public int getXnArticleReadNum() {
		return xnArticleReadNum;
	}

	public void setXnArticleReadNum(int xnArticleReadNum) {
		this.xnArticleReadNum = xnArticleReadNum;
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

	public int getFk_UserId() {
		return fk_UserId;
	}

	public void setFk_UserId(int fk_UserId) {
		this.fk_UserId = fk_UserId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public boolean isAttention() {
		return isAttention;
	}

	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadFace() {
		return headFace;
	}

	public void setHeadFace(String headFace) {
		this.headFace = headFace;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getIsV() {
		return isV;
	}

	public void setIsV(int isV) {
		this.isV = isV;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public int getLiveFans() {
		return liveFans;
	}

	public void setLiveFans(int liveFans) {
		this.liveFans = liveFans;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public int getLiveIsEnd() {
		return liveIsEnd;
	}

	public void setLiveIsEnd(int liveIsEnd) {
		this.liveIsEnd = liveIsEnd;
	}

	public int getArticleReadCount() {
		return articleReadCount;
	}

	public void setArticleReadCount(int articleReadCount) {
		this.articleReadCount = articleReadCount;
	}

	public int getArticleGood() {
		return articleGood;
	}

	public void setArticleGood(int articleGood) {
		this.articleGood = articleGood;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHoby() {
		return hoby;
	}

	public void setHoby(String hoby) {
		this.hoby = hoby;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public int getSignCount() {
		return signCount;
	}

	public void setSignCount(int signCount) {
		this.signCount = signCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getLiveGbookIsPass() {
		return liveGbookIsPass;
	}

	public void setLiveGbookIsPass(int liveGbookIsPass) {
		this.liveGbookIsPass = liveGbookIsPass;
	}

	public int getAskNum() {
		return askNum;
	}

	public void setAskNum(int askNum) {
		this.askNum = askNum;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getAnswerPrice() {
		return answerPrice;
	}

	public void setAnswerPrice(int answerPrice) {
		this.answerPrice = answerPrice;
	}

	public int getYaerPrice() {
		return yaerPrice;
	}

	public void setYaerPrice(int yaerPrice) {
		this.yaerPrice = yaerPrice;
	}

	public int getMothPrice() {
		return mothPrice;
	}

	public void setMothPrice(int mothPrice) {
		this.mothPrice = mothPrice;
	}

	public int getQulaterPrice() {
		return qulaterPrice;
	}

	public void setQulaterPrice(int qulaterPrice) {
		this.qulaterPrice = qulaterPrice;
	}

	public int getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(int dayPrice) {
		this.dayPrice = dayPrice;
	}

	public int getIsTuijian() {
		return isTuijian;
	}

	public void setIsTuijian(int isTuijian) {
		this.isTuijian = isTuijian;
	}

	public int getWeekRenQi() {
		return weekRenQi;
	}

	public void setWeekRenQi(int weekRenQi) {
		this.weekRenQi = weekRenQi;
	}

	public int getMothRenQi() {
		return mothRenQi;
	}

	public void setMothRenQi(int mothRenQi) {
		this.mothRenQi = mothRenQi;
	}
	
/*	public String getVideoLiveUrl() {
		return videoLiveUrl;
	}

	public void setVideoLiveUrl(String videoLiveUrl) {
		this.videoLiveUrl = videoLiveUrl;
	}*/

	public String getAppLiveUrl() {
		return appLiveUrl;
	}

	public void setAppLiveUrl(String appLiveUrl) {
		this.appLiveUrl = appLiveUrl;
	}

	public int getIsUserLiveUrl() {
		return isUserLiveUrl;
	}

	public void setIsUserLiveUrl(int isUserLiveUrl) {
		this.isUserLiveUrl = isUserLiveUrl;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(double returnRate) {
		this.returnRate = returnRate;
	}

}
