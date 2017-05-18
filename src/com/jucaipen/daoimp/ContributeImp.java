package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.ContributeDao;
import com.jucaipen.model.Contribute;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         贡献榜
 */
public class ContributeImp implements ContributeDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Contribute> contributes = new ArrayList<Contribute>();

	@Override
	public Contribute findContributeById(int id) {
		// 根据id获取贡献榜信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Contribute WHERE Id="
					+ id);
			while (res.next()) {
				int userId = res.getInt(2); // FK_UserId
				int teacherId = res.getInt(3); // FK_TearchId
				int fk_id = res.getInt(4); // FK_Id
				String insertDate = res.getString(5); // InsertDate
				int allJucaiBills = res.getInt(6); // AllJucaibi
				int comeType = res.getInt(7); // ComType
				Contribute contribute = new Contribute();
				contribute.setUserId(userId);
				contribute.setTeacherId(teacherId);
				contribute.setFk_id(fk_id);
				contribute.setInsertDate(insertDate);
				contribute.setAllJucaiBills(allJucaiBills);
				contribute.setComType(comeType);
				return contribute;
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
	public List<Contribute> findContributeByTid(int tId, String type, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contribute> findTopContributeByTid(int tId, String type, int top) {
		// 获取用户的所有贡献信息
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP "+top+" SUM(AllJucaibi) AS A,FK_UserId from JCP_Contribute WHERE FK_TearchId="
							+ tId
							+ "  AND   DATEDIFF("+type+", InsertDate, GETDATE())=0  GROUP BY FK_UserId ORDER BY A DESC");
			while (res.next()) {
				int bills = res.getInt(1);
				int userId = res.getInt(2); // FK_TearchId
				Contribute contribute = new Contribute();
				contribute.setAllJucaiBills(bills);
				contribute.setUserId(userId);
				contributes.add(contribute);
			}
			return contributes;
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
	public List<Contribute> findContributeByTid(int tId,String type) {
		// 获取用户的所有贡献信息
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT SUM(AllJucaibi) AS A,FK_UserId from JCP_Contribute WHERE FK_TearchId="
							+ tId
							+ "  AND   DATEDIFF("+type+", InsertDate, GETDATE())=0  GROUP BY FK_UserId ORDER BY A DESC");
			while (res.next()) {
				int bills = res.getInt(1);
				int userId = res.getInt(2); // FK_TearchId
				Contribute contribute = new Contribute();
				contribute.setAllJucaiBills(bills);
				contribute.setUserId(userId);
				contributes.add(contribute);
			}
			return contributes;
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
	public List<Contribute> findContributeByUidAndTid(int uId, int tId) {
		// 获取用户对某个讲师的贡献
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Contribute WHERE FK_UserId="
							+ uId + " AND FK_TearchId=" + tId);
			while (res.next()) {
				int id = res.getInt(1);
				int fk_id = res.getInt(4); // FK_Id
				String insertDate = res.getString(5); // InsertDate
				int allJucaiBills = res.getInt(6); // AllJucaibi
				int comeType = res.getInt(7); // ComType
				Contribute contribute = new Contribute();
				contribute.setId(id);
				contribute.setUserId(uId);
				contribute.setTeacherId(tId);
				contribute.setFk_id(fk_id);
				contribute.setInsertDate(insertDate);
				contribute.setAllJucaiBills(allJucaiBills);
				contribute.setComType(comeType);
				contributes.add(contribute);
			}
			return contributes;
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
	public List<Contribute> findContributeByUidAndFkId(int uId, int fkId) {
		// 获取用户对关联id 的贡献
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Contribute WHERE FK_UserId="
							+ uId + " AND FK_Id=" + fkId);
			while (res.next()) {
				int id = res.getInt(1);
				int tId = res.getInt(3);
				String insertDate = res.getString(5); // InsertDate
				int allJucaiBills = res.getInt(6); // AllJucaibi
				int comeType = res.getInt(7); // ComType
				Contribute contribute = new Contribute();
				contribute.setId(id);
				contribute.setUserId(uId);
				contribute.setTeacherId(tId);
				contribute.setFk_id(fkId);
				contribute.setInsertDate(insertDate);
				contribute.setAllJucaiBills(allJucaiBills);
				contribute.setComType(comeType);
				contributes.add(contribute);
			}
			return contributes;
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
	public List<Contribute> findAllContribute() {
		// 获取所有贡献
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Contribute");
			while (res.next()) {
				int id = res.getInt(1);
				int uId = res.getInt(2);
				int tId = res.getInt(3);
				int fkId = res.getInt(4);
				String insertDate = res.getString(5); // InsertDate
				int allJucaiBills = res.getInt(6); // AllJucaibi
				int comeType = res.getInt(7); // ComType
				Contribute contribute = new Contribute();
				contribute.setId(id);
				contribute.setUserId(uId);
				contribute.setTeacherId(tId);
				contribute.setFk_id(fkId);
				contribute.setInsertDate(insertDate);
				contribute.setAllJucaiBills(allJucaiBills);
				contribute.setComType(comeType);
				contributes.add(contribute);
			}
			return contributes;
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
	public int addContribute(Contribute contribute) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_Contribute"
							+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES ("
							+ contribute.getUserId() + ","
							+ contribute.getTeacherId() + ","
							+ contribute.getFk_id() + ",'"
							+ contribute.getInsertDate() + "',"
							+ contribute.getAllJucaiBills() + ","
							+ contribute.getComType() + ")");
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
	public List<Contribute> findContributeGroupByTid(String type) {
		// 获取用户的所有贡献信息
		contributes.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT SUM(AllJucaibi) AS A,FK_TearchId from JCP_Contribute WHERE "
					+ " DATEDIFF("+type+", InsertDate, GETDATE())=0  GROUP BY FK_TearchId ORDER BY A DESC");
			while (res.next()) {
				int bills = res.getInt(1);
				int teacherId = res.getInt(2); // FK_TearchId
				Contribute contribute = new Contribute();
				contribute.setAllJucaiBills(bills);
				contribute.setTeacherId(teacherId);
				contributes.add(contribute);
			}
			return contributes;
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
	public int findAllContributeByTid(int teacherId) {
		// 获取用户的所有贡献信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT SUM(AllJucaibi) AS A from JCP_Contribute WHERE FK_TearchId="
							+ teacherId);
			while (res.next()) {
				return res.getInt(1);
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


}
