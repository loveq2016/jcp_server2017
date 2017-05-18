package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.ApkInfoDao;
import com.jucaipen.model.ApkInfo;
import com.jucaipen.utils.JdbcUtil;

public class ApkInfoImp implements ApkInfoDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<ApkInfo> infos = new ArrayList<ApkInfo>();
	private int isSuccess;

	/*
	 * 
	 * 获取最大APK信息的id
	 */
	public int querrtMaxId() {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select max(Id) from versionInfo");
			while (res.next()) {
				int maxId = res.getInt(1);
				return maxId;
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

		return 0;
	}

	/*
	 * 
	 * 获取最新apk版本号
	 */
	public ApkInfo findApkInfoById(int id) {
		ApkInfo apkInfo = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 1 versionCode,apkUrl,length,webUrl FROM versionInfo ORDER BY versionCode DESC ");
			while (res.next()) {
				int versionCode = res.getInt(1);
				String apkPath = res.getString(2);
				String length = res.getString(3);
				String webUrl=res.getString(4);
				apkInfo = new ApkInfo();
				apkInfo.setApkPath(apkPath);
				apkInfo.setLength(length);
				apkInfo.setWebUrl(webUrl);
				apkInfo.setVersionCode(versionCode);
			}
			return apkInfo;
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
	 * 获取全部APK版本信息
	 */
	public List<ApkInfo> findAll() {
		infos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM versionInfo");
			while (res.next()) {
				int id = res.getInt("Id");
				String pkgName = res.getString("pkgName");
				int versionCode = res.getInt("versionCode");
				String versionName = res.getString("versionName");
				String apkUrl = res.getString("apkUrl");
				ApkInfo info = new ApkInfo();
				info.setId(id);
				info.setPkgName(pkgName);
				info.setVersionCode(versionCode);
				info.setVsionName(versionName);
				info.setApkPath(apkUrl);
				infos.add(info);
			}
			return infos;
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
	 * 添加APK版本信息
	 */
	public int insertApkInfo(ApkInfo info) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO versionInfo ( Id ,pkgName,versionCode,versionName,apkUrl,updateDate,isFoce,length,webUrl) VALUES ("
							+ info.getId()
							+ ",'"
							+ info.getPkgName()
							+ "',"
							+ info.getVersionCode()
							+ ",'"
							+ info.getVsionName()
							+ "','"
							+ info.getApkPath()
							+ "','"
							+ info.getUpdateDate()
							+ "',"
							+ info.getIsForce()
							+ ","
							+ info.getLength()+",'"
							+info.getWebUrl()+"')"
							);
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
		return 0;
	}

	/*
	 * 删除APK版本信息
	 */
	public int deleteApkInfo() {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("DELETE  versionInfo WHERE");
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
		return 0;
	}

	@Override
	public ApkInfo findInfoByCode(int code) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  Id FROM versionInfo WHERE versionCode="
							+ code + " ORDER BY Id DESC ");
			while (res.next()) {
				int id = res.getInt(1);
				ApkInfo apkInfo = new ApkInfo();
				apkInfo.setId(id);
				return apkInfo;
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
	public int updateUrl(int code, int type, String url) {
		try {
			int res = 0;
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (type == 1) {
				res = sta.executeUpdate("UPDATE versionInfo SET webUrl='" + url
						+ "' WHERE versionCode=" + code);
			} else {
				res = sta.executeUpdate("UPDATE versionInfo SET apkUrl='" + url
						+ "' WHERE versionCode=" + code);
			}

			return res;
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
