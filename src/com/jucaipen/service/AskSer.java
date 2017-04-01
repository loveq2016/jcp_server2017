package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.AskDao;
import com.jucaipen.daoimp.AskImp;
import com.jucaipen.model.Ask;

public class AskSer{

	/**
	 * @return 获取所有提问
	 */
	public static List<Ask> findAllAsk(int page) {
		AskDao dao=new AskImp();
		return dao.findAllAsk(page);
	}
	
	public static List<Ask> findAskByUidAndTeacherId(int userId, int teacherId){
		AskDao dao=new AskImp();
		return dao.findAskByUidAndTeacherId(userId, teacherId);
	}

	/**
	 * @param count
	 * @return  获取最近几条提问
	 */
	public static List<Ask> findLstAsk(int count) {
		AskDao dao=new AskImp();
		return dao.findLstAsk(count);
	}
	
	public static int updateHits(int id, int hits, int xnHits){
		AskDao dao=new AskImp();
		return dao.updateHits(id, hits, xnHits);
	}
	
	public static List<Ask> findAskByParentId(int pId){
		AskDao dao=new AskImp();
		return dao.findAskByParentId(pId);
	}
	
	
	/**
	 * @param uId
	 * @return   根据用户id获取用户的提问数
	 */
	public static int findAskNumByUid(int uId){
		AskDao dao=new AskImp();
		return dao.findAskNumByUId(uId);
	}
	
	/**
	 * @param userId
	 * @return 根据用户id获取提问信息
	 */
	public static List<Ask> findAskByUserId(int userId) {
		AskDao dao=new AskImp();
		return dao.findAskByUserId(userId);
	}

	/**
	 * @param userId
	 * @return 根据用户id获取提问信息
	 */
	public static List<Ask> findAskByUserId(int userId,int page) {
		AskDao dao=new AskImp();
		return dao.findAskByUserId(userId,page);
	}

	/**
	 * @param teacherId
	 * @return 根据讲师id获取提问信息
	 */
	public static List<Ask> findAskByTeacherId(int teacherId,int page) {
		AskDao dao=new AskImp();
		return dao.findAskByTeacherId(teacherId,page);
	}
	
	public static List<Ask> findLastByTeacherId(int teacherId,int count){
		AskDao dao=new AskImp();
		return dao.findLastByTeacherId(teacherId, count);
	}

	/**
	 * @param classId
	 * @return 根据分类id获取提问信息
	 */
	public static List<Ask> findAskByClassId(int classId) {
		AskDao dao=new AskImp();
		return dao.findAskByClassId(classId);
	}

	/**
	 * @param state
	 * @return 根据提问状态获取提问信息
	 */
	public static List<Ask> findAskByAskState(int state) {
		AskDao dao=new AskImp();
		return dao.findAskByAskState(state);
	}

	/**
	 * @param isReply
	 * @return 根据提问回复状态获取提问信息
	 */
	public static List<Ask> findAskByIsReply(int isReply) {
		AskDao dao=new AskImp();
		return dao.findAskByIsReply(isReply);
	}

	/**
	 * @param id
	 * @return 根据提问id获取提问详细信息
	 */
	public static Ask findAskById(int id) {
		AskDao dao=new AskImp();
		return dao.findAskById(id);
	}

	/**
	 * @param ask
	 * @return 添加提问信息
	 */
	public static int insertAsk(Ask ask) {
		AskDao dao=new AskImp();
		return dao.insertAsk(ask);
	}

}
