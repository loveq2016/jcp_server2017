package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.NewsClassDao;
import com.jucaipen.model.NewsClass;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class NewsClassImp implements NewsClassDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<NewsClass> nList = new ArrayList<NewsClass>();

	public List<NewsClass> findAllNewsClass() {
		// 获取一级分类所有信息
		nList.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_NewsClass");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String className = res.getString(SqlUtil.NEWONECLASS_BIGNAME);
				String KeyWord = res.getString(SqlUtil.NEWONECLASS_KEYWORD);
				String Description = res.getString(SqlUtil.NEWONECLASS_DESC);
				String LinkUrl = res.getString(SqlUtil.NEWONECLASS_LINKURL);
				int sortId = res.getInt(SqlUtil.NEWONECLASS_SORTID);
				int parentId=res.getInt("ParentId");
				NewsClass nBigClass = new NewsClass(id, className, KeyWord, Description, LinkUrl, sortId);
				nBigClass.setParentId(parentId);
				nList.add(nBigClass);
			}
			return nList;

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

	public List<NewsClass> findNewsClassByPId(int pId) {
		// 根据id 获取分类信息
		nList.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_NewsClass where ParentId="
					+ pId);
			while (res.next()) {
				int id=res.getInt("Id");
				String BigName = res.getString(SqlUtil.NEWONECLASS_BIGNAME);
				String KeyWord = res.getString(SqlUtil.NEWONECLASS_KEYWORD);
				String Description = res.getString(SqlUtil.NEWONECLASS_DESC);
				String LinkUrl = res.getString(SqlUtil.NEWONECLASS_LINKURL);
				int sortId = res.getInt(SqlUtil.NEWONECLASS_SORTID);
				int parentId=res.getInt("ParentId");
				NewsClass nBigClass = new NewsClass(id, BigName, KeyWord,
						Description,LinkUrl, sortId);
				nBigClass.setLinkUrl(LinkUrl);
				nBigClass.setParentId(parentId);
				nList.add(nBigClass);
			}
			return nList;    
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
	public NewsClass findClassById(int id) {
		// 根据id获取新闻分类信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_NewsClass WHERE Id="+id);
			while (res.next()) {
				String BigName = res.getString(SqlUtil.NEWONECLASS_BIGNAME);
				String KeyWord = res.getString(SqlUtil.NEWONECLASS_KEYWORD);
				String Description = res.getString(SqlUtil.NEWONECLASS_DESC);
				String LinkUrl = res.getString(SqlUtil.NEWONECLASS_LINKURL);
				int sortId = res.getInt(SqlUtil.NEWONECLASS_SORTID);
				int parentId=res.getInt("ParentId");
				NewsClass nBigClass = new NewsClass(id, BigName, KeyWord,
						Description,LinkUrl, sortId);
				nBigClass.setLinkUrl(LinkUrl);
				nBigClass.setParentId(parentId);
				return nBigClass;
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
