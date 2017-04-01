package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.BankDao;
import com.jucaipen.model.Bank;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *  银行信息
 */
public class BankImp implements BankDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Bank> banks=new ArrayList<Bank>();

	@Override
	public List<Bank> findAllBank() {
		// 获取所有银行信息
		banks.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Bank");
			while (res.next()) {
				int id=res.getInt(1);  //Id
				String name=res.getString(2);  //BankName
				String channel=res.getString(3); //BankChannel
				String bankClass=res.getString(4); //BankClass
				Bank bank=new Bank();
				bank.setId(id);
				bank.setBankName(name);
				bank.setBankChannel(channel);
				bank.setBankClass(bankClass);
				banks.add(bank);
			}
			return banks;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Bank findBankById(int id) {
		//根据id获取银行信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Bank WHERE Id="+id);
			while (res.next()) {
				String name=res.getString(2);  //BankName
				String channel=res.getString(3); //BankChannel
				String bankClass=res.getString(4); //BankClass
				Bank bank=new Bank();
				bank.setId(id);
				bank.setBankName(name);
				bank.setBankChannel(channel);
				bank.setBankClass(bankClass);
				return bank;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
