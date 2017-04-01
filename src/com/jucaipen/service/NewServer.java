package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.NewsDao;
import com.jucaipen.daoimp.NewsImp;
import com.jucaipen.model.News;

/**
 * @author ylf
 * 
 *         新闻服务类
 * 
 */
public class NewServer {
	private static List<News> news;

	/**
	 * @param pager
	 * @return 根据页数查询指定页的新闻信息
	 */
	public static List<News> queryNews(int pager) {
		NewsDao dao = new NewsImp();
		news = dao.findAll(pager);
		return news;

	}

	/**
	 * @param bigId
	 * @param pager
	 * @return 根据一级分类查询新闻信息
	 */
	public static List<News> queryNewsByBigId(int bigId, int pager) {
		NewsDao dao = new NewsImp();
		return dao.findNewsBybigId(bigId, pager);
	}


	/**
	 * @param id
	 * @return 根据id 查询新闻详细内容
	 */
	public static News findNewsById(int id) {
		NewsDao dao = new NewsImp();
		return dao.findNews(id);
	}

	/**
	 * @param id
	 * @return 获取相关新闻
	 */
	public static List<News> findRelatedNewsById(int id) {
		NewsDao dao = new NewsImp();
		return dao.findRelatedNewsById(id);
	}

	/**
	 * @param count
	 * @return 获取最近的count条新闻
	 */
	public static List<News> findLastNews(int count) {
		NewsDao dao = new NewsImp();
		return dao.findLastNewsByNewsNum(count);

	}
	
	/**
	 * @param hits
	 * @param id
	 * @return   修改点击数
	 */
	public static int upDateHits(int xnHits,int hits,int id){
		NewsDao dao=new NewsImp();
		return dao.upDateHits(xnHits,hits, id);
	}
	
	/**
	 * @param comments
	 * @param id
	 * @return   修改评论数
	 */
	public static int upDateComments(int comments,int id){
		NewsDao dao=new NewsImp();
		return dao.upDateComments(comments, id);
	}
	
	/**
	 * @param classId
	 * @param page
	 * @return   根据分类查询新闻
	 */
	public static List<News>  findNewsByClassId(int classId,int page){
		NewsDao dao=new NewsImp();
		return dao.findNewsBybigId(classId, page);
	}

}
