package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.SysDetailAccountDao;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.utils.JdbcUtil;

public class SysDetailAccountImp implements SysDetailAccountDao {
	private Connection dbConn;
	private Statement sta;

	@Override
	public int addDetails(SysDetailAccount detail) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_SysAccountDateil"
							+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
							+ " VALUES(" + detail.getUserId() + ","
							+ detail.getType() + "," + detail.getRecoderType()
							+ "," + detail.getOrderId() + ","
							+ detail.getPrice() + ",'" + detail.getInsertDate()
							+ "','" + detail.getRemark() + "','"
							+ detail.getIp() + "'," + detail.getIsDel() + ")");
		} catch (SQLException e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int delDetail(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("UPDATE JCP_SysAccountDateil SET IsDel=1 WHERE Id="
							+ id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
