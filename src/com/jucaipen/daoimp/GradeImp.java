package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jucaipen.dao.GradeDao;
import com.jucaipen.model.Grade;
import com.jucaipen.utils.JdbcUtil;

public class GradeImp implements GradeDao {
	private Connection dbConn;
	private PreparedStatement statement;
	private ResultSet query;

	@Override
	public Grade findGradByIntegeral(int interal) {
		try {
			dbConn=JdbcUtil.connSqlServer();
			statement = dbConn.prepareStatement("SELECT Grade,GradeFace FROM JCP_Grade WHERE  ?  BETWEEN MinIntegral AND MaxIntegral");
			statement.setInt(1, interal);
			query = statement.executeQuery();
			while (query.next()) {
				int gradeNum = query.getInt(1);
				String gradeFace=query.getString(2);
				Grade grade=new Grade();
				grade.setGrade(gradeNum);
				grade.setGradeFace(gradeFace);
				return grade;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(statement, dbConn, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
