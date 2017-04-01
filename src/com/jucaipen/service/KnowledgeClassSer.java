package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.KnowledgeClassDao;
import com.jucaipen.daoimp.KnowledgeClassImp;
import com.jucaipen.model.KnowledgeClass;

public class KnowledgeClassSer  {

	/**
	 * @param id
	 * @return 根据id获取知识分类信息
	 */
	public static KnowledgeClass findKnowledgeById(int id) {
		KnowledgeClassDao dao=new KnowledgeClassImp();
		return dao.findKnowledgeById(id);
	}

	/**
	 * @return 获取所有的知识分类信息
	 */
	public static List<KnowledgeClass> findAllKnowledgeClass() {
		KnowledgeClassDao dao=new KnowledgeClassImp();
		return dao.findAllKnowledgeClass();
	}
	
	/**
	 * @param pId
	 * @return  根据父级id获取分类信息
	 */
	public static List<KnowledgeClass> findKnowClassByPid(int pId){
		KnowledgeClassDao dao=new KnowledgeClassImp();
		return dao.findKnowClassByPid(pId);
		
	}

}
