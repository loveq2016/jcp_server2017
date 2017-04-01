package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Answer;


/**
 * @author Administrator
 *
 * 回答
 */
public interface AnswerDao {
	
	/**
	 * @param answer
	 * @return  添加回答
	 */
	public int insertAnswer(Answer answer);
	
	/**
	 * @param id
	 * @return  根据id获取回答
	 */
	public Answer findAnswerById(int id);
	
	/**
	 * @return  获取所有回答
	 */
	public List<Answer> findAllAnswer(int page);
	
	/**
	 * @param teacherId
	 * @return  根据讲师id获取回答
	 */
	public List<Answer> findAnswerByTeacherId(int teacherId);
	
	/**
	 * @param askId
	 * @return  根据提问获取回答
	 */
	public  List<Answer> findAnswerByAskId(int askId);
	
	/**
	 * @param teacherId
	 * @param count
	 * @return  获取讲师最近的几条问答
	 */
	public List<Answer> findAnswerByTeacherAndLast(int teacherId,int count);
	
	/**
	 * @param count
	 * @return   查询最近几条回答
	 */
	public List<Answer> findAnswerByLast(int count);
	

}
