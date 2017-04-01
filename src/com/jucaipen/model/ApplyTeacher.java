package com.jucaipen.model;

/**
 * @author Administrator  --------------新增数据
 *
 *   申请讲师
 */
public class ApplyTeacher {
	/**
	 *   Id
	 */
	private int id;
	/**
	 *  真实姓名   TrueName
	 */
	private String trueName;
	/**
	 *  身份证号  IDCard
	 */
	private String idCard;
	/**
	 *  性别   Sex     性别（0保密，1男，2女）
	 */
	private int sex;
	/**
	 *  身份证照片1  CardImg_1
	 */
	private String cardImage1;
	/**
	 *  身份证照片2 CardImg_2
	 */
	private String cardImage2;
	/**
	 *  身份证照片3 CardImg_3
	 */
	private String cardImage3;
	/**
	 *  资格证种类id FK_CertificationId
	 */
	private int fk_certificationId;
	
	private String certificationName;
	
	/**
	 *  岗位id  FK_PositionId
	 */
	private int fk_PositionId;
	
	private String positionName;
	
	
	/**
	 *  资格证号码  CertificationNum
	 */
	private String certificationNum;
	/**
	 *  所在公司省份  FK_ProvinceId
	 */
	private int fk_ProvinceId;
	
	private String provinceName;
	
	/**
	 *  所在公司城市  FK_CityId
	 */
	private int fk_CityId;
	
	private String cityName;
	
	/**
	 *  公司名称 CompanyName
	 */
	private String companyName;
	
	/**
	 *  擅长领域，多个以逗号隔开   ShanChang
	 */
	private String shanChang;
	/**
	 *  个人简介  UserInformation
	 */
	private String userInformation;
	/**
	 *  推荐用户id  FK_ParentUserId
	 */
	private int fk_ParentUserId;
	
	
	
	private String parentAccount;
	/**
	 *  联系手机号  MobileNum
	 */
	private String mobileNum;
    /**
     *  联系Email  Email
     */
    private String email;
    /**
     *  申请用户id   FK_UserId
     */
    private int fk_UserId;
    /**
     *   申请状态（0待审核，1审核中，2审核成功，3审核失败）                 State
     */
    private int state;
    /**
     *  InsertDate 
     */
    private String insertDate;
    /**
     *  是否申请开通文字直播（0否  1是）  IsTxtLive
     */
    private  int isTxtLive;
    /**
     *  是否开通视频直播（0否  1是） IsVideoLive
     */
    private int isVideoLive;
    /**
     *  银行id  FK_BankId
     */
    private int fk_BankId;
    
    private String bankName;
    
    /**
     *  银行账号   BankAccount
     */
    private String bankAccount;
    
    private String actionCode;
    
    
    
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getParentAccount() {
		return parentAccount;
	}
	public void setParentAccount(String parentAccount) {
		this.parentAccount = parentAccount;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCardImage1() {
		return cardImage1;
	}
	public void setCardImage1(String cardImage1) {
		this.cardImage1 = cardImage1;
	}
	public String getCardImage2() {
		return cardImage2;
	}
	public void setCardImage2(String cardImage2) {
		this.cardImage2 = cardImage2;
	}
	public String getCardImage3() {
		return cardImage3;
	}
	public void setCardImage3(String cardImage3) {
		this.cardImage3 = cardImage3;
	}
	public int getFk_certificationId() {
		return fk_certificationId;
	}
	public void setFk_certificationId(int fk_certificationId) {
		this.fk_certificationId = fk_certificationId;
	}
	public int getFk_PositionId() {
		return fk_PositionId;
	}
	public void setFk_PositionId(int fk_PositionId) {
		this.fk_PositionId = fk_PositionId;
	}
	public String getCertificationNum() {
		return certificationNum;
	}
	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
	}
	public int getFk_ProvinceId() {
		return fk_ProvinceId;
	}
	public void setFk_ProvinceId(int fk_ProvinceId) {
		this.fk_ProvinceId = fk_ProvinceId;
	}
	public int getFk_CityId() {
		return fk_CityId;
	}
	public void setFk_CityId(int fk_CityId) {
		this.fk_CityId = fk_CityId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShanChang() {
		return shanChang;
	}
	public void setShanChang(String shanChang) {
		this.shanChang = shanChang;
	}
	public String getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(String userInformation) {
		this.userInformation = userInformation;
	}
	public int getFk_ParentUserId() {
		return fk_ParentUserId;
	}
	public void setFk_ParentUserId(int fk_ParentUserId) {
		this.fk_ParentUserId = fk_ParentUserId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFk_UserId() {
		return fk_UserId;
	}
	public void setFk_UserId(int fk_UserId) {
		this.fk_UserId = fk_UserId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getIsTxtLive() {
		return isTxtLive;
	}
	public void setIsTxtLive(int isTxtLive) {
		this.isTxtLive = isTxtLive;
	}
	public int getIsVideoLive() {
		return isVideoLive;
	}
	public void setIsVideoLive(int isVideoLive) {
		this.isVideoLive = isVideoLive;
	}
	public int getFk_BankId() {
		return fk_BankId;
	}
	public void setFk_BankId(int fk_BankId) {
		this.fk_BankId = fk_BankId;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
}
