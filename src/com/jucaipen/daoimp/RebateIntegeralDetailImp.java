package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.RebateIntegeralDetailDao;
import com.jucaipen.model.RebateIntegeralDetail;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.TimeUtils;

public class RebateIntegeralDetailImp implements RebateIntegeralDetailDao {

	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<RebateIntegeralDetail> details = new ArrayList<RebateIntegeralDetail>();

	/**
	 * @return 查询返利积分总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_RebateIntegral_Detail "
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
	public RebateIntegeralDetail findRebateIntegeralById(int id) {
		// 根据id获取返利积分信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_RebateIntegral_Detail WHERE Id="
							+ id);
			while (res.next()) {
				int userId = res.getInt(2); // FK_UserId
				int integralNum = res.getInt(3); // IntegralNum
				String insertDate = res.getString(4); // InsertDate
				String remark = res.getString(5); // ReMark
				int type = res.getInt(6); // Type
				int fromId = res.getInt(7); // FK_FromId
				RebateIntegeralDetail detail = new RebateIntegeralDetail();
				detail.setUserId(userId);
				detail.setType(type);
				detail.setFromId(fromId);
				detail.setInsertDate(insertDate);
				detail.setRemark(remark);
				detail.setIntegralNum(integralNum);
				return detail;
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
	public List<RebateIntegeralDetail> findRebateIntegeralByUserId(int userId,
			int page) {
		// 根据用户id获取返利积分信息
		details.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + userId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_RebateIntegral_Detail WHERE FK_UserId="
							+ userId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int integralNum = res.getInt("IntegralNum"); // IntegralNum
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("ReMark"); // ReMark
				int type = res.getInt("Type"); // Type
				int fromId = res.getInt("FK_FromId"); // FK_FromId
				RebateIntegeralDetail detail = new RebateIntegeralDetail();
				detail.setUserId(userId);
				detail.setType(type);
				detail.setId(id);
				detail.setTotlePage(totlePage);
				detail.setPage(page);
				detail.setFromId(fromId);
				detail.setInsertDate(insertDate);
				detail.setRemark(remark);
				detail.setIntegralNum(integralNum);
				details.add(detail);
			}
			return details;
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
	public int addRebateIntegeral(RebateIntegeralDetail inDetail) {
		// 添加返利积分信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_RebateIntegral_Detail(FK_UserId,IntegralNum,InsertDate,ReMark,Type,FK_FromId) VALUES("
							+ inDetail.getUserId()
							+ ","
							+ inDetail.getIntegralNum()
							+ ",'"
							+ inDetail.getInsertDate()
							+ "','"
							+ inDetail.getRemark()
							+ "',"
							+ inDetail.getType()
							+ "," + inDetail.getFromId() + ")");
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
	public List<RebateIntegeralDetail> findRebateIntegeralByUserId(int userId) {
		// 获取用户下的返利信息
		details.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT Id,InsertDate FROM JCP_RebateIntegral_Detail WHERE FK_UserId="+userId);
			while (res.next()) {
             String insertDate=res.getString(2);
             if(TimeUtils.isToday(insertDate)){
            	 int id=res.getInt(1);
            	 RebateIntegeralDetail detail=new RebateIntegeralDetail();
            	 detail.setId(id);
            	 details.add(detail);
             }
			}
			return details;
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
