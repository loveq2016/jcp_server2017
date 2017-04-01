package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.HotIdea;
public interface HotIdeaDao {
	
	/**
	 * @param ideaId
	 * @param hits
	 * @return  添加点击数
	 */
	public int addHit(int ideaId,int hits,int xnHits);
	
	/**
	 * @param ideaId
	 * @return  添加评论数
	 */
	public int addComment(int ideaId,int commCount);
	/**
	 * @param ideaId
	 * @return   点赞
	 */
	public int addGood(int ideaId,int googs);
	
	/**
	 * @param id
	 * @return  根据id获取点击数、点赞数和评论数
	 */
	public HotIdea findGoodAndHitAndComm(int id);
	
	/**
	 * @param condition
	 * @return  查询数据总页数
	 */
	public int findTotlePage(String condition);
	
	/**
	 * @return  获取所有的热门观点
	 */
	public List<HotIdea> findAllHotIdea(int page);
	/**
	 * @param count
	 * @return  获取最近的人们观点
	 */
	public List<HotIdea> findIdeaByCount(int count);
	/**
	 * @param teacherId
	 * @param count
	 * @return  获取讲师最近几条观点
	 */
	public List<HotIdea> findLastIdeaByTeacherId(int teacherId,int count);
	/**
	 * @param teacherId
	 * @return 根据教师id获取人们观点
	 */
	public List<HotIdea> findIdeaByTeacherId(int teacherId,int page);
	/**
	 * @param id
	 * @return 根据id获取热门观点
	 */
	public HotIdea findIdeaById(int id);
	/**
	 * @return  获取首页观点信息
	 */
	public List<HotIdea> findIndexIdea(int count);
	
	
	

}
