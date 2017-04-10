package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.MyGiftsDao;
import com.jucaipen.model.MyGifts;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         我的礼品
 */
public class MyGiftsImp implements MyGiftsDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<MyGifts> gifts = new ArrayList<MyGifts>();

	/**
	 * @return 查询礼品记录总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_MyLiPin "
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

	@Override
	public List<MyGifts> findMyGiftBySenderId(int senderId, int page) {
		// 获取我送出的礼品
		// select FK_LiPinId,sum(LiPinNum) from JCP_MyLiPin WHERE
		// FK_SendUserId=6750 GROUP BY FK_LiPinId
		gifts.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT FK_LiPinId,SUM(LiPinNum) from JCP_MyLiPin WHERE FK_SendUserId="
							+ senderId + "  GROUP BY FK_LiPinId");
			while (res.next()) {
				int giftId = res.getInt(1); // FK_LiPinId
				int giftNum = res.getInt(2);
				MyGifts g = new MyGifts();
				g.setGiftId(giftId);
				g.setGiftNum(giftNum);
				gifts.add(g);
			}
			return gifts;
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
	public List<MyGifts> findMyGiftByReceiverId(int receiverId, int page) {
		// 获取我收到的礼品
		gifts.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT FK_LiPinId,SUM(LiPinNum) from JCP_MyLiPin WHERE FK_ReceiveUserId="
							+ receiverId + "  GROUP BY FK_LiPinId");
			while (res.next()) {
				int giftId = res.getInt(1); // FK_LiPinId
				int giftNum = res.getInt(2);
				MyGifts g = new MyGifts();
				g.setReceiverId(receiverId);
				g.setGiftId(giftId);
				g.setGiftNum(giftNum);
				g.setPage(page);
				gifts.add(g);
			}
			return gifts;
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
	public int addGifts(MyGifts gifts) {
		// 添加礼品信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_MyLiPin (FK_SendUserId,FK_ReceiveUserId,FK_LiPinId,InsertDate,SortId,LiPinNum,ReMark,Price) VALUES ("
							+ gifts.getSenderId()
							+ ","
							+ gifts.getReceiverId()
							+ ","
							+ gifts.getGiftId()
							+ ",'"
							+ gifts.getInsertDate()
							+ "',"
							+ gifts.getSortId()
							+ ","
							+ gifts.getGiftNum()
							+ ",'"
							+ gifts.getRemark() + "',"+gifts.getPrice()+")");
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

	@Override
	public List<MyGifts> findMyGiftDetailBySenderId(int senderId, int giftId,
			int page) {
		gifts.clear();
		int totlePage = getTotlePage(" WHERE FK_SendUserId=" + senderId
				+ " AND FK_LiPinId=" + giftId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate DESC) AS RowNumber,* FROM JCP_MyLiPin  WHERE FK_SendUserId="
							+ senderId + " AND FK_LiPinId=" + giftId + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				String insertDate = res.getString("InsertDate");
				int giftNum = res.getInt("LiPinNum");
				String remark = res.getString("ReMark");
				int receiverId = res.getInt("FK_ReceiveUserId");
				MyGifts gift = new MyGifts();
				gift.setPage(page);
				gift.setTotlePage(totlePage);
				gift.setInsertDate(insertDate);
				gift.setGiftNum(giftNum);
				gift.setRemark(remark);
				gift.setReceiverId(receiverId);
				gift.setGiftId(giftId);
				gifts.add(gift);
			}
			return gifts;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<MyGifts> findMyGiftDetailByReceiverId(int receiverId,
			int giftId, int page) {
		gifts.clear();
		int totlePage = getTotlePage(" WHERE FK_ReceiveUserId=" + receiverId
				+ " AND FK_LiPinId=" + giftId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate DESC) AS RowNumber,* FROM JCP_MyLiPin WHERE FK_ReceiveUserId="
							+ receiverId + " AND FK_LiPinId=" + giftId + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				String insertDate = res.getString("InsertDate");
				int giftNum = res.getInt("LiPinNum");
				String remark = res.getString("ReMark");
				int sendId = res.getInt("FK_SendUserId");
				MyGifts gift = new MyGifts();
				gift.setPage(page);
				gift.setTotlePage(totlePage);
				gift.setInsertDate(insertDate);
				gift.setGiftNum(giftNum);
				gift.setRemark(remark);
				gift.setSenderId(sendId);
				gift.setReceiverId(receiverId);
				gift.setGiftId(giftId);
				gifts.add(gift);
			}
			return gifts;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
