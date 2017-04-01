package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.IdeaSaleDao;
import com.jucaipen.model.IdeaSale;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *   购买观点信息
 */
public class IdeaSaleImp implements IdeaSaleDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<IdeaSale> slList=new ArrayList<IdeaSale>();
	
	/**
	 * @return 查询购买观点总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TeacherlogSale "
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
	

	public List<IdeaSale> findAllTxtLiveSale(int page) {
		// 获取所有的销售信息
		int totlePage=getTotlePage("");
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TeacherlogSale"
							+ ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			slList=getIdeaSales(res,totlePage,page);
			return slList;
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

	public List<IdeaSale> findTxtLiveSaleByUid(int userId,int page) {
		//根据用户id获取直播销售信息
		int totlePage=getTotlePage("WHERE FK_UserId="+userId);
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP 15 * FROM "
					+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TeacherlogSale"
					+ " WHERE FK_UserId="+userId+") A " + "WHERE RowNumber > " + 15
					* (page - 1));
			slList=getIdeaSales(res,totlePage,page);
			return slList;
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

	public List<IdeaSale> findTxtLiveSaleByTeacherId(int teacherId,int page) {
		//根据讲师id获取购买信息
		int totlePage=getTotlePage("WHERE FK_TearchId="+teacherId);
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP 15 * FROM "
					+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TeacherlogSale"
					+ " WHERE FK_TearchId="+teacherId+") A " + "WHERE RowNumber > " + 15
					* (page - 1));
			slList=getIdeaSales(res,totlePage,page);
			return slList;
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

	public List<IdeaSale> findTxtLiveSaleByLiveId(int liveId) {
		//根据直播id获取购买信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TeacherlogSale WHERE FK_LogId="+liveId+" ORDER BY InsertDate DESC");
			slList=getIdeaSales(res,0,0);
			return slList;
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

	public IdeaSale findTxtLiveSaleByUiDAndLiveId(int uId, int liveId) {
		// 根据直播id和用户id获取购买信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TeacherlogSale WHERE FK_LogId="+liveId+" AND FK_UserId="+uId);
		    slList=getIdeaSales(res,0,0);
			if(slList.size()>0){
				return slList.get(0);
			}
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

	public IdeaSale findTxtLiveSaleById(int id) {
		//根据id获取购买信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TeacherlogSale WHERE Id="+id+"");
		    slList=getIdeaSales(res,0,0);
			if(slList.size()>0){
				return slList.get(0);
			}
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

	public IdeaSale findTxtLiveSaleByOrderCode(String orderCode) {
		//根据订单号获取购买信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TeacherlogSale WHERE OrderCode="+orderCode+" ");
		    slList=getIdeaSales(res,0,0);
			if(slList.size()>0){
				return slList.get(0);
			}
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

	public List<IdeaSale> findTxtLiveSaleByUserLastCount(int uId, int count) {
		// 获取用户最近几条信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP "+count +" FROM JCP_TeacherlogSale WHERE FK_UserId="+uId+" ORDER BY InsertDate DESC");
		    slList=getIdeaSales(res,0,0);
			return slList;
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
	
	public List<IdeaSale> getIdeaSales(ResultSet result,int totlePage,int page){
		slList.clear();
		try {
			while (result.next()) {
				int id=result.getInt("Id");
				int userId=result.getInt("FK_UserId");
				int teacherId=result.getInt("FK_TearchId");
				String orderCode=result.getString("OrderCode");
				int liveId=result.getInt("FK_LogId");
				String insertDate=result.getString("InsertDate");
				IdeaSale sale=new IdeaSale(id, userId, teacherId, orderCode, liveId, insertDate);
				sale.setTotlePage(totlePage);
				sale.setPage(page);
				slList.add(sale);
			}
			return slList;
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
