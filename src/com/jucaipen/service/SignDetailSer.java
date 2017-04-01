package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.SignDetailDao;
import com.jucaipen.daoimp.SignDetailImp;
import com.jucaipen.model.SignDetail;

public class SignDetailSer {

	/**
	 * @param userId
	 * @param page
	 * @return  根据用户id获取签到信息  分页
	 */
	public static  List<SignDetail> findSignDetailByUserId(int userId, int page) {
		SignDetailDao dao=new SignDetailImp();
		return dao.findSignDetailByUserId(userId, page);
	}
	
	
	/**
	 * @param userId
	 * @param page
	 * @return  根据用户id获取月签到信息
	 */
	public static  List<SignDetail> findMothSignDetailByUserId(int userId) {
		SignDetailDao dao=new SignDetailImp();
		return dao.findMothSignDetailByUserId(userId);
	}
	
	

	/**
	 * @param detail
	 * @return 添加签到详细信息
	 */
	public static int addSignDetail(SignDetail detail) {
		SignDetailDao dao=new SignDetailImp();
		return dao.addSignDetail(detail);
	}

}
