package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.JcpNews;

public interface JcpNewsDao {
	/**
	 * @return 查询所有新闻
	 */
	public List<JcpNews> findAll(int pager);

	/**
	 * @param bigId
	 * @return 根据分类查询新闻
	 */
	public List<JcpNews> findNewsBybigId(int bigId,int smallId, int pager);


	/**
	 * @param isIndex
	 * @return 查询首页显示的新闻
	 */
	public List<JcpNews> findNewsByIndex(int isIndex, int pager);


	/**
	 * @param isBest
	 * @return 查询精选新闻
	 */
	public List<JcpNews> findNewsByBest(int isBest, int pager);

	/**
	 * @param isTop
	 * @return 查询置顶新闻
	 */
	public List<JcpNews> findNewsByTop(int isTop, int pager);
	/**
	 * @param id
	 * @return  查询指定新闻信息
	 */
	public JcpNews findNews(int id);
	/**
	 * @param classId
	 * @param isIndex
	 * @return 查询分类且首页显示的新闻
	 */
	public List<JcpNews> findNewsIndex(int bigId,int smallId,int isIndex);
	/**
	 * @param id
	 * @return   通过指定新闻id 获取相关新闻
	 */
	public List<JcpNews> findRelatedNewsById(int id,int top);
	/**
	 * @param count
	 * @return   查询最新的count条新闻
	 */
	public List<JcpNews> findLastNewsByNewsNum(int count);
	
	public JcpNews findHitsAndGoods(int id);
	
	public int addGoods(int id,int goods);
	
	/**
	 * @param hits
	 * @return   修改新闻点击数
	 */
	public int upDateHits(int xnHits,int hits,int id);
	/**
	 * @param Commens
	 * @return   修改评论数
	 */
	public int upDateComments(int Commens,int id);
}
