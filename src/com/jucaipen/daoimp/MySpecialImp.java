package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.MySpecialDao;
import com.jucaipen.model.MySpecial;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         我的专辑信息
 */
public class MySpecialImp implements MySpecialDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<MySpecial> mySpecials = new ArrayList<MySpecial>();

	/**
	 * @return 查询我的专辑总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_MySpecial "
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
	public MySpecial findSpecialById(int id) {
		// 根据id获取专辑信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_MySpecial WHERE Id=" + id+" AND IsDel=0");
			while (res.next()) {
				int userId = res.getInt(2); // FK_UserId
				int specialId = res.getInt(3); // FK_SpecialId
				String insertDate = res.getString(4); // InsertDate
				String remark = res.getString(5); // Remark
				int isStop = res.getInt(7); // IsStop
				String startDate = res.getString(8); // StartDate
				String endDate = res.getString(9); // EndDate
				MySpecial mySpecial = new MySpecial();
				mySpecial.setUserId(userId);
				mySpecial.setSpecialId(specialId);
				mySpecial.setInsertDate(insertDate);
				mySpecial.setRemark(remark);
				mySpecial.setIsStop(isStop);
				mySpecial.setStartDate(startDate);
				mySpecial.setEndDate(endDate);
				return mySpecial;
			}
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
	public MySpecial getIsMySpecial(int uId, int sId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_MySpecial WHERE FK_UserId="
							+ uId
							+ " AND FK_SpecialId="
							+ sId
							+ " AND IsStop=0 AND IsDel=0");
			while (res.next()) {
				String insertDate = res.getString(4); // InsertDate
				String remark = res.getString(5); // Remark
				int isStop = res.getInt(7); // IsStop
				String startDate = res.getString(8); // StartDate
				String endDate = res.getString(9); // EndDate
				MySpecial mySpecial = new MySpecial();
				mySpecial.setInsertDate(insertDate);
				mySpecial.setRemark(remark);
				mySpecial.setIsStop(isStop);
				mySpecial.setStartDate(startDate);
				mySpecial.setEndDate(endDate);
				return mySpecial;
			}
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
	public List<MySpecial> findSpecialByUid(int userId, int page) {
		// 根据用户id获取有用的专辑信息
		mySpecials.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + userId+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_MySpecial WHERE FK_UserId="
							+ userId + " AND IsDel=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int specialId = res.getInt("FK_SpecialId"); // FK_SpecialId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Remark"); // Remark
				int isStop = res.getInt("IsStop"); // IsStop
				String startDate = res.getString("StartDate"); // StartDate
				String endDate = res.getString("EndDate"); // EndDate
				MySpecial mySpecial = new MySpecial();
				mySpecial.setId(id);
				mySpecial.setTotlePage(totlePage);
				mySpecial.setPage(page);
				mySpecial.setUserId(userId);
				mySpecial.setSpecialId(specialId);
				mySpecial.setInsertDate(insertDate);
				mySpecial.setRemark(remark);
				mySpecial.setIsStop(isStop);
				mySpecial.setStartDate(startDate);
				mySpecial.setEndDate(endDate);
				mySpecials.add(mySpecial);
			}
			return mySpecials;
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
	public int addSpecial(MySpecial special) {
		// 添加我的专辑信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_MySpecial (FK_UserId,FK_SpecialId,InsertDate,Remark,IsDel,IsStop,StartDate,EndDate) VALUES ("
							+ special.getUserId()
							+ ","
							+ special.getSpecialId()
							+ ",'"
							+ special.getInsertDate()
							+ "','"
							+ special.getRemark()
							+ "',"
							+ special.getIsDel()
							+ ","
							+ special.getIsStop()
							+ ",'"
							+ special.getStartDate()
							+ "','"
							+ special.getEndDate() + "')");
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
	public int removeSpecial(int id) {
		// 删除专辑信息
		return 0;
	}

	@Override
	public int getSpecialSallNum(int specialId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT  COUNT(*) FROM JCP_MySpecial WHERE FK_SpecialId="+specialId);
			while (res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
