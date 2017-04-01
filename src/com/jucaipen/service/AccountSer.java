package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.AccountDao;
import com.jucaipen.daoimp.AccountImp;
import com.jucaipen.model.Account;

public class AccountSer{

	/**
	 * @return  获取所有账户信息
	 */
	public static List<Account> findAllAccount() {
		AccountDao dao=new AccountImp();
		return dao.findAllAccount();
	}

	/**
	 * @param id
	 * @return  根据id获取账户信息
	 */
	public static Account findAccountById(int id) {
		AccountDao dao=new AccountImp();
		return dao.findAccountById(id);
	}

	/**
	 * @param uId
	 * @return  根据用户信息获取账户信息
	 */
	public static Account findAccountByUserId(int uId) {
		AccountDao dao=new AccountImp();
		return dao.findAccountByUserId(uId);
	}
	
	/**
	 * @param uId
	 * @param newBills
	 * @return  修改聚财币数量
	 */
	public static int updateBills(int uId,int newBills){
		AccountDao dao=new AccountImp();
		return dao.updateBills(uId, newBills);
	}
	
	/**
	 * @param uId
	 * @param integeral
	 * @return 修改积分信息
	 */
	public static int updateIntegeral(int uId,int integeral){
		AccountDao dao=new AccountImp();
		return dao.updateIntegeral(uId, integeral);
	}
	
	/**
	 * @param account
	 * @return  添加账户信息
	 */
	public static int addAccount(Account account){
		AccountDao dao=new AccountImp();
		return dao.addAccount(account);
	}

}
