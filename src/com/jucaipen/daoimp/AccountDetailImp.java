package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.AccountDetailDao;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.utils.JdbcUtil;

public class AccountDetailImp implements AccountDetailDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();

	/**
	 * @return 查询账户信息总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Account_Detail "
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
	public List<AccountDetail> findAccountDetailByuId(int userId, int page) {
		// 根据用户id获取账户详细信息
		accountDetails.clear();
		int totlePage = getTotlePage("WHERE UserId=" + userId+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Account_Detail WHERE UserId="
							+ userId + " AND IsDel=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String orderCode = res.getString("OrderCode");
				double detailMoney = res.getDouble("DetailMoney");
				int detailType = res.getInt("DetailType");
				int state = res.getInt("State");
				String remark = res.getString("Remark");
				String insertDate = res.getString("InsertDate");
				int isDel = res.getInt("IsDel");
				AccountDetail detail = new AccountDetail(id, orderCode,
						detailMoney, detailType, state, remark, insertDate,
						isDel, userId);
				detail.setPage(page);
				detail.setTotlePage(totlePage);
				accountDetails.add(detail);
			}
			return accountDetails;
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
	public List<AccountDetail> findAccountDetailByUIdAndState(int uId,
			int state, int page) {
		// 根据用户id和状态
		accountDetails.clear();
		int totlePage = getTotlePage("WHERE UserId=" + uId + " AND State="
				+ state+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Account_Detail WHERE UserId="
							+ uId + " AND State=" + state + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String orderCode = res.getString("OrderCode");
				double detailMoney = res.getDouble("DetailMoney");
				int detailType = res.getInt("DetailType");
				String remark = res.getString("Remark");
				String insertDate = res.getString("InsertDate");
				int isDel = res.getInt("IsDel");
				AccountDetail detail = new AccountDetail(id, orderCode,
						detailMoney, detailType, state, remark, insertDate,
						isDel, uId);
				detail.setTotlePage(totlePage);
				detail.setPage(page);
				accountDetails.add(detail);
			}
			return accountDetails;
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
	public List<AccountDetail> findAccountDetailByUidAndType(int uId, int type,
			int page) {
		// 根据用户id和分类获取
		accountDetails.clear();
		int totlePage = getTotlePage("WHERE DetailType=" + type
				+ " AND UserId=" + uId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Account_Detail WHERE UserId="
							+ uId + " AND DetailType=" + type + " AND IsDel=0) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String orderCode = res.getString("OrderCode");
				double detailMoney = res.getDouble("DetailMoney");
				int detailType = res.getInt("DetailType");
				String remark = res.getString("Remark");
				String insertDate = res.getString("InsertDate");
				int isDel = res.getInt("IsDel");
				int state = res.getInt("State");
				AccountDetail detail = new AccountDetail(id, orderCode,
						detailMoney, detailType, state, remark, insertDate,
						isDel, uId);
				detail.setTotlePage(totlePage);
				detail.setPage(page);
				accountDetails.add(detail);
			}
			return accountDetails;
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
	public List<AccountDetail> findDetailByUidAndState(int uId,
			int state,  int page) {
		// 根据用户id获取分类下的相应状态账单信息
		accountDetails.clear();
		int totlePage = getTotlePage("WHERE  UserId=" + uId + " AND State=" + state+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Account_Detail WHERE UserId="
							+ uId +  " AND State="
							+ state + " AND IsDel=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String orderCode = res.getString("OrderCode");
				double detailMoney = res.getDouble("DetailMoney");
				int detailType = res.getInt("DetailType");
				String remark = res.getString("Remark");
				String insertDate = res.getString("InsertDate");
				int isDel = res.getInt("IsDel");
				AccountDetail detail = new AccountDetail(id, orderCode,
						detailMoney, detailType, state, remark, insertDate,
						isDel, uId);
				detail.setTotlePage(totlePage);
				detail.setPage(page);
				accountDetails.add(detail);
			}
			return accountDetails;
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
	public int addAccountDetails(AccountDetail detail) {
		// 添加账户详细信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int delAccountDetails(int id) {
		// 删除账户详细信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("UPDATE JCP_Account_Detail SET IsDel=1 WHERE Id="
							+ id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public AccountDetail findDetailByOrderCode(String orderCode) {
		// 根据用户id获取账户详细信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Account_Detail WHERE OrderCode='"+orderCode+"'");
			while (res.next()) {
				int id = res.getInt("Id");
				AccountDetail detail = new AccountDetail();
				detail.setId(id);
				return detail;
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

}
