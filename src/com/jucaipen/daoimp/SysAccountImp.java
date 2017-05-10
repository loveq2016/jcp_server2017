package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.SysAccountDao;
import com.jucaipen.model.SysAccount;
import com.jucaipen.utils.JdbcUtil;

public class SysAccountImp implements SysAccountDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	@Override
	public int updatePurchInfo( int sysAccount, int childAccount,
			int userAccount) {
		// 更新充值
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_SysAccount SET SysAccount="
					+ sysAccount + ",SysChildAccount=" + childAccount
					+ ",UserAccount=" + userAccount);
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
	public SysAccount findAccountInfo() {
		// 获取总账户信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_SysAccount");
			while (res.next()) {
				int sysAccount = res.getInt(1);
				int childAccount = res.getInt(2);
				int userAccount = res.getInt(3);
				int giftAccount = res.getInt(4);
				int txtLiveAccount = res.getInt(5);
				int payLogAccount = res.getInt(6);
				int payAskAccount = res.getInt(7);
				int openGurdianAccount = res.getInt(8);
				int tacticsAccount = res.getInt(9);
				int payVideoAccount = res.getInt(10);
				int paySpecialAccount = res.getInt(11);
				int teacherRebateAccount = res.getInt(12);
				int sysRebateAccount = res.getInt(13);
				int sysDobateAccount = res.getInt(14);
				int daShangAccount = res.getInt(15);
				SysAccount account = new SysAccount();
				account.setSysAccount(sysAccount);
				account.setUserAccount(userAccount);
				account.setSysChildAccount(childAccount);
				account.setDaShangAccount(daShangAccount);
				account.setGiftAccount(giftAccount);
				account.setOpenGurdianAccount(openGurdianAccount);
				account.setPayAskAccount(payAskAccount);
				account.setPayLogAccount(payLogAccount);
				account.setTxtLiveAccount(txtLiveAccount);
				account.setTeacherRebateAccount(teacherRebateAccount);
				account.setTactivsAccount(tacticsAccount);
				account.setPayVideoAccount(payVideoAccount);
				account.setPaySpecialAccount(paySpecialAccount);
				account.setSysRebateAccount(sysRebateAccount);
				account.setSysDobateAccount(sysDobateAccount);
				return account;
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
	public int updateGurdianInfo(int sysAccount, int childAccount,
			int userAccount, int gurdianAccount) {
		return 0;
	}

	@Override
	public int updateDaShangInfo(int child, int user, int daShang) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_SysAccount SET DaShangAccount="
					+ daShang + ",SysChildAccount=" + child
					+ ",UserAccount=" + user);
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
	public int updateGiftInfo(int child, int user, int gift) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_SysAccount SET GiftAccount="
					+ gift + ",SysChildAccount=" + child
					+ ",UserAccount=" + user);
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
	public int updateTeacherRebate(int teacherAccount, int rebateAccount) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_SysAccount SET TeacherAccount="
					+ teacherAccount + ",SysRebateAccount=" + rebateAccount);
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

}
