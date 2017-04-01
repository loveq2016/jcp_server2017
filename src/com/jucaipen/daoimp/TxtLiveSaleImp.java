package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TxtLiveSaleDao;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.utils.JdbcUtil;

public class TxtLiveSaleImp implements TxtLiveSaleDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<TxtLiveSale> sales = new ArrayList<TxtLiveSale>();
	
	/**
	 * @return 查询文字直播销售总页数
	 */
	public int findTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TxtLiveSale "
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
	public TxtLiveSale findSaleByUidAndTxtId(int uId, int txtId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLiveSale WHERE FK_UserId="
							+ uId + " AND FK_TxtLiveId=" + txtId);
			while (res.next()) {
				int id = res.getInt(1);
				String insertDate = res.getString(6);
				TxtLiveSale sale = new TxtLiveSale();
				sale.setId(id);
				sale.setInsertDate(insertDate);
				return sale;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<TxtLiveSale> findSaleByUserId(int userId,int page) {
		sales.clear();
		int totlePage=findTotlePage(" WHERE FK_UserId="+userId);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TxtLiveSale"
							+ " WHERE FK_UserId=" + userId + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String insertDate = res.getString("InsertDate");
				int teacherId = res.getInt("FK_TearchId");
				int txtId=res.getInt("FK_TxtLiveId");
				TxtLiveSale sale = new TxtLiveSale();
				sale.setTotlePage(totlePage);
				sale.setPage(page);
				sale.setId(id);
				sale.setFk_txtId(txtId);
				sale.setInsertDate(insertDate);
				sale.setTeacherId(teacherId);
				sales.add(sale);
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int addSale(TxtLiveSale sale) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_TxtLiveSale"
							+ "(FK_UserId,FK_TearchId,OrderCode,FK_TxtLiveId,InsertDate) VALUES ("
							+ sale.getUserId() + "," + sale.getTeacherId()
							+ ",'" + sale.getOrderCode() + "',"
							+ sale.getFk_txtId() + ",'" + sale.getInsertDate()
							+ "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<TxtLiveSale> findSaleByUserIdAndTiD(int userId, int teacherId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLiveSale WHERE FK_UserId="
							+ userId + " AND FK_TearchId=" + teacherId);
			while (res.next()) {
				int id = res.getInt(1);
				String insertDate = res.getString(6);
				String endDate=res.getString(7);
				String startDate=res.getString(8);
				TxtLiveSale sale = new TxtLiveSale();
				sale.setId(id);
				sale.setEndDate(endDate);
				sale.setStartDate(startDate);
				sale.setInsertDate(insertDate);
				sales.add(sale);
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
