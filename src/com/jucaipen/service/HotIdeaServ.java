package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.HotIdeaDao;
import com.jucaipen.daoimp.HotIdeaImp;
import com.jucaipen.model.HotIdea;

public class HotIdeaServ{

	/**
	 * @return 查询所有的热门观点
	 */
	public static List<HotIdea> findAllHotIdea(int page) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findAllHotIdea(page);
	}

	/**
	 * @param count
	 * @return 查询最近的几条热门观点
	 */
	public static List<HotIdea> findIdeaByCount(int count) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findIdeaByCount(count);
	}

	/**
	 * @param teacherId
	 * @return  根据教师id查询人们观点
	 */
	public static List<HotIdea> findIdeaByTeacherId(int teacherId,int page) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findIdeaByTeacherId(teacherId,page);
	}
	
	public static List<HotIdea>  findLastIdeaByTeacherId(int teacherId,int count){
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findLastIdeaByTeacherId(teacherId, count);
	}

	/**
	 * @param id
	 * @return   通过id获取热门观点信息
	 */
	public static HotIdea findIdeaById(int id) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findIdeaById(id);
	}

	/**
	 * @param ideaId
	 * @param hits
	 * @return  根据id添加点赞数
	 */
	public static int addHit(int ideaId, int hits,int xnHits) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.addHit(ideaId, hits,xnHits);
	}

	/**
	 * @param ideaId
	 * @param commCount
	 * @return  根据id 添加评论数
	 */
	public static int addComment(int ideaId, int commCount) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.addComment(ideaId, commCount);
	}

	/**
	 * @param ideaId
	 * @param googs
	 * @return  根据id添加点赞数
	 */
	public static int addGood(int ideaId, int googs) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.addGood(ideaId, googs);
	}

	/**
	 * @param id
	 * @return  根据id获取点赞数、点击数和评论数
	 */
	public static HotIdea findGoodAndHitAndComm(int id) {
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findGoodAndHitAndComm(id);
	}
	
	
	public static List<HotIdea>  findIndexIdea(int count){
		HotIdeaDao dao=new HotIdeaImp();
		return dao.findIndexIdea(count);
	}

	
	
	

}
