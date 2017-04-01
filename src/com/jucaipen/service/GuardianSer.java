package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.GuardianDao;
import com.jucaipen.daoimp.GuardianImp;
import com.jucaipen.model.Guardian;

/**
 * @author Administrator
 *
 *  守护者
 */
public class GuardianSer  {

	/**
	 * @param id
	 * @return 通过id获取守护信息
	 */
	public static  Guardian findGuardianById(int id) {
		GuardianDao dao=new GuardianImp();
		return dao.findGuardianById(id);
	}

	/**
	 * @param userId
	 * @param page
	 * @return 通过用户id获取我守护的
	 */
	public static List<Guardian> findGurdianByUid(int userId, int page) {
		GuardianDao dao=new GuardianImp();
		return dao.findGurdianByUid(userId, page);
	}

	/**
	 * @param teacherId
	 * @param page
	 * @return 通过讲师id获取守护我的  分页
	 */
	public static List<Guardian> findGuradianByTeacherId(int teacherId, int page) {
		GuardianDao dao=new GuardianImp();
		return dao.findGuradianByTeacherId(teacherId, page);
	}

	
	/**
	 * @param teacherId
	 * @return 通过讲师id获取守护我的   无分页
	 */
	public static List<Guardian> findGuradianByTeacherId(int teacherId) {
		GuardianDao dao=new GuardianImp();
		return dao.findGuradianByTeacherId(teacherId);
	}
	
	
	/**
	 * @param guardian
	 * @return 添加守护信息
	 */
	public static int addGuardian(Guardian guardian) {
		GuardianDao dao=new GuardianImp();
		return dao.addGuardian(guardian);
	}
	
	/**
	 * @param teacherId
	 * @param userId
	 * @return  获取守护状态
	 */
	public static Guardian findIsGuardian(int teacherId,int userId){
		GuardianDao dao=new GuardianImp();
		return dao.findIsGuardian(teacherId, userId);
	}

}
