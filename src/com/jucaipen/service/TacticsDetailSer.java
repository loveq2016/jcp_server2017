package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TacticsDetailsDao;
import com.jucaipen.daoimp.TacticsDetailsImp;
import com.jucaipen.model.TacticsDetails;

/**
 * @author Administrator
 *  战法详细信息
 */
public class TacticsDetailSer  {

	/**
	 * @param id
	 * @return  根据id获取战法详细信息
	 */
	public static TacticsDetails findDetailsById(int id) {
		TacticsDetailsDao dao=new TacticsDetailsImp();
		return dao.findDetailsById(id);
	}

	/**
	 * @param fkId
	 * @return 根据战法id获取战法详细信息
	 */
	public static List<TacticsDetails> findDetailsByFkId(int fkId,int page) {
		TacticsDetailsDao dao=new TacticsDetailsImp();
		return dao.findDetailsByFkId(fkId,page);
	}

}
