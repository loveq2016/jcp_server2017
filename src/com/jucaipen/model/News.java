package com.jucaipen.model;

import java.io.Serializable;
/**
 * @author ylf          ---------------  数据已更新    5.18
 * 
 *         新闻类
 * 
 */
@SuppressWarnings("serial")
public class News implements Serializable {
	/**
	 * 总页数
	 */
	private int totlePager;
	/**
	 * 当前新闻页数
	 */
	private int pager;
	/**
	 * 新闻标题
	 */
	private String title;
	/**
	 * 新闻id
	 */
	private int id;
	/**
	 * 新闻关键字
	 */
	private String KeyWord;

	/**
	 * 新闻内容
	 */
	private String Bodys;
	/**
	 * 新闻大类
	 */
	private int classId;
	/**
	 * 阅读数
	 */
	private int hits;
	
	/**
	 *   虚拟阅读数
	 */
	private int XnHits;
	/**
	 * 发布时间
	 */
	private String publishDate;
	/**
	 * 来源
	 */
	private int fromId;
	/**
	 * 作者
	 */
	private String reporter;
	/**
	 * 新闻图片链接
	 */
	private String ImageUrl;
	/**
	 *  作者
	 */
	private String writer;
	/**
	 * 新闻网址
	 */
	private String htmlPath;
	/**
	 *  点赞
	 */
	private int goods;
	/**
	 * 评论数
	 */
	private int comments;
	/**
	 * 是否置顶（1 置顶 0不置顶）
	 */
	private int isTop;
	/**
	 * 是否精选
	 */
	private int isBest;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 是否首页显示
	 */
	private int isIndex;
	/**
	 *   是否推荐    0 不推荐   1  推荐
	 */
	private int isTuijian;
	/**
	 * 发布者
	 */
	private String publishUser;
	/**
	 * 新闻来源
	 */
	private String from;
	
	
	public News(int id) {
		this.id = id;
	}
	public News(int totlePager, int pager, String title, int id,
			String keyWord, String bodys, int classId, int hits, int xnHits,
			String publishDate, int fromId, String reporter, String imageUrl,
			String writer, String htmlPath, int goods, int comments, int isTop,
			int isBest, String summary, int isIndex, int isTuijian,
			String publishUser, String from) {
		super();
		this.totlePager = totlePager;
		this.pager = pager;
		this.title = title;
		this.id = id;
		KeyWord = keyWord;
		Bodys = bodys;
		this.classId = classId;
		this.hits = hits;
		XnHits = xnHits;
		this.publishDate = publishDate;
		this.fromId = fromId;
		this.reporter = reporter;
		ImageUrl = imageUrl;
		this.writer = writer;
		this.htmlPath = htmlPath;
		this.goods = goods;
		this.comments = comments;
		this.isTop = isTop;
		this.isBest = isBest;
		this.summary = summary;
		this.isIndex = isIndex;
		this.isTuijian = isTuijian;
		this.publishUser = publishUser;
		this.from = from;
	}
	public int getTotlePager() {
		return totlePager;
	}
	public void setTotlePager(int totlePager) {
		this.totlePager = totlePager;
	}
	public int getPager() {
		return pager;
	}
	public void setPager(int pager) {
		this.pager = pager;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyWord() {
		return KeyWord;
	}
	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}
	public String getBodys() {
		return Bodys;
	}
	public void setBodys(String bodys) {
		Bodys = bodys;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getXnHits() {
		return XnHits;
	}
	public void setXnHits(int xnHits) {
		XnHits = xnHits;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsTuijian() {
		return isTuijian;
	}
	public void setIsTuijian(int isTuijian) {
		this.isTuijian = isTuijian;
	}
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
     

}
