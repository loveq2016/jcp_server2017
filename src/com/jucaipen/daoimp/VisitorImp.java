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

	@Override
	public List<Visitor> getAllVisitor() {
		visitors.clear();
		Connection connMySql = JdbcUtil.connMySql();
		try {
			PreparedStatement statement = connMySql.prepareStatement("SELECT * FROM visitor");
			ResultSet query = statement.executeQuery();
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
		}
		return null;
	}

}
