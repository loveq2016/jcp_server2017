package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jucaipen.dao.CheckDetailDao;
import com.jucaipen.model.CheckDetail;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *  审核信息
 */
public class CheckDetailImp implements CheckDetailDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	@Override
	public CheckDetail findCheckById(int id) {
		//通过id获取审核信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_ShenHe_Detail WHERE Id="+id);
			while (res.next()) {
				int applyId=res.getInt(2);  //FK_ApplyId
				String reason=res.getString(3); //Cause
				String passDate=res.getString(4); //PassDate
				String auditor=res.getString(5); //Auditor
				int adminUserId=res.getInt(6); //FK_AdminUserId
				int state=res.getInt(7); //State
				CheckDetail detail=new CheckDetail();
				detail.setId(id);
				detail.setApplyId(applyId);
				detail.setReason(reason);
				detail.setPassDate(passDate);
				detail.setAuditor(auditor);
				detail.setFk_AdminUserId(adminUserId);
				detail.setState(state);
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
	public CheckDetail findCheckByApplyId(int applyId) {
		// 通过申请id获取审核信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_ShenHe_Detail WHERE FK_ApplyId="+applyId);
			while (res.next()) {
				int id=res.getInt(1);  //Id
				String reason=res.getString(3); //Cause
				String passDate=res.getString(4); //PassDate
				String auditor=res.getString(5); //Auditor
				int adminUserId=res.getInt(6); //FK_AdminUserId
				int state=res.getInt(7); //State
				CheckDetail detail=new CheckDetail();
				detail.setId(id);
				detail.setApplyId(applyId);
				detail.setReason(reason);
				detail.setPassDate(passDate);
				detail.setAuditor(auditor);
				detail.setFk_AdminUserId(adminUserId);
				detail.setState(state);
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

}
