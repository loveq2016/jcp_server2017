package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.WithdrawDao;
import com.jucaipen.model.Withdraw;
import com.jucaipen.utils.JdbcUtil;

public class WithdrawImp implements WithdrawDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Withdraw> withdraws = new ArrayList<Withdraw>();

	/**
	 * @return 查询提现记录总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TiXian "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

	@Override
	public Withdraw findWithDrawById(int id) {
		// 根据id获取提现信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TiXian WHERE Id=" + id+" AND IsDel=0");
			while (res.next()) {
				String orderCode = res.getString(2); // OrderCode
				int userId = res.getInt(3); // UserId
				String bankCode = res.getString(4); // BankCode
				int bankType = res.getInt(5); // BankType
				String trueName = res.getString(6); // BankUserTrueName
				String bankName = res.getString(7); // BankName
				double outMoney = res.getDouble(8); // OutMoney
				String insertDate = res.getString(9); // InsertDate
				int type = res.getInt(10); // Type
				String shenheDate = res.getString(11); // ShenHeDate
				int isPay = res.getInt(12); // IsPay
				String remark = res.getString(14); // Remark
				Withdraw withdraw = new Withdraw();
				withdraw.setOrderCode(orderCode);
				withdraw.setUserId(userId);
				withdraw.setBankCode(bankCode);
				withdraw.setBankType(bankType);
				withdraw.setBankUserTrueName(trueName);
				withdraw.setBankName(bankName);
				withdraw.setOutMoney(outMoney);
				withdraw.setInsertDate(insertDate);
				withdraw.setType(type);
				withdraw.setCheckDate(shenheDate);
				withdraw.setIsPay(isPay);
				withdraw.setRemark(remark);
				return withdraw;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<Withdraw> findWithDrawByUserId(int userId, int page) {
		// 根据用户id获取所有的提现信息
		withdraws.clear();
		int totlePage = getTotlePage(" WHERE UserId=" + userId+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TiXian WHERE UserId="
							+ userId + " AND IsDel=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String orderCode = res.getString("OrderCode"); // OrderCode
				String bankCode = res.getString("BankCode"); // BankCode
				int bankType = res.getInt("BankType"); // BankType
				String trueName = res.getString("BankUserTrueName"); // BankUserTrueName
				String bankName = res.getString("BankName"); // BankName
				double outMoney = res.getDouble("OutMoney"); // OutMoney
				String insertDate = res.getString("InsertDate"); // InsertDate
				int type = res.getInt("Type"); // Type
				String shenheDate = res.getString("ShenHeDate"); // ShenHeDate
				int isPay = res.getInt("IsPay"); // IsPay
				String remark = res.getString("Remark"); // Remark
				Withdraw withdraw = new Withdraw();
				withdraw.setOrderCode(orderCode);
				withdraw.setUserId(userId);
				withdraw.setBankCode(bankCode);
				withdraw.setId(id);
				withdraw.setTotlePage(totlePage);
				withdraw.setPage(page);
				withdraw.setBankType(bankType);
				withdraw.setBankUserTrueName(trueName);
				withdraw.setBankName(bankName);
				withdraw.setOutMoney(outMoney);
				withdraw.setInsertDate(insertDate);
				withdraw.setType(type);
				withdraw.setCheckDate(shenheDate);
				withdraw.setIsPay(isPay);
				withdraw.setRemark(remark);
				withdraws.add(withdraw);
			}
			return withdraws;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int addWithDraw(Withdraw withdraw) {
		// 添加提现记录
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_TiXian (OrderCode,UserId,"
							+ "BankCode,BankType,BankUserTrueName,BankName,OutMoney,"
							+ "InsertDate,Type,ShenHeDate,IsPay,IsDel,Remark) VALUES ('"
							+ withdraw.getOrderCode()
							+ "',"
							+ withdraw.getUserId()
							+ ",'"
							+ withdraw.getBankCode()
							+ "',"
							+ withdraw.getBankType()
							+ ",'"
							+ withdraw.getBankUserTrueName()
							+ "','"
							+ withdraw.getBankName()
							+ "','"
							+ withdraw.getOutMoney()
							+ "','"
							+ withdraw.getInsertDate()
							+ "',"
							+ withdraw.getType()
							+ ",'"
							+ withdraw.getCheckDate()
							+ "',"
							+ withdraw.getIsPay()
							+ ","
							+ withdraw.getIsDel()
							+ ",'" + withdraw.getRemark() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
