package com.jucaipen.daoimp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.LiveRecoderSaleDao;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.utils.JdbcUtil;

public class LiveRecoderSaleImp implements LiveRecoderSaleDao {
	private Connection conn;
	private Statement sta;
	private ResultSet res;

	@Override
	public LiveRecoderSale getLiveSaleByUidAndLiveId(int uId, int recoderId) {
		// 获取当前用户购买的单次直播
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_VideoLive_RecordSale WHERE LiveCodeId="
							+ recoderId + " AND UserId=" + uId);
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
							+ "LiveCodeId,TeacherId,InsertDate,PayJCB,UserId,Remark) VALUES("
							+ sale.getLiveRecoderId() + ","
							+ sale.getTeacherId() + ",'" + sale.getPurchDate()
							+ "'," + sale.getPayBills() + ","
							+ sale.getUserId() + ",'" + sale.getRemark() + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
