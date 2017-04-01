package com.jucaipen.service;

import java.util.List;
import com.jucaipen.dao.AccountDetailDao;
import com.jucaipen.daoimp.AccountDetailImp;
import com.jucaipen.model.AccountDetail;

public class AccountDetailSer {

	/**
	 * @param uId
	 * @return 根据用户id获取账号详细信息
	 */
	public static List<AccountDetail> findAccountDetailByuId(int uId, int page) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.findAccountDetailByuId(uId, page);
	}

	/**
	 * @param uId
	 * @param state
	 * @return 根据用户id和状态获取
	 */
	public static List<AccountDetail> findAccountDetailByUIdAndState(int uId,
			int state, int page) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.findAccountDetailByUIdAndState(uId, state, page);
	}

	/**
	 * @param uId
	 * @param type
	 * @return 根据用户id和分类获取
	 */
	public static List<AccountDetail> findAccountDetailByUidAndType(int uId,
			int type, int page) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.findAccountDetailByUidAndType(uId, type, page);
	}

	public static AccountDetail findDetailByOrderCode(String orderCode) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.findDetailByOrderCode(orderCode);
	}

	/**
	 * @param state
	 * @param type
	 * @param userId
	 * @param page
	 * @return 获取用户分类下的不同状态账户信息
	 */
	public static List<AccountDetail> findDetailByUserIdAndType(int userId,
			int state, int page) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.findDetailByUidAndState(userId, state, page);
	}

	/**
	 * @param id
	 * @return 删除账户详细信息
	 */
	public static int delDetails(int id) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.delAccountDetails(id);
	}

	/**
	 * @param detail
	 * @return 添加账户详细信息
	 */
	public static int addDetails(AccountDetail detail) {
		AccountDetailDao dao = new AccountDetailImp();
		return dao.addAccountDetails(detail);
	}

}
