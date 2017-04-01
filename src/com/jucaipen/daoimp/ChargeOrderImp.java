package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.ChargeOrderDao;
import com.jucaipen.model.ChargeOrder;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         充值订单
 */
public class ChargeOrderImp implements ChargeOrderDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<ChargeOrder> orders = new ArrayList<ChargeOrder>();

	/**
	 * @return 查询充值订单信息总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_AddOrder "
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
	public ChargeOrder findOrderById(int id) {
		// 根据id获取充值订单
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_AddOrder WHERE Id=" + id
					+ " AND IsDel=" + 0);
			while (res.next()) {
				int userId = res.getInt(2); // UserId
				String orderCode = res.getString(3); // OrderCode
				double payMoney = res.getDouble(4); // Pay_money
				String insertDate = res.getString(5); // InsertDate
				String payDate = res.getString(6); // PaymentDate
				int payType = res.getInt(9); // PaymentMethod
				String alipayAccount = res.getString(10); // AlipayAccount
				int orderState = res.getInt(12); // OrderState
				ChargeOrder order = new ChargeOrder();
				order.setUserId(userId);
				order.setOrderCode(orderCode);
				order.setChargeMoney(payMoney);
				order.setInsertDate(insertDate);
				order.setPayDate(payDate);
				order.setPayType(payType);
				order.setAlipayAccount(alipayAccount);
				order.setOrderState(orderState);
				return order;
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
	public List<ChargeOrder> findOrderByUid(int userId, int page) {
		// 根据用户id获取订单信息
		orders.clear();
		int totlePage = getTotlePage(" WHERE UserId=" + userId + " AND IsDel="
				+ 0);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_AddOrder WHERE UserId="
							+ userId + " AND IsDel=" + 0 + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				String orderCode = res.getString("OrderCode");
				double payMoney = res.getDouble("Pay_money");
				String insertDate = res.getString("InsertDate");
				String payDate = res.getString("PaymentDate");
				int payType = res.getInt("PaymentMethod");
				String alipayAccount = res.getString("AlipayAccount");
				int orderState = res.getInt("OrderState");
				ChargeOrder order = new ChargeOrder();
				order.setTotlePage(totlePage);
				order.setPage(page);
				order.setOrderCode(orderCode);
				order.setChargeMoney(payMoney);
				order.setInsertDate(insertDate);
				order.setPayDate(payDate);
				order.setPayType(payType);
				order.setAlipayAccount(alipayAccount);
				order.setOrderState(orderState);
				orders.add(order);
			}
			return orders;
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
	public List<ChargeOrder> findOrderByUidAndState(int userId, int state,
			int page) {
		orders.clear();
		int totlePage = getTotlePage(" WHERE UserId=" + userId
				+ " AND OrderState=" + state + " AND IsDel=" + 0);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_AddOrder WHERE UserId="
							+ userId + " AND OrderState=" + state
							+ " AND IsDel=" + 0 + ") A " + "WHERE RowNumber > "
							+ 15 * (page - 1));
			while (res.next()) {
				int id=res.getInt("Id");
				String orderCode = res.getString("OrderCode");
				double payMoney = res.getDouble("Pay_money");
				String insertDate = res.getString("InsertDate");
				String payDate = res.getString("PaymentDate");
				int payType = res.getInt("PaymentMethod");
				String alipayAccount = res.getString("AlipayAccount");
				ChargeOrder order = new ChargeOrder();
				order.setTotlePage(totlePage);
				order.setPage(page);
				order.setId(id);
				order.setOrderCode(orderCode);
				order.setChargeMoney(payMoney);
				order.setInsertDate(insertDate);
				order.setPayDate(payDate);
				order.setPayType(payType);
				order.setAlipayAccount(alipayAccount);
				order.setOrderState(state);
				orders.add(order);
			}
			return orders;
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
	public int addOrder(ChargeOrder order) {
		// 添加订单
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("INSERT INTO JCP_AddOrder(UserId,"
					+ "OrderCode,Pay_money,InsertDate,PaymentDate,"
					+ "IsDel,IP,PaymentMethod,AlipayAccount,BankChannel,"
					+ "OrderState) VALUES("
					+ order.getUserId()
					+ ",'"
					+ order.getOrderCode()
					+ "','"
					+ order.getChargeMoney()
					+ "','"
					+ order.getInsertDate()
					+ "','"
					+ order.getPayDate()
					+ "',"
					+ order.getIsDel()
					+ ",'"
					+ order.getIp()
					+ "',"
					+ order.getPayType()
					+ ",'"
					+ order.getAlipayAccount()
					+ "','"
					+ order.getBankChannel()
					+ "'," + order.getOrderState() + ")");
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
	public int deleteOrder(int id) {
		// 删除充值订单信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_AddOrder SET IsDel=" + 1
					+ " WHERE Id=" + id);
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
	public int updatePayState(String orderCode, int state, String payDate,
			String ip,String prePayDate,int type) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_AddOrder SET OrderState="
					+ state + ",PaymentDate='" + payDate + "',IP='" + ip
					+ "',PaymentMethod="+type+",InsertDate='"+prePayDate+"' WHERE OrderCode='" + orderCode + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ChargeOrder findOrderByOrderCode(String orderCode) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_AddOrder WHERE OrderCode='"+orderCode+"' ORDER BY Id DESC");
			while (res.next()) {
				int id = res.getInt(1);
				int userId = res.getInt(2);
				int orderState = res.getInt(12);
				ChargeOrder order=new ChargeOrder();
				order.setUserId(userId);
				order.setOrderState(orderState);
				order.setId(id);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
