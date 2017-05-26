package com.jucaipen.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.jucaipen.utils.JdbcUtil;

public class JsonTest {
	
	public static void main(String[] args) throws SQLException {
			Connection connection = JdbcUtil.connSqlServer();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO JCP_Grade(GradeName,Grade,MinIntegral,MaxIntegral,SortId,Remark) "
					+ "VALUES (?,?,?,?,?,?)");
			statement.setString(1,3001+"¼¶");
			//39,381,390,39,GETDATE(),'ÐÇÐÇ'
			statement.setInt(2, 3001);  //  40
			statement.setInt(3, 3001*10-9);  // 391   400-9
			statement.setInt(4, 3001*10);  //  400
			statement.setInt(5, 3001);
			statement.setString(6, "×êÊ¯");
			statement.execute();
		
	}

}
