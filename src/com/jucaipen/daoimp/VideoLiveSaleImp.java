package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.VideoLiveSaleDao;
import com.jucaipen.model.VideoLiveSale;
import com.jucaipen.utils.JdbcUtil;

public class VideoLiveSaleImp implements VideoLiveSaleDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<VideoLiveSale> sales = new ArrayList<VideoLiveSale>();

	@Override
	public VideoLiveSale findSaleByUidAndLiveId(int uId, int liveId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_VideoLiveSale WHERE FK_UserId="
							+ uId + " AND FK_VideoLiveId=" + liveId);
			while (res.next()) {
				int id = res.getInt(1);
				String insertDate = res.getString(6);
				String startDate=res.getString(7);
				String endDate=res.getString(8);
				int isStop=res.getInt(9);
				VideoLiveSale sale = new VideoLiveSale();
				sale.setId(id);
				sale.setInsertDate(insertDate);
				sale.setStartDate(startDate);
				sale.setEndDate(endDate);
				sale.setIsStop(isStop);
				return sale;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VideoLiveSale> findSaleByUserId(int userId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_VideoLiveSale WHERE FK_UserId="
							+ userId);
			while (res.next()) {
				int id = res.getInt(1);
				String insertDate = res.getString(6);
				int teacherId = res.getInt(3);
				VideoLiveSale sale = new VideoLiveSale();
				sale.setId(id);
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
	public int addSale(VideoLiveSale sale) {
		sales.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
