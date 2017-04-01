package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.CertificationDao;
import com.jucaipen.model.Certification;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         证书信息
 */
public class CertificationImp implements CertificationDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Certification> certifications = new ArrayList<Certification>();

	@Override
	public List<Certification> findAllCertification() {
		// 获取所有证书信息
		certifications.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Certification");
			while (res.next()) {
				int id = res.getInt(1); // Id
				String name = res.getString(2); // TypeName
				String remark = res.getString(3); // Remark
				Certification certification = new Certification();
				certification.setId(id);
				certification.setTypeName(name);
				certification.setRemark(remark);
				certifications.add(certification);
			}
			return certifications;
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
	public Certification findCertificationById(int id) {
		// 根据id获取证书信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Certification WHERE Id="
					+ id);
			while (res.next()) {
				String name = res.getString(2); // TypeName
				String remark = res.getString(3); // Remark
				Certification certification = new Certification();
				certification.setId(id);
				certification.setTypeName(name);
				certification.setRemark(remark);
				return certification;
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
