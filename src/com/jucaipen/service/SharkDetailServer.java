package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.SharkDetailDao;
import com.jucaipen.daoimp.SharkDetailImp;
import com.jucaipen.model.SharkDetail;

public class SharkDetailServer {

	
	/**
	 * @param uId
	 * @return  根据用户id获取摇一摇信息
	 */
	public static List<SharkDetail> getSharkList(int uId) {
		SharkDetailDao dao=new SharkDetailImp();
		return dao.getSharkList(uId);
	}

	/**
	 * @param uId
	 * @return  根据用户id获取摇一摇次数
	 */
	public static int getSharkCount(int uId) {
		SharkDetailDao dao=new SharkDetailImp();
		return dao.getSharkCount(uId);
	}

	/**
	 * @param detail
	 * @return  添加摇一摇信息
	 */
	public static int addDetail(SharkDetail detail) {
		SharkDetailDao dao=new SharkDetailImp();
		return dao.addDetail(detail);
	}
	
	/**
	 * @param min
	 * @param max
	 * @return  获取当前区间摇一摇的次数
	 */
	public static int getMaxCount(int min,int max){
		SharkDetailDao dao=new SharkDetailImp();
		return dao.getMaxCount(min, max);
	}

}
