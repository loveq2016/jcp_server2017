package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.MySpecialDao;
import com.jucaipen.daoimp.MySpecialImp;
import com.jucaipen.model.MySpecial;

public class MySpecialSer {

	/**
	 * @param id
	 * @return 通过id获取我的专辑信息
	 */
	public static MySpecial findSpecialById(int id) {
		MySpecialDao dao=new MySpecialImp();
		return dao.findSpecialById(id);
	}
	
	public static int getSpecialSallNum(int specialId){
		MySpecialDao dao=new MySpecialImp();
		return dao.getSpecialSallNum(specialId);
	}

	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取我的专辑信息
	 */
	public static List<MySpecial> findSpecialByUid(int userId, int page) {
		MySpecialDao dao=new MySpecialImp();
		return dao.findSpecialByUid(userId, page);
	}

	/**
	 * @param special
	 * @return 添加专辑信息
	 */
	public int addSpecial(MySpecial special) {
		MySpecialDao dao=new MySpecialImp();
		return dao.addSpecial(special);
	}

	/**
	 * @param id
	 * @return 删除专辑记录
	 */
	public static int removeSpecial(int id) {
		MySpecialDao dao=new MySpecialImp();
		return dao.removeSpecial(id);
	}
	
	public static MySpecial getIsMySpecial(int uId,int sId){
		MySpecialDao dao=new MySpecialImp();
		return dao.getIsMySpecial(uId, sId);
		
	}

}
