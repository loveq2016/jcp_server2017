package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TxtLiveDetailsDao;
import com.jucaipen.model.TxtLiveDetails;
import com.jucaipen.utils.JdbcUtil;

public class TxtLiveDetaileImp implements TxtLiveDetailsDao {
	private List<TxtLiveDetails> txtLiveDetails = new ArrayList<TxtLiveDetails>();
	private Connection dbConn;
	private ResultSet res;
	private Statement sta;
	private int isSuccess;

	public int insertTextDetaile(TxtLiveDetails details) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO JCP_TxtLive_Detail(FK_LiveId,FK_CommId,Bodys,Images,LiveType,InsertDate) VALUES("
							+ details.getFk_liveId()
							+ ","
							+ details.getFk_commId()
							+ ",'"
							+ details.getBodys()
							+ "','"
							+ details.getImage()
							+ "',"
							+ details.getLiveType()
							+ ",'"
							+ details.getInsertDate() + "')");
			return isSuccess;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<TxtLiveDetails> findTextDetaileByLiveId(int liveId,int type) {
		try {
			txtLiveDetails.clear();
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,FK_LiveId,Bodys,InsertDate,IsFree,LivePrice FROM JCP_TxtLive_Detail WHERE FK_LiveId="
							+ liveId+" AND LiveType="+type+" ORDER BY InsertDate DESC");
			while (res.next()) {
				int id=res.getInt(1);
				int relate_LiveId=res.getInt(2);
				String bodys=res.getString(3);
				String insertDate=res.getString(4);
				int isFree=res.getInt(5);
				int livePrice=res.getInt(6);
				TxtLiveDetails diDetails=new TxtLiveDetails();
				diDetails.setId(id);
				diDetails.setFk_liveId(relate_LiveId);
				diDetails.setBodys(bodys);
				diDetails.setLivePrice(livePrice);
				diDetails.setIsFree(isFree);
				diDetails.setInsertDate(insertDate);
				txtLiveDetails.add(diDetails);
			}
			return txtLiveDetails;
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
	public List<TxtLiveDetails> findLaseDetaileByLiveId(int liveId, int count,int type) {
		try {
			txtLiveDetails.clear();
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "+count+" Id,FK_LiveId,Bodys,InsertDate,IsFree FROM JCP_TxtLive_Detail WHERE FK_LiveId="
							+ liveId+" AND LiveType="+type+" ORDER BY InsertDate ASC");
			while (res.next()) {
				int id=res.getInt(1);
				int relate_LiveId=res.getInt(2);
				String bodys=res.getString(3);
				String insertDate=res.getString(4);
				int isFree=res.getInt(5);
				TxtLiveDetails diDetails=new TxtLiveDetails();
				diDetails.setId(id);
				diDetails.setFk_liveId(relate_LiveId);
				diDetails.setBodys(bodys);
				diDetails.setIsFree(isFree);
				diDetails.setInsertDate(insertDate);
				txtLiveDetails.add(diDetails);
			}
			return txtLiveDetails;
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
	
	public List<TxtLiveDetails> findPullTextDetaileByLiveId(int liveId,int maxId) {
		try {
			txtLiveDetails.clear();
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,FK_LiveId,Bodys,InsertDate FROM JCP_TxtLive_Detail WHERE FK_LiveId="
							+ liveId+"AND Id>"+maxId+" ORDER BY InsertDate ASC");
			while (res.next()) {
				int id=res.getInt(1);
				int relate_LiveId=res.getInt(2);
				String bodys=res.getString(3);
				String insertDate=res.getString(4);
				TxtLiveDetails diDetails=new TxtLiveDetails();
				diDetails.setId(id);
				diDetails.setFk_liveId(relate_LiveId);
				diDetails.setBodys(bodys);
				diDetails.setInsertDate(insertDate);
				txtLiveDetails.add(diDetails);
			}
			return txtLiveDetails;
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

	public List<TxtLiveDetails> findTextDetaileByTitleId(int commId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Detail WHERE FK_CommId="
							+ commId+"ORDER InsertDate DESC");
			txtLiveDetails = getTexDetaile(res);
			return txtLiveDetails;
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

	public TxtLiveDetails findTextDetaileById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Detail WHERE Id="
							+ id);
			txtLiveDetails = getTexDetaile(res);
			if (txtLiveDetails.size() > 0) {
				return txtLiveDetails.get(0);
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

	public List<TxtLiveDetails> findTextDetaileByType(int type) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Detail WHERE LiveType="
							+ type + " ORDER BY InsertDate DESC");
			txtLiveDetails = getTexDetaile(res);
			return txtLiveDetails;
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

	public List<TxtLiveDetails> findAllTextDetaile() {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TxtLive_Detail ORDER BY InsertDate DESC");
			txtLiveDetails = getTexDetaile(res);
			return txtLiveDetails;
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

	public List<TxtLiveDetails> getTexDetaile(ResultSet result) {
		txtLiveDetails.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				int relateLiveId = result.getInt("FK_LiveId");
				int commId = result.getInt("FK_CommId");
				String bodys = result.getString("Bodys");
				String image = result.getString("Images");
				int liveType = result.getInt("LiveType");
				String insertDate = result.getString("InsertDate");
				TxtLiveDetails txDetails = new TxtLiveDetails();
				txDetails.setId(id);
				txDetails.setFk_liveId(relateLiveId);
				txDetails.setFk_commId(commId);
				txDetails.setBodys(bodys);
				txDetails.setLiveType(liveType);
				txDetails.setImage(image);
				txDetails.setInsertDate(insertDate);
				txtLiveDetails.add(txDetails);
			}
			return txtLiveDetails;
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
