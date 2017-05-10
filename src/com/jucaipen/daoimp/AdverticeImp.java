package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.AdverticeDao;
import com.jucaipen.model.Advertive;
import com.jucaipen.utils.JdbcUtil;

public class AdverticeImp implements AdverticeDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Advertive> advertives = new ArrayList<Advertive>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accumulate.dao.AdverticeDao#findAll()
	 */
	public List<Advertive> findAll() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accumulate.dao.AdverticeDao#findAdByPid(int)
	 */
	public List<Advertive> findAdByPid(int pId) {
		advertives.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Slide where FK_ClassId="
					+ pId+" ORDER BY SortId ASC");
			while (res.next()) {
				int id = res.getInt("Id");
				int pageId = res.getInt("FK_ClassId");
				String title = res.getString("Title");
				String LinkUrl = res.getString("LinkUrl");
				String ImageUrl = res.getString("ImageUrl");
				int PxId = res.getInt("SortId");
				Advertive advertive = new Advertive(id, pageId, title, LinkUrl,
						ImageUrl, PxId);
				advertives.add(advertive);
			}
			return advertives;
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
	public int updateState(int classId, String urls,String state) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Slide SET LinkUrl='"+state+"' WHERE FK_ClassId="+classId+" AND ImageUrl='"+urls+"'");
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

}
