package com.jucaipen.daoimp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.LiveRecoderSaleDao;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.utils.JdbcUtil;

public class LiveRecoderSaleImp implements LiveRecoderSaleDao {
	private Connection conn;
	private Statement sta;
	private ResultSet res;
	private List<LiveRecoderSale> sales=new ArrayList<LiveRecoderSale>();
	
	
	/**
	 * @return 查询地区信息总页数
	 */
	public int findTotlePager(String condition) {
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_VideoLive_RecordSale "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, conn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public LiveRecoderSale getLiveSaleByUidAndLiveId(int uId, int recoderId,int type) {
		// 获取当前用户购买的单次直播
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_VideoLive_RecordSale WHERE LiveCodeId="
							+ recoderId + " AND UserId=" + uId+" AND RecordType="+type);
			while (res.next()) {
				int id = res.getInt(1); // Id
				int teacherId = res.getInt(3); // TeacherId
				String insertDate = res.getString(4); // InsertDate
				int purchBills = res.getInt(5); // PayJCB
				int userId = res.getInt(6); // UserId
				String remark = res.getString(7); // Remark
				LiveRecoderSale sale = new LiveRecoderSale(id, recoderId,
						teacherId, insertDate, purchBills, userId, remark);
				return sale;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, conn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int addLiveSale(LiveRecoderSale sale) {
		// 添加用户购买单次直播记录
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_VideoLive_RecordSale("
							+ "LiveCodeId,TeacherId,InsertDate,PayJCB,UserId,Remark,RecordType) VALUES("
							+ sale.getLiveRecoderId() + ","
							+ sale.getTeacherId() + ",'" + sale.getPurchDate()
							+ "'," + sale.getPayBills() + ","
							+ sale.getUserId() + ",'" + sale.getRemark() + "',"+sale.getRecoderType()+")");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, conn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<LiveRecoderSale> getMyVideo(int uId, int page, int type) {
		try {
			sales.clear();
			int totle=findTotlePager(" WHERE UserId="+uId+" AND RecordType="+type);
			conn=JdbcUtil.connSqlServer();
			sta=conn.createStatement();
			sta.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate DESC) AS RowNumber,* FROM JCP_VideoLive_RecordSale"
							+ " WHERE UserId="+uId+" AND RecordType="+type+") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id=res.getInt(1);
				int recoderId=res.getInt(2);
				int teacherId=res.getInt(3);
				String insertDate=res.getString(4);
				LiveRecoderSale sale=new LiveRecoderSale();
				sale.setId(id);
				sale.setTeacherId(teacherId);
				sale.setLiveRecoderId(recoderId);
				sale.setPurchDate(insertDate);
				sales.add(sale);
			}
			return sales;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, conn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
