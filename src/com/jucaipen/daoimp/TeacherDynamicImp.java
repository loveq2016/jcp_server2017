package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TeacherDynamicDao;
import com.jucaipen.model.TeacherDynamic;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 * 
 *         ½²Ê¦¶¯Ì¬
 */
public class TeacherDynamicImp implements TeacherDynamicDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;
	private List<TeacherDynamic> dynamics = new ArrayList<TeacherDynamic>();

	public int insertDynamic(TeacherDynamic dynamic) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO JCP_TxtLive_Dynamic (FK_Id,DynamicType,InsertDate,FK_TearchId) VALUES ("
							+ dynamic.getRelateId()
							+ ","
							+ dynamic.getType()
							+ ",'"
							+ dynamic.getInsertDate() + "',"+dynamic.getTeacherId()+")");
			return isSuccess;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<TeacherDynamic> findAllDynamic() {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Dynamic ORDER BY InsertDate DESC");
			dynamics = getDynamic(res);
			return dynamics;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<TeacherDynamic> findDynamicByType(int type) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Dynamic WHERE DynamicType="
							+ type + " ORDER BY InsertDate DESC");
			dynamics = getDynamic(res);
			return dynamics;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public TeacherDynamic findDynamicById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TxtLive_Dynamic WHERE Id="
					+ id);
			dynamics = getDynamic(res);
			if (dynamics.size() > 0) {
				return dynamics.get(0);
			}
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<TeacherDynamic> getDynamic(ResultSet result) {
		dynamics.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				int relateId = result.getInt("FK_Id");
				int relateType = result.getInt("DynamicType");
				String insertDate = result.getString("InsertDate");
				int teacherId=result.getInt("FK_TearchId");
				TeacherDynamic dynamic = new TeacherDynamic(id, relateId,
						relateType, insertDate,teacherId);
				dynamics.add(dynamic);
			}
			return dynamics;
		} catch (Exception e) {
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
