package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TxtMsgDao;
import com.jucaipen.model.TxtLiveMsg;
import com.jucaipen.utils.JdbcUtil;

public class TxtMsgImp implements TxtMsgDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<TxtLiveMsg> msgs = new ArrayList<TxtLiveMsg>();

	@Override
	public List<TxtLiveMsg> findLastTxtMsg(int count, int liveId,
			boolean isCheck,int msgType) {
		msgs.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (isCheck) {
				// 审核后的数据  ---普通用户
				res = sta
						.executeQuery("SELECT  * FROM (SELECT TOP "
								+ count
								+ " * from JCP_TxtLive_Msg WHERE Fk_TxtLiveId="
								+ liveId
								+ " AND shenhe>0 AND MsgType="+msgType+" ORDER BY InsertDate DESC) as a ORDER BY InsertDate ASC");
			} else {
				// 管理员
				res = sta
						.executeQuery("SELECT  * FROM (SELECT TOP "
								+ count
								+ " * from JCP_TxtLive_Msg WHERE Fk_TxtLiveId="
								+ liveId
								+ " AND MsgType="+msgType+" ORDER BY InsertDate DESC) as a ORDER BY InsertDate ASC");
			}
			while (res.next()) {
				int id = res.getInt(1); // UserId
				int userId = res.getInt(2); // UserId
				String messBody = res.getString(3); // MessBody
				int shenhe = res.getInt(4); // shenhe
				String insertDate = res.getString(6); // InsertDate
				int receiverId = res.getInt(7); // ReceiverId
				int isSysAdmin = res.getInt(8); // IsSysAdmin
				int isRoomAdmin = res.getInt(9); // IsRoomAdmin
				TxtLiveMsg msg = new TxtLiveMsg();
				msg.setId(id);
				msg.setInsertDate(insertDate);
				msg.setMessBody(messBody);
				msg.setReceiverId(receiverId);
				msg.setUserId(userId);
				msg.setShenhe(shenhe);
				msg.setIsSysAdmin(isSysAdmin);
				msg.setIsRoomAdmin(isRoomAdmin);
				msgs.add(msg);
			}
			return msgs;
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
	public List<TxtLiveMsg> findTxtMsgByMaxId(int maxId, int liveId,
			boolean isCheck,int msgType) {
		msgs.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (isCheck) {
				// 普通用户
				res = sta
						.executeQuery("SELECT * FROM JCP_TxtLive_Msg WHERE Fk_TxtLiveId="
								+ liveId + " AND shenhe>0  AND Id>" + maxId+" AND MsgType="+msgType+" ORDER BY InsertDate ASC");
			} else {
				// 管理员
				res = sta
						.executeQuery("SELECT * FROM JCP_TxtLive_Msg WHERE Fk_TxtLiveId="
								+ liveId + " AND Id>" + maxId+" AND MsgType="+msgType+" ORDER BY InsertDate ASC");
			}
			while (res.next()) {
				int id = res.getInt(1);
				int userId = res.getInt(2);
				String msgBody = res.getString(3);
				int shenhe = res.getInt(4);
				String insertDate = res.getString(6);
				int receiverId = res.getInt(7);
				int isSysAdmin = res.getInt(8);
				int isRoomAdmin = res.getInt(9);
				TxtLiveMsg msg = new TxtLiveMsg();
				msg.setId(id);
				msg.setUserId(userId);
				msg.setReceiverId(receiverId);
				msg.setMessBody(msgBody);
				msg.setShenhe(shenhe);
				msg.setInsertDate(insertDate);
				msg.setIsSysAdmin(isSysAdmin);
				msg.setIsRoomAdmin(isRoomAdmin);
				msgs.add(msg);
			}
			return msgs;

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
	public int addMsg(TxtLiveMsg msg) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			return sta.executeUpdate("INSERT INTO JCP_TxtLive_Msg"
					+ "(UserId,MessBody,shenhe,Fk_TxtLiveId,InsertDate,"
					+ "ReceiverId,IsSysAdmin,IsRoomAdmin,IP,MsgType) VALUES ("
					+ msg.getUserId() + ",'" + msg.getMessBody() + "',"
					+ msg.getShenhe() + "," + msg.getTxtLiveId() + ",'"
					+ msg.getInsertDate() + "'," + msg.getReceiverId() + ","
					+ msg.getIsSysAdmin() + "," + msg.getIsSysAdmin() 
					+",'"+msg.getIp()
					+"',"+msg.getMsgType()+ ")");
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
