package com.jucaipen.model;

public class JcpNews {
	private int page;
	
	private int totlePage;
	private int id;
	
	private String title;
	
	private int bigId;
	
	private int smallId;
	
	private String keyWord;
	
	private String imageUrl;
	
	private String imageThumb;
	
	private String desc;
	
	private String zhaoYao;
	
	private int comeFrom;
	
	private String from;
	
	private String repoter;
	
	private String bodys;
	
	private String bodyForMobile;
	
	private String htmlPath;
	
	private int hits;
	
	private int comms;
	
	private String insertDate;
	
	private String insertUserName;
	
	/**
	 *  是否是置顶（1为置顶，0不为置顶）
	 */
	private int isTop;
	
	/**
	 *  是否精选（1为精选0则不是）
	 */
	private int isBest;
	
	/**
	 *  是否是图片新闻（1为图片新闻，0则不是）
	 */
	private int image;
	
	/**
	 *  是否首页显示（0为不显示，1为首页显示）
	 */
	private int isIndex;
	
	private String xiangguanNews;
	
	private int zan;
	
	/**
	 * 1为推荐，0为不推荐
	 */
	private int isTuijian;
	
	private int xnHits;
	
	
	
	

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBigId() {
		return bigId;
	}

	public void setBigId(int bigId) {
		this.bigId = bigId;
	}

	public int getSmallId() {
		return smallId;
	}

	public void setSmallId(int smallId) {
		this.smallId = smallId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageThumb() {
		return imageThumb;
	}

	public void setImageThumb(String imageThumb) {
		this.imageThumb = imageThumb;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getZhaoYao() {
		return zhaoYao;
	}

	public void setZhaoYao(String zhaoYao) {
		this.zhaoYao = zhaoYao;
	}

	public int getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(int comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getRepoter() {
		return repoter;
	}

	public void setRepoter(String repoter) {
		this.repoter = repoter;
	}

	public String getBodys() {
		return bodys;
	}

	public void setBodys(String bodys) {
		this.bodys = bodys;
	}

	public String getBodyForMobile() {
		return bodyForMobile;
	}

	public void setBodyForMobile(String bodyForMobile) {
		this.bodyForMobile = bodyForMobile;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getComms() {
		return comms;
	}

	public void setComms(int comms) {
		this.comms = comms;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertUserName() {
		return insertUserName;
	}

	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getIsBest() {
		return isBest;
	}

	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	public String getXiangguanNews() {
		return xiangguanNews;
	}

	public void setXiangguanNews(String xiangguanNews) {
		this.xiangguanNews = xiangguanNews;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public int getIsTuijian() {
		return isTuijian;
	}

	public void setIsTuijian(int isTuijian) {
		this.isTuijian = isTuijian;
	}

	public int getXnHits() {
		return xnHits;
	}

	public void setXnHits(int xnHits) {
		this.xnHits = xnHits;
	}
}
