package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Rebate;

public interface RebateDao {
	
	/**
	 * @param id
	 * @return 通过id获取返利信息
	 */
	public  Rebate findRebateById(int id);
	
	
	public int contributeBills(int uId,int tId);
	
	/**
	 * @param teacherId
	 * @return 根据讲师获取返利信息  分页
	 */
	public List<Rebate> findRebateByTid(int teacherId,int page);
	
	
	/**
	 * @param userId
	 * @return  获取我的返利信息
	 */
	public List<Rebate> findRebateByUserId(int userId,int page);
	
	/**
	 * @param teacherId
	 * @param 
	 * @return  根据讲师获取返利信息  无分页
	 */
	public List<Rebate> findRebateByTid(int teacherId);

	
	/**
	 * @param rebate
	 * @return 添加返利记录
	 */
	public int addRebate(Rebate rebate);
	
	/**
	 * @param uId
	 * @param tId
	 * @return  获取用户贡献讲师聚财币信息
	 */
	public List<Rebate> findRebate(int uId,int tId);
	
	
	
	/**
	 * @param teacherId
	 * @return  获取月榜单
	 */
	public List<Rebate>   findRebateByTeacher(int teacherId,String type);
	
	
	/**
	 * @param type
	 * @return  获取最新的讲师排行榜
	 */
	public List<Rebate>  findLastTeacherLast(String type);

}
