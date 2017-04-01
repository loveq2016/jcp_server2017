package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.FavoritesDao;
import com.jucaipen.model.Favorites;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class FavoritesImp implements FavoritesDao {
	private Connection dbConn;
	private ResultSet res;
	private Statement sta;
	private List<Favorites> favorites;
	private int isSuccess;

	/**
	 * @return 查询收藏总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Favorites "
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

	/*
	 * 收藏信息
	 */
	public int insertFavorites(Favorites favorites) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("INSERT INTO JCP_Favorites "
					+ "(FK_Id,Type,InsertDate,FK_UserId) VALUES ("
					+ favorites.getFk_Id() + "," + favorites.getType() + ",'"
					+ favorites.getDate() + "'," + favorites.getuId() + ")");
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/*
	 * 取消收藏信息
	 */
	public int cancelFavorites(int uId, int nId, int type) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("delete from JCP_Favorites where FK_UserId="
							+ uId + " and FK_Id=" + nId + " AND Type=" + type);
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/*
	 * 
	 * 查询是否收藏
	 */
	public Favorites findFavouritesByUidAndNid(int uId, int nId, int type) {
		Favorites newsFavorites = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select Id from JCP_Favorites where FK_UserId="
							+ uId + " and FK_Id=" + nId + " AND Type=" + type);
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				newsFavorites = new Favorites();
				newsFavorites.setId(id);
			}
			return newsFavorites;
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
	public List<Favorites> findFavourateByUidAndType(int uId, int type, int page) {
		int totlePager = findTotlePager("Where FK_UserId=" + uId + " AND Type="
				+ type);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC,InsertDate DESC) AS RowNumber,* FROM JCP_Favorites"
							+ " where FK_UserId=" + uId + " AND Type=" + type
							+ ") A " + "WHERE RowNumber > " + 15 * (page - 1));
			favorites = getFavorites(res, page, totlePager);
			return favorites;
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

	/*
	 * 查询该用户收藏的所有新闻资讯信息
	 */
	public List<Favorites> findFavorites(int uId, int pager) {
		int totlePager = findTotlePager("Where FK_UserId=" + uId);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC,InsertDate DESC) AS RowNumber,* FROM JCP_Favorites"
							+ " where FK_UserId=" + uId + ") A "
							+ "WHERE RowNumber > " + 15 * (pager - 1));
			favorites = getFavorites(res, pager, totlePager);
			return favorites;
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

	/**
	 * @param result
	 * @return 获取收藏的新闻信息
	 */
	public List<Favorites> getFavorites(ResultSet result, int pager,
			int totlePager) {
		favorites = new ArrayList<Favorites>();
		try {
			while (result.next()) {
				int id = result.getInt(SqlUtil.NEWS_ID);
				int uId = result.getInt(SqlUtil.NEWSCOMM_UID);
				int nId = result.getInt(SqlUtil.FAVORITES_NID);
				String insertDate = result
						.getString(SqlUtil.EQUITYFAVORITES_DATE);
				Favorites nf = new Favorites();
				nf.setTotlePager(totlePager);
				nf.setPager(pager);
				nf.setId(id);
				nf.setuId(uId);
				nf.setFk_Id(nId);
				nf.setDate(insertDate);
				favorites.add(nf);
			}
			return favorites;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return favorites;

	}

}
