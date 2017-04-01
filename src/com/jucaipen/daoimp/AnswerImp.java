package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.AnswerDao;
import com.jucaipen.model.Answer;
import com.jucaipen.utils.JdbcUtil;

public class AnswerImp implements AnswerDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;
	private List<Answer> answers = new ArrayList<Answer>();
	
	

	/**
	 * @return 查询回答总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Answer "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
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


	public int insertAnswer(Answer answer) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO JCP_Answer"
							+ "(AnswerBody,FK_TearchId,InsertDate,FreeBody,FK_AskId,Grade)"
							+ "VALUES ('" + answer.getAnswerBody() + "',"
							+ answer.getTeacherId() + ",'"
							+ answer.getAnswerDate() + "','"
							+ answer.getPrivateBody() + "'," + answer.getAskId()
							+ "," + answer.getGrade() + ")");
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public Answer findAnswerById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Answer WHERE Id="
					+ id);
			answers = getAnswer(res,1,1);
			if (answers.size() > 0) {
				return answers.get(0);
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

	public List<Answer> findAnswerByLast(int count) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "+count+" * FROM JCP_Answer ORDER BY InsertDate DESC");
			answers = getAnswer(res,1,1);
			return answers;
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
	
	
	public List<Answer> findAllAnswer(int page) {
		int totlePage=findTotlePager("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER ( ORDER BY InsertDate DESC) AS RowNumber,* FROM JCP_Answer"
							+") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			answers = getAnswer(res,page,totlePage);
			return answers;
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

	public List<Answer> findAnswerByTeacherId(int teacherId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Answer WHERE FK_TearchId="
							+ teacherId + " ORDER BY InsertDate DESC");
			answers = getAnswer(res,1,1);
			return answers;
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

	public List<Answer> findAnswerByAskId(int askId) {
		try {
			answers.clear();
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT AnswerBody,FK_TearchId,InsertDate,IsCaiNa,FreeBody FROM JCP_Answer WHERE FK_AskId="
							+ askId + " ORDER BY InsertDate DESC");
			while (res.next()) {
				String answerBody=res.getString(1);
				int teacherId=res.getInt(2);
				String insertDate=res.getString(3);
				int isCatch=res.getInt(4);
				String freeBody=res.getString(5);
				Answer answer=new Answer();
				answer.setAnswerBody(answerBody);
				answer.setAnswerDate(insertDate);
				answer.setTeacherId(teacherId);
				answer.setIsCatch(isCatch);
				answer.setPrivateBody(freeBody);
				answers.add(answer);
			}
			return answers;
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

	public List<Answer> findAnswerByTeacherAndLast(int teacherId, int count) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + count
					+ " * FROM JCP_Answer WHERE FK_TearchId=" + teacherId
					+ " ORDER BY InsertDate DESC");
			answers = getAnswer(res,1,1);
			return answers;
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

	public List<Answer> getAnswer(ResultSet result,int page,int totlePage) {
		answers.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				String answerBody = result.getString("AnswerBody");
				int teacherId = result.getInt("TearcherId");
				String answerDate = result.getString("InsertDate");
				int askId = result.getInt("AskId");
				int scroe = result.getInt("Grade");
				Answer answer = new Answer();
				answer.setPage(page);
				answer.setTotlePage(totlePage);
				answer.setId(id);
				answer.setAnswerBody(answerBody);
				answer.setTeacherId(teacherId);
				answer.setAnswerDate(answerDate);
				answer.setAskId(askId);
				answer.setGrade(scroe);
				answers.add(answer);
			}
			return answers;
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
