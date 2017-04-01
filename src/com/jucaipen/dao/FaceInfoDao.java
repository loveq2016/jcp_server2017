package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.ExpressionInfo;

/**
 * @author YLF
 * 
 *     表情详细信息
 *
 */
public interface FaceInfoDao {
	
	/**
	 * @return   获取所有表情信息
	 */
	public List<ExpressionInfo> findAllFaceInfo();
	
	/**
	 * @param typeId
	 * @return   获取当前分类下的所有表情信息
	 */
	public List<ExpressionInfo>  findFaceInfoByTypeId(int typeId);
	
	/**
	 * @param id
	 * @return  根据id获取表情信息
	 */
	public ExpressionInfo findFaceInfoById(int id);

}
