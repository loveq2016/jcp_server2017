package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.MarkerDao;
import com.jucaipen.model.Marker;
import com.jucaipen.utils.JdbcUtil;

public class MarkerImp implements MarkerDao {
	private Connection dbConn;
	private ResultSet res;
	private Statement sta;
	private List<Marker> markers = new ArrayList<Marker>();

	/**
	 * @return 查询打赏记录总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_MarkerWord_Dateil "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
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
	public Marker findMarkerById(int id) {
		// 根据id获取打赏信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_MarkerWord_Dateil WHERE Id="
							+ id);
			while (res.next()) {
				int type = res.getInt(2); // MarkerType
				int userId = res.getInt(3); // FK_UserId
				int logId = res.getInt(4); // FK_LogId
				int markCount = res.getInt(5); // MaekerCount
				String insertDate = res.getString(6);// InsertDate
				Marker marker = new Marker();
				marker.setType(type);
				marker.setUserId(userId);
				marker.setIdeaId(logId);
				marker.setMarkerCount(markCount);
				marker.setInsertDate(insertDate);
				return marker;
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

	@Override
	public List<Marker> findMarkerByUserId(int uId, int page) {
		// 根据用户id获取打赏信息
		markers.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + uId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_MarkerWord_Dateil WHERE FK_UserId="
							+ uId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int type = res.getInt("MarkerType"); // MarkerType
				int logId = res.getInt("FK_LogId"); // FK_LogId
				int markCount = res.getInt("MaekerCount"); // MaekerCount
				String insertDate = res.getString("InsertDate");// InsertDate
				Marker marker = new Marker();
				marker.setType(type);
				marker.setId(id);
				marker.setTotlePage(totlePage);
				marker.setPage(page);
				marker.setUserId(uId);
				marker.setIdeaId(logId);
				marker.setMarkerCount(markCount);
				marker.setInsertDate(insertDate);
				markers.add(marker);
			}
			return markers;
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
	public List<Marker> findMarkerByUserIdAndType(int type, int userId, int page) {
		// 根据用户获取分类打赏信息
		markers.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + userId
				+ " AND MarkerType" + type);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_MarkerWord_Dateil WHERE FK_UserId="
							+ userId + "AND MarkerType=" + type + " ) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int logId = res.getInt("FK_LogId"); // FK_LogId
				int markCount = res.getInt("MaekerCount"); // MaekerCount
				String insertDate = res.getString("InsertDate");// InsertDate
				Marker marker = new Marker();
				marker.setType(type);
				marker.setId(id);
				marker.setTotlePage(totlePage);
				marker.setPage(page);
				marker.setUserId(userId);
				marker.setIdeaId(logId);
				marker.setMarkerCount(markCount);
				marker.setInsertDate(insertDate);
				markers.add(marker);
			}
			return markers;
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
	public List<Marker> findTopMarkerByLogId(int logId, int top) {
		// 根据日志id获取最近几条打赏信息
		markers.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + top
					+ " * FROM JCP_MarkerWord_Dateil WHERE FK_LogId=" + logId);
			while (res.next()) {
				int id = res.getInt("Id");
				int userId = res.getInt("FK_UserId");
				int type = res.getInt("MarkerType");
				int markCount = res.getInt("MaekerCount"); // MaekerCount
				String insertDate = res.getString("InsertDate");// InsertDate
				Marker marker = new Marker();
				marker.setType(type);
				marker.setId(id);
				marker.setUserId(userId);
				marker.setIdeaId(logId);
				marker.setMarkerCount(markCount);
				marker.setInsertDate(insertDate);
				markers.add(marker);
			}
			return markers;
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
	public int addMarker(Marker marker) {
		// 添加打赏信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_MarkerWord_Dateil(MarkerType,FK_UserId,FK_LogId,MaekerCount,InsertDate,IP) VALUES("
							+ marker.getType()
							+ ","
							+ marker.getUserId()
							+ ","
							+ marker.getIdeaId()
							+ ","
							+ marker.getMarkerCount()
							+ ",'"
							+ marker.getInsertDate()
							+ "','"
							+ marker.getIp()
							+ "')");
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
	public List<Marker> findMarkerByTeacherId(int tId,int type) {
		//获取讲师打赏的信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT FK_UserId,MaekerCount FROM JCP_MarkerWord_Dateil WHERE FK_LogId="+tId+" AND MarkerType="+type);
			while (res.next()) {
				
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

	@Override
	public List<Marker> findAll() {
		//获取所有打赏信息
		markers.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT FK_UserId,MaekerCount,FK_LogId,MarkerType FROM JCP_MarkerWord_Dateil");
		    while (res.next()) {
				int userId=res.getInt(1);
				int markerCount=res.getInt(2);
				int fkId=res.getInt(3);
				int markType=res.getInt(4);
				Marker marker=new Marker();
				marker.setType(markType);
				marker.setUserId(userId);
				marker.setMarkerCount(markerCount);
				marker.setIdeaId(fkId);
				markers.add(marker);
			}
		    return markers;
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
