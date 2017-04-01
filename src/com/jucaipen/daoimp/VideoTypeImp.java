package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.VideoTypeDao;
import com.jucaipen.model.VideoType;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class VideoTypeImp implements VideoTypeDao {
	private Connection dbConn;
	private ResultSet res;
	private java.sql.Statement sta;
	private List<VideoType> fts;

	public List<VideoType> findAll() {
		fts = new ArrayList<VideoType>();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select Id,Title  from JCP_VideoType");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String typeName = res.getString(SqlUtil.VIDEOCLASS_CLASSNAME);
				VideoType vt = new VideoType(id, typeName);
				fts.add(vt);
			}
			return fts;
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

	public VideoType findTypeById(int id) {
		VideoType ft = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_VideoType");
			fts = getFinalType(res);
			if (fts != null && fts.size() > 0) {
				ft = fts.get(0);
			}
			return ft;
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

	public List<VideoType> getFinalType(ResultSet result) {
		fts = new ArrayList<VideoType>();
		try {
			while (result.next()) {
				int id = result.getInt(SqlUtil.VIDEOCLASS_ID);
				String name = result.getString(SqlUtil.VIDEOCLASS_CLASSNAME);
				String keyWord = res.getString(SqlUtil.VIDEOCLASS_KEYWORD);
				String descript = res.getString(SqlUtil.VIDEOCLASS_DESC);
				int sortId = res.getInt(SqlUtil.VIDEOCLASS_SORTID);
				VideoType ft = new VideoType(id, name);
				ft.setDescript(descript);
				ft.setKeyWord(keyWord);
				ft.setSortId(sortId);
				fts.add(ft);
			}
			return fts;
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

	public List<VideoType> findTypeByIsDelete(int isDelete) {
		return null;
	}
	
}
