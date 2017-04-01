package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.FaceTypeDao;
import com.jucaipen.daoimp.FaceTypeImp;
import com.jucaipen.model.ExpressionType;

/**
 * @author YLF
 * 
 *         表情分类
 * 
 */
public class FaceTypeServer {

	/**
	 * @return 获取所有表情分类分类信息
	 */
	public static List<ExpressionType> findAllFace() {
		FaceTypeDao dao = new FaceTypeImp();
		return dao.findAllFace();
	}

	/**
	 * @param id
	 * @return 根据id获取表情分类信息
	 */
	public static ExpressionType findFaceById(int id) {
		FaceTypeDao dao=new FaceTypeImp();
		return dao.findFaceById(id);
	}
	
}
