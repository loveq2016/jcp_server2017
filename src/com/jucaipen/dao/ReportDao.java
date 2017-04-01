package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Report;

public interface ReportDao {
	
	/**
	 * @param id
	 * @return  根据id获取举报信息
	 */
	public Report findRepoterById(int id);
	
	/**
	 * @param userId
	 * @return 根据用户id获取举报信息
	 */
	public List<Report> findRepoterByUserId(int userId,int page);
	
	/**
	 * @param userId
	 * @param type
	 * @param page
	 * @return 获取用户下的分类举报信息
	 */
	public List<Report> findRepoterByUserIdAndType(int userId,int type,int page);
	/**
	 * @param report
	 * @return 添加举报信息
	 */
	public int addRepoter(Report report);

}
