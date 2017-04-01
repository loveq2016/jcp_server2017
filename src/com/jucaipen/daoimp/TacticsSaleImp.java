package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TacticsSaleDao;
import com.jucaipen.model.TacticsSale;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         战法销售信息
 */
public class TacticsSaleImp implements TacticsSaleDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<TacticsSale> sales = new ArrayList<TacticsSale>();

	/**
	 * @return 查询战法销售信息总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TacticsSale "
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
	public TacticsSale findSaleById(int id) {
		// 根据id获取战法信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TacticsSale WHERE Id="
					+ id);
			while (res.next()) {
				int userId = res.getInt("UserId");
				int tacticsId = res.getInt("TacticsId");
				String telPhone = res.getString("MobileNum");
				String insertDate = res.getString("InsetDate");
				String startDate = res.getString("StratDate");
				String endDate = res.getString("EndDate");
				int isStop = res.getInt("IsStop");
				String ip = res.getString("IP");
				String remark = res.getString("Remarks");
				TacticsSale sale = new TacticsSale();
				sale.setUserId(userId);
				sale.setTelPhone(telPhone);
				sale.setInsertDate(insertDate);
				sale.setStartDate(startDate);
				sale.setEndDate(endDate);
				sale.setIsStop(isStop);
				sale.setIp(ip);
				sale.setTacticsId(tacticsId);
				sale.setRemark(remark);
				return sale;
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
	public List<TacticsSale> findSaleByUserId(int uId, int page) {
		// 根据用户id获取战法购买信息
		sales.clear();
		int totlePage = getTotlePage(" WHERE UserId=" + uId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsetDate desc) AS RowNumber,* FROM JCP_TacticsSale WHERE UserId="
							+ uId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int tacticsId = res.getInt("TacticsId");
				String telPhone = res.getString("MobileNum");
				String insertDate = res.getString("InsetDate");
				String startDate = res.getString("StratDate");
				String endDate = res.getString("EndDate");
				int isStop = res.getInt("IsStop");
				String ip = res.getString("IP");
				String remark = res.getString("Remarks");
				TacticsSale sale = new TacticsSale();
				sale.setUserId(uId);
				sale.setId(id);
				sale.setPage(page);
				sale.setTotlePage(totlePage);
				sale.setTelPhone(telPhone);
				sale.setInsertDate(insertDate);
				sale.setStartDate(startDate);
				sale.setEndDate(endDate);
				sale.setIsStop(isStop);
				sale.setIp(ip);
				sale.setTacticsId(tacticsId);
				sale.setRemark(remark);
				sales.add(sale);
			}
			return sales;
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
	public int addSale(TacticsSale sale) {
		// 添加战法销售信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO  JCP_TacticsSale(UserId,"
							+ "TacticsId,MobileNum,InsetDate,StratDate,EndDate,IsStop,IP,Remarks) VALUES("
							+ sale.getUserId() + "," + sale.getTacticsId()
							+ ",'" + sale.getTelPhone() + "','"
							+ sale.getInsertDate() + "','"
							+ sale.getStartDate() + "','" + sale.getEndDate()
							+ "," + sale.getIsStop() + ",'" + sale.getIp()
							+ "','" + sale.getRemark() + "')");
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
	public int delSale(int id) {
		// 删除战法销售信息
		/*
		 * dbConn=JdbcUtil.connSqlServer(); try { sta=dbConn.createStatement();
		 * return sta.executeUpdate("UPDATE JCP_TacticsSale SET "); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
		return 0;
	}

	@Override
	public TacticsSale findTacticsIsSale(int uId, int tacticsId) {
		sales.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,StratDate,EndDate FROM JCP_TacticsSale WHERE UserId="
							+ uId
							+ " AND  TacticsId="
							+ tacticsId
							+ " AND IsStop=0");
			while (res.next()) {
				int id = res.getInt(1);
				String startDate = res.getString(2);
				String endDate = res.getString(3);
				TacticsSale sale = new TacticsSale();
				sale.setId(id);
				sale.setUserId(uId);
				sale.setStartDate(startDate);
				sale.setEndDate(endDate);
				return sale;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TacticsSale> findTacticsIsSale(int tacticsId) {
		sales.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,StratDate,EndDate,UserId FROM JCP_TacticsSale WHERE TacticsId="
							+ tacticsId + " AND IsStop=0");
			while (res.next()) {
				int id = res.getInt(1);
				String startDate = res.getString(2);
				String endDate = res.getString(3);
				int uId=res.getInt(4);
				TacticsSale sale = new TacticsSale();
				sale.setId(id);
				sale.setStartDate(startDate);
				sale.setUserId(uId);
				sale.setEndDate(endDate);
				sales.add(sale);
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
