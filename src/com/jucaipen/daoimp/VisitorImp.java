package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.VisitorDao;
import com.jucaipen.model.Visitor;
import com.jucaipen.utils.JdbcUtil;

public class VisitorImp implements VisitorDao {
	private List<Visitor> visitors=new ArrayList<Visitor>();
	private Connection connMySql;
	private PreparedStatement statement;
	private ResultSet query;

	@Override
	public List<Visitor> getAllVisitor() {
		visitors.clear();
		connMySql = JdbcUtil.connSqlServer();
		try {
			statement = connMySql.prepareStatement("SELECT * FROM visitor");
			query = statement.executeQuery();
			while (query.next()) {
				int id=query.getInt(1);
				int userId=query.getInt(2);
				String ip=query.getString(3);
				int devType=query.getInt(4);
				String header=query.getString(5);
				String insertDate=query.getString(6);
				String param=query.getString(7);
				Visitor visitor=new Visitor();
				visitor.setId(id);
				visitor.setUserId(userId);
				visitor.setIp(ip);
				visitor.setDevType(devType);
				visitor.setHead(header);
				visitor.setInsertDate(insertDate);
				visitor.setParam(param);
				visitors.add(visitor);
			}
			return visitors;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(statement, connMySql, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int addVisitor(Visitor visitor) {
		try {
			connMySql = JdbcUtil.connSqlServer();
			statement = connMySql.prepareStatement("INSERT INTO visitor("
					+ "userId,ip,devType,header,insertDate,param,url,host,hostAddress)"
					+ "VALUES ("+visitor.getUserId()+",'"+visitor.getIp()+"',"+visitor.getDevType()+",'"
					+visitor.getHead()+"','"+visitor.getInsertDate()+"','"+visitor.getParam()+"','"
					+visitor.getUrl()+"','"+visitor.getHost()+"','"+visitor.getHostAddress()+"')");
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(statement, connMySql, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
