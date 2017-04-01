package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.LinkUrlDao;
import com.jucaipen.model.LinkUrl;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *  连接URL
 */
public class LinkUrlImp implements LinkUrlDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<LinkUrl> urls=new ArrayList<LinkUrl>();

	@Override
	public LinkUrl findUrlById(int id) {
		//根据id获取连接地址
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LinkUrl WHERE Id="+id);
			while (res.next()) {
				String name=res.getString(2);  //LinkName
				String url=res.getString(3); //LinkUrl
				int sortId=res.getInt(4); //SortId
				String remark=res.getString(5); //Remark
				LinkUrl link=new LinkUrl();
				link.setLinkName(name);
				link.setLinkUrl(url);
				link.setSortId(sortId);
				link.setRemark(remark);
				return link;
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
	public List<LinkUrl> findAll() {
		//获取所有连接地址
		urls.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LinkUrl");
			while (res.next()) {
				String name=res.getString(2);  //LinkName
				String url=res.getString(3); //LinkUrl
				int sortId=res.getInt(4); //SortId
				String remark=res.getString(5); //Remark
				LinkUrl link=new LinkUrl();
				link.setLinkName(name);
				link.setLinkUrl(url);
				link.setSortId(sortId);
				link.setRemark(remark);
				urls.add(link);
			}
			return urls;
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
