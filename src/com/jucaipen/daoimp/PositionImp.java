package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.PositionDao;
import com.jucaipen.model.Position;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *  岗位信息
 */
public class PositionImp implements PositionDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Position> positions=new ArrayList<Position>();

	@Override
	public Position findPositionById(int id) {
		//根据id获取岗位信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Position WHERE Id="+id);
			while (res.next()) {
				String name=res.getString(2);  //PositionName
				String remark=res.getString(3);  //Remark
				int cenId=res.getInt(4);  //FK_CenId
				Position position=new Position();
				position.setName(name);
				position.setRemark(remark);
				position.setFk_CenId(cenId);
				return position;
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
	public List<Position> findAll() {
		//获取所有岗位信息
		positions.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Position");
			while (res.next()) {
				int id=res.getInt(1);
				String name=res.getString(2);  //PositionName
				String remark=res.getString(3);  //Remark
				int cenId=res.getInt(4);  //FK_CenId
				Position position=new Position();
				position.setName(name);
				position.setId(id);
				position.setRemark(remark);
				position.setFk_CenId(cenId);
				positions.add(position);
			}
			return positions;
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
	public List<Position> findPositionByCid(int cId) {
		//根据资格证书id获取相应岗位
		positions.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Position WHERE FK_CenId="+cId);
			while (res.next()) {
					int id=res.getInt(1);
					String name=res.getString(2);  //PositionName
					String remark=res.getString(3);  //Remark
					Position position=new Position();
					position.setName(name);
					position.setId(id);
					position.setRemark(remark);
					position.setFk_CenId(cId);
					positions.add(position);
			}
				return positions;
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
