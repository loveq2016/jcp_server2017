package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Ask;

/**
 * @author Administrator
 *
 *  获取提问信息
 *  
 */
public interface AskDao {
	
	/**
	 * @param condition
	 * @return   获取咨询信息总页数
	 */
	public int findTotlePage(String condition);
	
	public List<Ask>  findAskByUidAndTeacherId(int userId,int teacherId);
	
	/**
	 * @param pId
	 * @return  
	 */
	public List<Ask> findAskByParentId(int pId);
	
	/**
	 * @return  获取所有提问信息
	 */
	public List<Ask> findAllAsk(int page);
	
	/**
	 * @param count
	 * @return 获取最近的几条提问
	 */
	public List<Ask> findLstAsk(int count);
	/**
	 * @param userId
	 * @return  获取当前用户下的所有提问
	 */
	public List<Ask> findAskByUserId(int userId);
	/**
	 * @param userId
	 * @return  获取当前用户下的所有提问
	 */
	public List<Ask> findAskByUserId(int userId,int page);
	/**
	 * @param teacherId
	 * @return 获取当前讲师下的所有提问
	 */
	public List<Ask> findAskByTeacherId(int teacherId,int page);
	
	/**
	 * @param teacherId
	 * @param count
	 * @return 获取讲师最近的几条问答
	 */
	public List<Ask> findLastByTeacherId(int teacherId,int count);
	
	/**
	 * @param classId
	 * @return 根据分类查询提问信息
	 */
	public List<Ask> findAskByClassId(int classId);
	/**
	 * @param state
	 * @return  通过状态，查询提问信息 2不显示1为显示
	 */
	public List<Ask> findAskByAskState(int state);
	/**
	 * @param isReply
	 * @return 根据回复状态查询提问信息 2未回复；1已回复
	 */
	public List<Ask> findAskByIsReply(int isReply);
	
	/**
	 * @param id
	 * @return 根据id查询提问信息
	 */
	public Ask findAskById(int id);
	/**
	 * @param ask
	 * @return 添加问题
	 */
	public int insertAsk(Ask ask);
	
	
	/**
	 * @param uId
	 * @return    根据用户id获取当前提问数
	 */
	public int findAskNumByUId(int uId);
	
	/**
	 * @param id
	 * @param hits
	 * @param xnHits
	 * @return  更新点击数
	 */
	public int updateHits(int id,int hits,int xnHits);

}
