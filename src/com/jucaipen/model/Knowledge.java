package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator   --------------------新增数据
 *
 *  知识
 */
public class Knowledge implements Serializable{
	/**
	 *  当前页
	 */
	private int page;
	/**
	 *  总页数
	 */
	private int totlePage;
	/**
	 *  Id
	 */
	private int id;
	/**
	 *  Title
	 */
	private String title;
	/**
	 *  KeyWord
	 */
	private String keyWord;
	/**
	 *  Bodys
	 */
	private String bodys;
	/**
	 *  所属类别 FK_ClassId
	 */
	private int fk_ClassId;
	/**
	 *  阅读数  HitCount
	 */
	private int hits;
	/**
	 *  虚拟阅读数  HitXNCount
	 */
	private int xnHits;
	/**
	 *  InsertDate
	 */
	private String insertDate;
	/**
	 *  所属来源id  FK_FromId
	 */
	private int fromId;
	/**
	 *   所属来源
	 */
	private String from;
	/**
	 *  发布人 FaBuRen
	 */
	private String faBuRen;
	/**
	 *  缩略图  ImageUrl
	 */
	private String imageUrl;
	/**
	 *  作者  ZuoZhe
	 */
	private String zuozhe;
	/**
	 *  静态页面URL PageUrl
	 */
	private String pageUrl;
	/**
	 * 赞   Zan
	 */
	private int goods;
	/**
	 *  评论数  CommonCount
	 */
	private int comms;
	/**
	 *  IsTop
	 */
	private int isTop;
	/**
	 *  是否精选  IsJingXuan
	 */
	private int isBest;
	/**
	 * 摘要  ZhaiYao
	 */
	private String zhaiYao;
	/**
	 *  IsIndex
	 */
	private int isIndex;
	/**
	 *  是否推荐  IsTuiJian
	 */
	private int isRecomm;
	/**
	 *  排序id  SortId
	 */
	private int sortId;
	/**
	 *   下篇id
	 */
	private int nextId;
	/**
	 *  上篇id
	 */
	private int lastId;
	/**
	 *  下篇标题
	 */
	private String nextTitle;
	/**
	 *  上篇标题
	 */
	private String lastTitle;
	public int getNextId() {
		return nextId;
	}
	public void setNextId(int nextId) {
		this.nextId = nextId;
	}
	public int getLastId() {
		return lastId;
	}
	public void setLastId(int lastId) {
		this.lastId = lastId;
	}
	public String getNextTitle() {
		return nextTitle;
	}
	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}
	public String getLastTitle() {
		return lastTitle;
	}
	public void setLastTitle(String lastTitle) {
		this.lastTitle = lastTitle;
	}
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
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	public int getFk_ClassId() {
		return fk_ClassId;
	}
	public void setFk_ClassId(int fk_ClassId) {
		this.fk_ClassId = fk_ClassId;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getXnHits() {
		return xnHits;
	}
	public void setXnHits(int xnHits) {
		this.xnHits = xnHits;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public String getFaBuRen() {
		return faBuRen;
	}
	public void setFaBuRen(String faBuRen) {
		this.faBuRen = faBuRen;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getZuozhe() {
		return zuozhe;
	}
	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getComms() {
		return comms;
	}
	public void setComms(int comms) {
		this.comms = comms;
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
	public String getZhaiYao() {
		return zhaiYao;
	}
	public void setZhaiYao(String zhaiYao) {
		this.zhaiYao = zhaiYao;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsRecomm() {
		return isRecomm;
	}
	public void setIsRecomm(int isRecomm) {
		this.isRecomm = isRecomm;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}
