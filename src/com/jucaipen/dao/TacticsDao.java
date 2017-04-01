package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Tactics;

/**
 * @author Administrator
 *
 *  战法
 */
public interface TacticsDao {
	
	/**
	 * @param page
	 * @return 获取所有战法
	 */
	public List<Tactics> findAll(int page);
	
	/**
	 * @param id
	 * @return  根据id获取战法信息
	 */
	public Tactics findTacticsById(int id);
	
	/**
	 * @param tId
	 * @param page
	 * @return  根据讲师id获取战法信息   分页
	 */
	public List<Tactics> findTacticsByTeacherId(int tId,int page);
	/**
	 * @param tId
	 * @return  根据讲师id获取战法信息  无分页
	 */
	public List<Tactics> findTacticsByTeacherId(int tId);

}
