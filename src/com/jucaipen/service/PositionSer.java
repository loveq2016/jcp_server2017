package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.PositionDao;
import com.jucaipen.daoimp.PositionImp;
import com.jucaipen.model.Position;

public class PositionSer {

	/**
	 * @param id
	 * @return 根据id获取岗位信息
	 */
	public static Position findPositionById(int id) {
		PositionDao dao=new PositionImp();
		return dao.findPositionById(id);
	}

	/**
	 * @return 获取所有的岗位信息
	 */
	public static List<Position> findAll() {
		PositionDao dao=new PositionImp();
		return dao.findAll();
	}
	
	/**
	 * @param cId
	 * @return 根据资格证类型获取相应的岗位
	 */
	public static List<Position> findPositionByCid(int cId){
		PositionDao dao=new PositionImp();
		return dao.findPositionByCid(cId);
		
	};

}
