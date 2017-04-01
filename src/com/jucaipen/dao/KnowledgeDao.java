package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Knowledge;

/**
 * @author Administrator
 *
 *  知识
 */
public interface KnowledgeDao {
	
	/**
	 * @param id
	 * @return 根据id获取知识信息
	 */
	public Knowledge findKnowledgeById(int id);
	
	public Knowledge findHitAndGoods(int id);
	
	/**
	 * @param classId
	 * @param page
	 * @return  根据分类获取知识信息
	 */
	public List<Knowledge> findKnowledgeByClassId(int classId,int page);
	
	/**
	 * @return 获取所有的知识信息
	 */
	public List<Knowledge> findAllKnowledge(int page);
	/**
	 * @param id
	 * @return  获取上下篇信息
	 */
	public Knowledge findTitleById(int id,int classId);
	
	public int addHits(int id,int hits,int xnHits);
	
	public int addGoods(int id,int goods);
	

}
