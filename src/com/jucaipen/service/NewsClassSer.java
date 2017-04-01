package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.NewsClassDao;
import com.jucaipen.daoimp.NewsClassImp;
import com.jucaipen.model.NewsClass;

public class NewsClassSer {
	
	/**
	 * @return  获取全部分类信息
	 */
	public static List<NewsClass> getAllClass(){
		NewsClassDao dao=new NewsClassImp();
		return dao.findAllNewsClass();
	}
	
	/**
	 * @param pId
	 * @return 根据父id获取分类信息
	 */
	public static List<NewsClass> getClassByPId(int pId){
		NewsClassDao dao=new NewsClassImp();
		return dao.findNewsClassByPId(pId);
	}
	
	/**
	 * @param id
	 * @return 根据id获取分类信息
	 */
	public static NewsClass findClassById(int id){
		NewsClassDao dao=new NewsClassImp();
		return dao.findClassById(id);
	}

}
