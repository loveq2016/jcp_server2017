package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.GiftsDao;
import com.jucaipen.model.Gifts;
import com.jucaipen.utils.JdbcUtil;
/**
 * @author Administrator
 * 
 *         礼品
 */
public class GiftsImp implements GiftsDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Gifts> gifts = new ArrayList<Gifts>();
	/**
	 * @return 查询礼品总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_LiPin "
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
	public List<Gifts> findAllGift(int page) {
		// 获取所有礼品
		gifts.clear();
		int totlePage = getTotlePage(" WHERE SaleType=1 AND IsDel=1");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id) AS RowNumber,* FROM JCP_LiPin"
							+ " WHERE SaleType=1 AND IsDel=1) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setTotlePage(totlePage);
				gift.setPage(page);
				gift.setImageUrl(image);
				gifts.add(gift);
			}
			return gifts;
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
	public List<Gifts> findGiftByClassId(int classId) {
		// 根据分类获取礼品
		gifts.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LiPin WHERE  ClassId="+classId+" AND SaleType=1 AND IsDel=1");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setTotlePage(1);
				gift.setPage(1);
				gift.setImageUrl(image);
				gifts.add(gift);
			}
			return gifts;
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
	public Gifts findGiftById(int id) {
		// 根据id获取礼品
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_LiPin WHERE Id="+id+" AND SaleType=1 AND IsDel=1");
			while (res.next()) {
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setImageUrl(image);
				return gift;
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
	public List<Gifts> findIsTuijian(int IsTuiJian) {
		gifts.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LiPin WHERE IsTuiJian="+IsTuiJian+" AND  SaleType=1 AND IsDel=1");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setImageUrl(image);
				gifts.add(gift);
			}
			return gifts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Gifts> findIsTuijian(int IsTuiJian, int page) {
		gifts.clear();
		int totlePage = getTotlePage("WHERE IsTuiJian="+IsTuiJian+" AND  SaleType=1 AND IsDel=1");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP 15 * FROM "
					+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id) AS RowNumber,* FROM JCP_LiPin WHERE  IsTuiJian="
					+ IsTuiJian + " AND SaleType=1 AND IsDel=1) A " + "WHERE RowNumber > " + 15
					* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setPage(page);
				gift.setTotlePage(totlePage);
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setImageUrl(image);
				gifts.add(gift);
			}
			return gifts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Gifts> findAllGifts() {
		// 获取所有礼品
		gifts.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_LiPin");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				int price = res.getInt("Price");
				String image = res.getString("ImagesUrl");
				Gifts gift = new Gifts();
				gift.setId(id);
				gift.setTitle(title);
				gift.setPrice(price);
				gift.setImageUrl(image);
				gifts.add(gift);
			}
			return gifts;
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
