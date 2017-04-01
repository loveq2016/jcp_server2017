package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.SignDetailDao;
import com.jucaipen.model.SignDetail;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         签到详细信息
 */
public class SignDetailImp implements SignDetailDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<SignDetail> details = new ArrayList<SignDetail>();

	/**
	 * @return 查询签到总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_QianDao_Detail "
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
	public List<SignDetail> findSignDetailByUserId(int userId, int page) {
		// 根据用户id获取签到详细信息
		details.clear();
		int totlePage = getTotlePage("WHERE UserId=" + userId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_QianDao_Detail WHERE UserId="
							+ userId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String insertDate = res.getString("InsertDate");
				String remark = res.getString("Remark");
				SignDetail detail = new SignDetail();
				detail.setTotlePage(totlePage);
				detail.setPage(page);
				detail.setId(id);
				detail.setInsertDate(insertDate);
				detail.setRemark(remark);
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
	public int addSignDetail(SignDetail detail) {
		// 添加签到信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_QianDao_Detail(UserId,InsertDate,Ip,Remark) VALUES("
							+ detail.getUserId()
							+ ",'"
							+ detail.getInsertDate()
							+ "','"
							+ detail.getIp()
							+ "','" + detail.getRemark() + "')");
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
	public List<SignDetail> findMothSignDetailByUserId(int userId) {
		// 根据用户id获取月签到信息
		details.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_QianDao_Detail WHERE UserId="+userId);
			while (res.next()) {
				String insertDate=res.getString(3);
				if(TimeUtils.isMoth(insertDate)){
					int id=res.getInt(1);
					SignDetail detail=new SignDetail();
					detail.setInsertDate(insertDate);
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
