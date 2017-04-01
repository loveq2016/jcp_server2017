package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.VideoClassDao;
import com.jucaipen.model.VideoClass;
import com.jucaipen.utils.JdbcUtil;

public class VideoClassImp implements VideoClassDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<VideoClass>  vcs=new ArrayList<VideoClass>();

	@Override
	public List<VideoClass> findAll() {
		vcs.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_VideoClass");
			while (res.next()) {
				int id=res.getInt(1);  //Id
				String title=res.getString(2);  //TypeTitle
				String keyWord=res.getString(3);  //KeyWord
				String desc=res.getString(4);  //Description
				int sortId=res.getInt(5);  //SortId
				int pId=res.getInt(6);  //ParentId
				VideoClass vc=new VideoClass();
				vc.setId(id);
				vc.setTypeTitle(title);
				vc.setDescript(desc);
				vc.setKeyWord(keyWord);
				vc.setParentId(pId);
				vc.setSortId(sortId);
				vcs.add(vc);
			}
			return vcs;
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
	public VideoClass findClassById(int id) {
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_VideoClass WHERE Id="+id);
			while (res.next()) {
				String title=res.getString(2);  //TypeTitle
				String keyWord=res.getString(3);  //KeyWord
				String desc=res.getString(4);  //Description
				int sortId=res.getInt(5);  //SortId
				int pId=res.getInt(6);  //ParentId
				VideoClass vc=new VideoClass();
				vc.setId(id);
				vc.setTypeTitle(title);
				vc.setDescript(desc);
				vc.setKeyWord(keyWord);
				vc.setParentId(pId);
				vc.setSortId(sortId);
				return vc;
			} }catch (SQLException e) {
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
	public List<VideoClass> findClassByPid(int pId) {
		vcs.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_VideoClass WHERE ParentId="+pId);
			while (res.next()) {
				int id=res.getInt(1);  //Id
				String title=res.getString(2);  //TypeTitle
				String keyWord=res.getString(3);  //KeyWord
				String desc=res.getString(4);  //Description
				int sortId=res.getInt(5);  //SortId
				VideoClass vc=new VideoClass();
				vc.setId(id);
				vc.setTypeTitle(title);
				vc.setDescript(desc);
				vc.setKeyWord(keyWord);
				vc.setParentId(pId);
				vc.setSortId(sortId);
				vcs.add(vc);
			}
			return vcs;
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
