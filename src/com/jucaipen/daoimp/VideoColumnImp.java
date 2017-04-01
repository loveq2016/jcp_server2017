package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.VideoColnumnDao;
import com.jucaipen.model.VideoColumn;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         直播栏目
 */
public class VideoColumnImp implements VideoColnumnDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<VideoColumn> columns = new ArrayList<VideoColumn>();

	@Override
	public VideoColumn findVideoColumnById(int id) {
		// 根据id获取直播栏目
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_VideoColumn WHERE Id="
					+ id);
			while (res.next()) {
				String name = res.getString(2); // VideoColumnName
				String keyWord = res.getString(3); // KeyWord
				String desc = res.getString(4); // Description
				int sortId = res.getInt(5); // SortId
				int isDel = res.getInt(6); // IsDel
				VideoColumn column = new VideoColumn();
				column.setName(name);
				column.setKeyWord(keyWord);
				column.setDesc(desc);
				column.setSortId(sortId);
				column.setIsDel(isDel);
				return column;
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
	public List<VideoColumn> findAllColumn() {
		// 获取所有的栏目信息
		columns.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_VideoColumn WHERE IsDel=0");
			while (res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2); // VideoColumnName
				String keyWord = res.getString(3); // KeyWord
				String desc = res.getString(4); // Description
				int sortId = res.getInt(5); // SortId
				int isDel = res.getInt(6); // IsDel
				VideoColumn column = new VideoColumn();
				column.setName(name);
				column.setId(id);
				column.setKeyWord(keyWord);
				column.setDesc(desc);
				column.setSortId(sortId);
				column.setIsDel(isDel);
				columns.add(column);
			}
			return columns;
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
