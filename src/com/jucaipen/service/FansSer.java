package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.FansDao;
import com.jucaipen.daoimp.FansImp;
import com.jucaipen.model.Fans;

/**
 * @author Administrator
 *
 * 粉丝信息
 */
public class FansSer {

	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取粉丝信息
	 */
	public static List<Fans> findFansByUid(int userId, int page) {
		FansDao dao=new FansImp();
		return dao.findFansByUid(userId, page);
	}
	
	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取粉丝信息
	 */
	public static List<Fans> findFansByUid(int userId) {
		FansDao dao=new FansImp();
		return dao.findFansByUid(userId);
	}


	/**
	 * @param teacherId
	 * @param page
	 * @return 根据讲师id获取粉丝信息
	 */
	public static List<Fans> findFansByTeacherId(int teacherId, int page) {
		FansDao dao=new FansImp();
		return dao.findFansByTeacherId(teacherId, page);
	}

	/**
	 * @param id
	 * @return 根据id获取粉丝信息
	 */
	public static Fans findFansById(int id) {
		FansDao dao=new FansImp();
		return dao.findFansById(id);
	}
	
	/**
	 * @param uId
	 * @param tId
	 * @return  判断用户是否关注该讲师
	 */
	public static Fans findIsFans(int uId,int tId){
		FansDao dao=new FansImp();
		return dao.findIsFans(uId, tId);
	}
	
	/**
	 * @param fans
	 * @return  添加关注
	 */
	public static int addFans(Fans fans){
		FansDao dao=new FansImp();
		return dao.addFans(fans);
	}
	
	/**
	 * @param id
	 * @return  取消关注
	 */
	public static int cancelFans(int tId,int uId){
		FansDao dao=new FansImp();
		return dao.cancelFans(tId,uId);
	}

}
