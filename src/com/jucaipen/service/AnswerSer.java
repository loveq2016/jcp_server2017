package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.AnswerDao;
import com.jucaipen.daoimp.AnswerImp;
import com.jucaipen.model.Answer;

public class AnswerSer{

	/**
	 * @param answer
	 * @return  添加回复
	 */
	public static int insertAnswer(Answer answer) {
		AnswerDao dao=new AnswerImp();
		return dao.insertAnswer(answer);
	}

	/**
	 * @param id
	 * @return  根据id获取回复信息
	 */
	public static Answer findAnswerById(int id) {
		AnswerDao dao=new AnswerImp();
		return dao.findAnswerById(id);
	}

	/**
	 * @return  获取所有回复信息
	 */
	public static List<Answer> findAllAnswer(int page) {
		AnswerDao dao=new AnswerImp();
		return dao.findAllAnswer(page);
	}
	
	/**
	 * @param count
	 * @return  获取最近几条回答
	 */
	public static List<Answer> findAnswerByLast(int count){
		AnswerDao dao=new AnswerImp();
		return dao.findAnswerByLast(count);
	}

	/**
	 * @param teacherId
	 * @return  根据讲师id获取回复信息
	 */
	public static List<Answer> findAnswerByTeacherId(int teacherId) {
		AnswerDao dao=new AnswerImp();
		return dao.findAnswerByTeacherId(teacherId);
	}

	/**
	 * @param askId
	 * @return  根据提问获取回复信息
	 */
	public static List<Answer> findAnswerByAskId(int askId) {
		AnswerDao dao=new AnswerImp();
		return dao.findAnswerByAskId(askId);
	}

	/**
	 * @param teacherId
	 * @param count
	 * @return  根据讲师id 获取最近的几条回复
	 */
	public static List<Answer> findAnswerByTeacherAndLast(int teacherId, int count) {
		AnswerDao dao=new AnswerImp();
		return dao.findAnswerByTeacherAndLast(teacherId, count);
	}

}
