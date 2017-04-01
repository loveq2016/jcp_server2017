package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.WithdrawDao;
import com.jucaipen.daoimp.WithdrawImp;
import com.jucaipen.model.Withdraw;

public class WithdrawSer {

	/**
	 * @param id
	 * @return
	 *  根据id获取提现信息
	 */
	public static  Withdraw findWithDrawById(int id) {
		WithdrawDao dao=new WithdrawImp();
		return dao.findWithDrawById(id);
	}

	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取提现信息
	 */
	public static List<Withdraw> findWithDrawByUserId(int userId, int page) {
		WithdrawDao dao=new WithdrawImp();
		return dao.findWithDrawByUserId(userId, page);
	}

	/**
	 * @param withdraw
	 * @return 添加提现记录
	 */
	public static int addWithDraw(Withdraw withdraw) {
		WithdrawDao dao=new WithdrawImp();
		return dao.addWithDraw(withdraw);
	}

}
