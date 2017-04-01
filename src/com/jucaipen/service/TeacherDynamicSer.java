package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TeacherDynamicDao;
import com.jucaipen.daoimp.TeacherDynamicImp;
import com.jucaipen.model.TeacherDynamic;

public class TeacherDynamicSer{
	
	/**
	 * @param dynamic
	 * @return  添加讲师动态信息
	 */
	public int insertDynamic(TeacherDynamic dynamic){
		TeacherDynamicDao dao=new TeacherDynamicImp();
		return dao.insertDynamic(dynamic);
	}

	/**
	 * @return 获取所有的讲师动态
	 */
	public List<TeacherDynamic> findAllDynamic() {
		TeacherDynamicDao dao=new TeacherDynamicImp();
		return dao.findAllDynamic();
	}

	/**
	 * @param type
	 * @return  根据类型获取讲师动态
	 */
	public List<TeacherDynamic> findDynamicByType(int type) {
		TeacherDynamicDao dao=new TeacherDynamicImp();
		return dao.findDynamicByType(type);
	}

	/**
	 * @param id
	 * @return  根据id获取讲师动态详细信息
	 */
	public TeacherDynamic findDynamicById(int id) {
		TeacherDynamicDao dao=new TeacherDynamicImp();
		return dao.findDynamicById(id);
	}

}
