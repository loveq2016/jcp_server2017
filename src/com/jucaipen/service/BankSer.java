package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.BankDao;
import com.jucaipen.daoimp.BankImp;
import com.jucaipen.model.Bank;

/**
 * @author Administrator
 *
 *  银行信息
 */
public class BankSer {

	/**
	 * @return 获取所有银行信息
	 */
	public static List<Bank> findAllBank() {
		BankDao dao=new BankImp();
		return dao.findAllBank();
	}

	/**
	 * @param id
	 * @return 根据id获取银行信息
	 */
	public static Bank findBankById(int id) {
		BankDao dao=new BankImp();
		return dao.findBankById(id);
	}

}
