package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Fans;


/**
 * @author Administrator
 *
 *  粉丝
 */
public interface FansDao {
	
	/**
	 * @param userId
	 * @return 根据用户id获取我关注的讲师
	 */
	public List<Fans> findFansByUid(int userId,int page);
	
	public List<Fans> findFansByUid(int userId);
	
	/**
	 * @param teacherId
	 * @return  根据讲师id获取粉丝信息
	 */
	public List<Fans> findFansByTeacherId(int teacherId,int page);
	
	/**
	 * @param id
	 * @return 根据id获取粉丝信息
	 */
	public Fans findFansById(int id);
	
	/**
	 * @param uId
	 * @param tId
	 * @return 查询用户是否关注讲师
	 */
	public Fans findIsFans(int uId,int tId);
	
	/**
	 * @param fans
	 * @return  添加关注
	 */
	public int addFans(Fans fans);
	
	/**
	 * @param id
	 * @return  取消关注
	 */
	public int cancelFans(int tId,int uId);

}
