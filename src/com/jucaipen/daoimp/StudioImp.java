package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.StudioDao;
import com.jucaipen.model.Studio;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         演播室
 */
public class StudioImp implements StudioDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Studio> studios = new ArrayList<Studio>();

	/**
	 * @return 查询演播室总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Studio "
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

	@Override
	public List<Studio> findAll(int page) {
		// 获取所有演播室信息
		studios.clear();
		int totlePage = getTotlePage("");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id desc) AS RowNumber,* FROM JCP_Studio"
							+ " AND IsDel=0) A " + "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id=res.getInt("Id");
				String title=res.getString("Title");  //Title
				String keyWord=res.getString("keyword");  //keyword
				String desc=res.getString("Description");  //Description
				String jianJie=res.getString("jianjie");  //jianjie
				String imageUrl=res.getString("ImageUrl");  //ImageUrl
				int goods=res.getInt("Goods");  //Goods
				int renQi=res.getInt("RenQi");  //RenQi
				String insertDate=res.getString("InsertDate");  //InsertDate
				String startDate=res.getString("StratDate");  //StratDate
				String endDate=res.getString("EndDate");  //EndDate
				int originId=res.getInt("VideoOriginalId");  //VideoOriginalId
				int liveId=res.getInt("VideoLiveId");  //VideoLiveId
				int columnId=res.getInt("VideoColumnId");  //VideoColumnId
				String url=res.getString("StudioUrl");  //StudioUrl
				int isEnd=res.getInt("IsEnd");  //IsEnd
				int isDel=res.getInt("IsDel");  //IsDel
				int sortId=res.getInt("SortId");  //SortId
				Studio studio=new Studio();
				studio.setId(id);
				studio.setTotlePage(totlePage);
				studio.setPage(page);
				studio.setTitle(title);
				studio.setKeyWord(keyWord);
				studio.setDesc(desc);
				studio.setJianjie(jianJie);
				studio.setImageUrl(imageUrl);
				studio.setGoods(goods);
				studio.setRenQi(renQi);
				studio.setInsertDate(insertDate);
				studio.setStartDate(startDate);
				studio.setEndDate(endDate);
				studio.setOriginalId(originId);
				studio.setVideoLiveId(liveId);
				studio.setVideoColumnId(columnId);
				studio.setStudioUrl(url);
				studio.setIsEnd(isEnd);
				studio.setIsDel(isDel);
				studio.setSortId(sortId);
				studios.add(studio);
			}
			return studios;
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
	public Studio findStudioById(int id) {
		// 根据id获取演播室信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Studio WHERE Id="+id+" AND IsDel=0");
			while (res.next()) {
				String title=res.getString(2);  //Title
				String keyWord=res.getString(3);  //keyword
				String desc=res.getString(4);  //Description
				String jianJie=res.getString(5);  //jianjie
				String imageUrl=res.getString(6);  //ImageUrl
				int goods=res.getInt(7);  //Goods
				int renQi=res.getInt(8);  //RenQi
				String insertDate=res.getString(9);  //InsertDate
				String startDate=res.getString(10);  //StratDate
				String endDate=res.getString(11);  //EndDate
				int originId=res.getInt(12);  //VideoOriginalId
				int liveId=res.getInt(13);  //VideoLiveId
				int columnId=res.getInt(14);  //VideoColumnId
				String url=res.getString(15);  //StudioUrl
				int isEnd=res.getInt(16);  //IsEnd
				int isDel=res.getInt(17);  //IsDel
				int sortId=res.getInt(18);  //SortId
				Studio studio=new Studio();
				studio.setTitle(title);
				studio.setKeyWord(keyWord);
				studio.setDesc(desc);
				studio.setJianjie(jianJie);
				studio.setImageUrl(imageUrl);
				studio.setGoods(goods);
				studio.setRenQi(renQi);
				studio.setInsertDate(insertDate);
				studio.setStartDate(startDate);
				studio.setEndDate(endDate);
				studio.setOriginalId(originId);
				studio.setVideoLiveId(liveId);
				studio.setVideoColumnId(columnId);
				studio.setStudioUrl(url);
				studio.setIsEnd(isEnd);
				studio.setIsDel(isDel);
				studio.setSortId(sortId);
				return studio;
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
	public List<Studio> findStudioByToday(int week) {
		//获取今日演播信息
		studios.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT Id,Title,RenQi,StratDate,EndDate,"
					+ "StudioUrl,ImageUrl,VideoLiveId FROM JCP_Studio "
					+ "WHERE BeginShowDate LIKE ('%"+week+"%') AND IsDel=0 ORDER BY SortId ASC");
			while (res.next()) {
				int id=res.getInt("Id");
				String title=res.getString("Title");
				int renQi=res.getInt("RenQi");
				String startDate=res.getString("StratDate");
				String endDate=res.getString("EndDate");
				String studioUrl=res.getString("StudioUrl");
				String imageUrl=res.getString("ImageUrl");
				int liveId=res.getInt("VideoLiveId");
				Studio studio=new Studio();
				studio.setId(id);
				studio.setVideoLiveId(liveId);
				studio.setTitle(title);
				studio.setStartDate(startDate);
				studio.setEndDate(endDate);
				studio.setRenQi(renQi);
				studio.setImageUrl(imageUrl);
				studio.setStudioUrl(studioUrl);
				studios.add(studio);
			}
			return studios;
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
	public List<Studio> findStudioFansByToday(int week) {
		studios.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT Id,Title,RenQi,ImageUrl FROM JCP_Studio WHERE BeginShowDate LIKE ('%"+week+"%') AND IsDel=0 ORDER BY RenQi DESC");
			while (res.next()) {
				int id=res.getInt("Id");
				String title=res.getString("Title");
				int renQi=res.getInt("RenQi");
				String imageUrl=res.getString("ImageUrl");
				Studio studio=new Studio();
				studio.setId(id);
				studio.setTitle(title);
				studio.setRenQi(renQi);
				studio.setImageUrl(imageUrl);
				studios.add(studio);
			}
			return studios;
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
	public List<Studio> findStudioByColumnId(int columnId, int page) {
		// 获取栏目下的演播室信息
		studios.clear();
		int totlePage = getTotlePage("WHERE VideoColumnId="+columnId+" AND IsDel=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id desc) AS RowNumber,* FROM JCP_Studio WHERE VideoColumnId="
							+columnId+ " AND IsDel=0) A " + "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id=res.getInt("Id");
				String title=res.getString("Title");  //Title
				String keyWord=res.getString("keyword");  //keyword
				String desc=res.getString("Description");  //Description
				String jianJie=res.getString("jianjie");  //jianjie
				String imageUrl=res.getString("ImageUrl");  //ImageUrl
				int goods=res.getInt("Goods");  //Goods
				int renQi=res.getInt("RenQi");  //RenQi
				String insertDate=res.getString("InsertDate");  //InsertDate
				String startDate=res.getString("StratDate");  //StratDate
				String endDate=res.getString("EndDate");  //EndDate
				int originId=res.getInt("VideoOriginalId");  //VideoOriginalId
				int liveId=res.getInt("VideoLiveId");  //VideoLiveId
				String url=res.getString("StudioUrl");  //StudioUrl
				int isEnd=res.getInt("IsEnd");  //IsEnd
				int isDel=res.getInt("IsDel");  //IsDel
				int sortId=res.getInt("SortId");  //SortId
				Studio studio=new Studio();
				studio.setId(id);
				studio.setTotlePage(totlePage);
				studio.setPage(page);
				studio.setTitle(title);
				studio.setKeyWord(keyWord);
				studio.setDesc(desc);
				studio.setJianjie(jianJie);
				studio.setImageUrl(imageUrl);
				studio.setGoods(goods);
				studio.setRenQi(renQi);
				studio.setInsertDate(insertDate);
				studio.setStartDate(startDate);
				studio.setEndDate(endDate);
				studio.setOriginalId(originId);
				studio.setVideoLiveId(liveId);
				studio.setVideoColumnId(columnId);
				studio.setStudioUrl(url);
				studio.setIsEnd(isEnd);
				studio.setIsDel(isDel);
				studio.setSortId(sortId);
				studios.add(studio);
			}
			return studios;
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
	public int findStudioRenQiById(int id) {
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT RenQi FROM JCP_Studio WHERE Id="+id+" AND IsDel=0");
			res.next();
			return res.getInt(1);
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

	@Override
	public int updateStudioRenQiById(int id,int renQi) {
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Studio SET RenQi="+renQi+" WHERE Id="+id);
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

	


}
