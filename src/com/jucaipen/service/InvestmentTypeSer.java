package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.InvestmentTypeDao;
import com.jucaipen.daoimp.InvestmentTypeImp;
import com.jucaipen.model.InvestmentType;

public class InvestmentTypeSer {

	/**
	 * @param id
	 * @return 根据id获取风格信息
	 */
	public static InvestmentType findTypeById(int id) {
		InvestmentTypeDao dao=new InvestmentTypeImp();
		return dao.findTypeById(id);
	}

	/**
	 * @return  获取所有的风格信息
	 */
	public static List<InvestmentType> findAllTypes() {
		InvestmentTypeDao dao=new InvestmentTypeImp();
		return dao.findAllTypes();
	}

}
