package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TeacherDynamic;

public interface TeacherDynamicDao {
	
	
	/**
	 * @param dynamic
	 * @return  添加讲师动态
	 */
	public int insertDynamic(TeacherDynamic dynamic);
	
	/**
	 * @return 获取讲师所有动态
	 */
	public List<TeacherDynamic> findAllDynamic();
	/**
	 * @param type
	 * @return 根据分类获取讲师动态信息
	 */
	public List<TeacherDynamic> findDynamicByType(int type);
	/**
	 * @param id
	 * @return 根据动态id获取讲师动态信息
	 */
	public TeacherDynamic findDynamicById(int id);

}
