package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.LiveRecoderDao;
import com.jucaipen.model.LiveRecoder;
import com.jucaipen.utils.JdbcUtil;

public class LiveRecoderImp implements LiveRecoderDao {
	private Connection conn;
	private Statement sta;
	private ResultSet res;
	@Override
	public LiveRecoder getRecoderByRect() {
		//获取最近进行的直播
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 1 * FROM JCP_VideoLive_Record ORDER BY LiveStartDate DESC");
			while (res.next()) {
				int id = res.getInt(1);
				int liveId = res.getInt(2);
				String liveNo = res.getString(3);
				int teacherId = res.getInt(4);
				String startDate = res.getString(5);
				int liveState = res.getInt(6);
				String endDate = res.getString(7);
				String remark = res.getString(8);
				LiveRecoder recoder = new LiveRecoder(id, liveId, teacherId,
						liveNo, startDate, liveState, endDate, remark);
				return recoder;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LiveRecoder getRecoderByLiveId(int liveId) {
		//根据直播id获取直播记录信息
		try {
			conn = JdbcUtil.connSqlServer();
			sta = conn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 1 * FROM JCP_VideoLive_Record WHERE VideoLiveId="+liveId+" ORDER BY LiveStartDate DESC");
			while (res.next()) {
				int id = res.getInt(1);
				String liveNo = res.getString(3);
				int teacherId = res.getInt(4);
				String startDate = res.getString(5);
				int liveState = res.getInt(6);
				String endDate = res.getString(7);
				String remark = res.getString(8);
				LiveRecoder recoder = new LiveRecoder(id, liveId, teacherId,
						liveNo, startDate, liveState, endDate, remark);
				return recoder;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
