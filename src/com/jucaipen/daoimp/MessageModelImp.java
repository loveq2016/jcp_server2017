package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.MessageModelDao;
import com.jucaipen.model.MessageModel;
import com.jucaipen.utils.JdbcUtil;

public class MessageModelImp implements MessageModelDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<MessageModel> models=new ArrayList<MessageModel>();
	

	public List<MessageModel> querryAllModel() {
		//获取所有模版信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Mess_Template");
			models=getMessageModel(res);
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


	public MessageModel findModelById(int id) {
		//根据id获取模版信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Mess_Template WHERE Id="+id);
			models=getMessageModel(res);
			if(models.size()>0){
				return models.get(0);
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
	
	private List<MessageModel> getMessageModel(ResultSet result) {
		//获取模版信息
		models.clear();
		try {
			while (result.next()) {
				int id=result.getInt("Id");
				int modelType=result.getInt("TemplateType");
				String title=result.getString("Title");
				String content=result.getString("TemplateContent");
				MessageModel model=new MessageModel();
				model.setId(id);
				model.setModelType(modelType);
				model.setTitle(title);
				model.setModelContent(content);
				models.add(model);
			}
			return models;
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
