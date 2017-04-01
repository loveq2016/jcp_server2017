package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.InvestmentType;


/**
 * @author Administrator
 *
 *  投资风格
 */
public interface InvestmentTypeDao {
	
	/**
	 * @param id
	 * @return 通过id获取投资风格
	 */
	public InvestmentType findTypeById(int id);
	
	/**
	 * @return 获取所有的投资风格
	 */
	public List<InvestmentType> findAllTypes();

}
