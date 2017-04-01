package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.MyPresentDao;
import com.jucaipen.daoimp.MyPresentImp;
import com.jucaipen.model.MyPresent;

public class MyPresentSer {

	/**
	 * @param uId
	 * @param page
	 * @return 获取我拥有的礼品
	 */
	public static List<MyPresent> findPresentByUserId(int uId, int page) {
		MyPresentDao dao=new MyPresentImp();
		return dao.findPresentByUserId(uId, page);
	}

	/**
	 * @param id
	 * @return 根据id获取礼品信息
	 */
	public static MyPresent findPresentById(int id) {
		MyPresentDao dao=new MyPresentImp();
		return dao.findPresentById(id);
	}

	/**
	 * @param present
	 * @return 添加礼品
	 */
	public static int addPresent(MyPresent present) {
		MyPresentDao dao=new MyPresentImp();
		return dao.addPresent(present);
	}

	/**
	 * @param present
	 * @return 修改礼品信息
	 */
	public static int sendPresent(MyPresent present) {
		MyPresentDao dao=new MyPresentImp();
		return dao.sendPresent(present);
	}
	
	
	/**
	 * @param present
	 * @return 修改礼品信息
	 */
	public static int sendPresent(int id,int num) {
		MyPresentDao dao=new MyPresentImp();
		return dao.sendPresent(id, num);
	}
	
	/**
	 * @param uId
	 * @param pId
	 * @return 获取用户拥有的某个礼品数量
	 */
	public static MyPresent findParentByUid(int uId,int pId){
		MyPresentDao dao=new MyPresentImp();
		return dao.findParentByUid(uId, pId);
	}

}
