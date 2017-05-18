package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.VideoLiveDao;
import com.jucaipen.model.VideoLive;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

/**
 * @author Administrator
 * 
 *         直播间
 */
public class VideoLiveImp implements VideoLiveDao {
	private Connection dbConn;
	private ResultSet res;
	private Statement sta;
	private List<VideoLive> chatRooms = new ArrayList<VideoLive>();

	/**
	 * @return 查询直播室总页数
	 */
	public int getTotlePage(String condition,double pageSize) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/"+pageSize+") as totlePager from JCP_VideoLive "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

	public List<VideoLive> getRoomList() {
		// 获取所有直播间名称
		chatRooms.clear();
		try {
			dbConn = JdbcUtil.connVideoSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT VideoImg,Id,Title,Videourl FROM JCP_VideoLive");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String roomName = res.getString("Title");
				String roomFace = res.getString("VideoImg");
				String liveUrl = res.getString("Videourl");
				VideoLive room = new VideoLive();
				room.setId(id);
				room.setVideoUrl(liveUrl);
				room.setVideoImage(roomFace);
				room.setTitle(roomName);
				chatRooms.add(room);
			}
			return chatRooms;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public VideoLive getRoomInfo(int id) {
		// 根据id 获取指定直播间信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_VideoLive WHERE Id=" + id);
			while (res.next()) {
				String title = res.getString(2); // Title
				String keyWord = res.getString(3); // Keyword
				String desc = res.getString(4); // Descirption
				int classId = res.getInt(5); // Fk_CalssId
				String videoUrl = res.getString(6); // Videourl
				String videoImage = res.getString(7); // VideoImg
				int teacherId = res.getInt(8); // Fk_TeacherId
				int isEnd = res.getInt(9); // IsEnd
				String startDate = res.getString(10); // StratDate
				String endDate = res.getString(11); // EndDate
				int renQi = res.getInt(12); // RenQi
				int xn=res.getInt(13);
				VideoLive live = new VideoLive();
				live.setClassId(classId);
				live.setDescript(desc);
				live.setEndDate(endDate);
				live.setIsEnd(isEnd);
				live.setXnRenQi(xn);
				live.setKeyWord(keyWord);
				live.setRenQi(renQi);
				live.setStartDate(startDate);
				live.setTeacherId(teacherId);
				live.setTitle(title);
				live.setVideoImage(videoImage);
				live.setVideoUrl(videoUrl);
				return live;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	

	public List<VideoLive> getAllRoom(int page,double pageSize) {
		chatRooms.clear();
		int totlePage = getTotlePage("",pageSize);
		System.out.println("t="+totlePage);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "+(int)pageSize+" * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY IsEnd ASC,RenQi DESC) AS RowNumber,* FROM JCP_VideoLive"
							+ ") A " + "WHERE RowNumber > " +(int)pageSize * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("Keyword"); // Keyword
				String desc = res.getString("Descirption"); // Descirption
				int classId = res.getInt("Fk_CalssId"); // Fk_CalssId
				String videoUrl = res.getString("Videourl"); // Videourl
				String videoImage = res.getString("VideoImg"); // VideoImg
				int teacherId = res.getInt("Fk_TeacherId"); // Fk_TeacherId
				int isEnd = res.getInt("IsEnd"); // IsEnd
				String startDate = res.getString("StratDate"); // StratDate
				String endDate = res.getString("EndDate"); // EndDate
				int renQi = res.getInt("RenQi"); // RenQi
				int xn=res.getInt("XnRenQi");
				VideoLive live = new VideoLive();
				live.setPage(page);
				live.setTotlePage(totlePage);
				live.setId(id);
				live.setTitle(title);
				live.setKeyWord(keyWord);
				live.setDescript(desc);
				live.setClassId(classId);
				live.setVideoUrl(videoUrl);
				live.setVideoImage(videoImage);
				live.setTeacherId(teacherId);
				live.setIsEnd(isEnd);
				live.setStartDate(startDate);
				live.setEndDate(endDate);
				live.setXnRenQi(xn);
				live.setRenQi(renQi);
				chatRooms.add(live);
			}
			return chatRooms;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public VideoLive getLiveUrl(int id) {
		// 根据id 查询直播室视频
		try {
			dbConn = JdbcUtil.connVideoSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Videourl FROM JCP_VideoLive WHERE Id="
							+ id);
			while (res.next()) {
				String liveUrl = res.getString("Videourl");
				VideoLive room = new VideoLive();
				room.setVideoUrl(liveUrl);
				return room;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public VideoLive findLiveBytId(int tId) {
		// 获取讲师下的直播视频信息
		chatRooms.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,Title,Keyword,Descirption,Fk_CalssId,ISNULL(Videourl,'') Videourl,VideoImg,IsEnd,StratDate,EndDate,RenQi,XnRenQi FROM JCP_VideoLive WHERE Fk_TeacherId="
							+ tId+" ORDER BY StratDate DESC");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("Keyword"); // Keyword
				String desc = res.getString("Descirption"); // Descirption
				int classId = res.getInt("Fk_CalssId"); // Fk_CalssId
				String videoUrl = res.getString("Videourl"); // Videourl
				String videoImage = res.getString("VideoImg"); // VideoImg
				int isEnd = res.getInt("IsEnd"); // IsEnd
				String startDate = res.getString("StratDate"); // StratDate
				String endDate = res.getString("EndDate"); // EndDate
				int renQi = res.getInt("RenQi"); // RenQi
				int xn=res.getInt("XnRenQi");
				VideoLive live = new VideoLive();
				live.setId(id);
				live.setTitle(title);
				live.setKeyWord(keyWord);
				live.setDescript(desc);
				live.setClassId(classId);
				live.setVideoUrl(videoUrl);
				live.setXnRenQi(xn);
				live.setVideoImage(videoImage);
				live.setTeacherId(tId);
				live.setIsEnd(isEnd);
				live.setStartDate(startDate);
				live.setEndDate(endDate);
				live.setRenQi(renQi);
				return live;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<VideoLive> findLiveByIsEnd(int isEnd) {
		chatRooms.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_VideoLive WHERE IsEnd="
					+ isEnd);
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("Keyword"); // Keyword
				String desc = res.getString("Descirption"); // Descirption
				int classId = res.getInt("Fk_CalssId"); // Fk_CalssId
				String videoUrl = res.getString("Videourl"); // Videourl
				String videoImage = res.getString("VideoImg"); // VideoImg
				String startDate = res.getString("StratDate"); // StratDate
				String endDate = res.getString("EndDate"); // EndDate
				int renQi = res.getInt("RenQi"); // RenQi
				int teacherId = res.getInt("Fk_TeacherId");
				VideoLive live = new VideoLive();
				live.setId(id);
				live.setTitle(title);
				live.setKeyWord(keyWord);
				live.setDescript(desc);
				live.setClassId(classId);
				live.setVideoUrl(videoUrl);
				live.setVideoImage(videoImage);
				live.setTeacherId(teacherId);
				live.setIsEnd(isEnd);
				live.setStartDate(startDate);
				live.setEndDate(endDate);
				live.setRenQi(renQi);
				chatRooms.add(live);
			}
			return chatRooms;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int updateRenQi(int id, int renQi,int xnRenQi) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_VideoLive SET XnRenQi="+xnRenQi+",RenQi="+renQi+" WHERE Id="+id);
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
	public List<VideoLive> findRecoderLive(int top) {
		chatRooms.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP "+top+" * FROM JCP_VideoLive ORDER BY IsEnd ASC,RenQi DESC"
					);
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("Keyword"); // Keyword
				String desc = res.getString("Descirption"); // Descirption
				int classId = res.getInt("Fk_CalssId"); // Fk_CalssId
				String videoUrl = res.getString("Videourl"); // Videourl
				String videoImage = res.getString("VideoImg"); // VideoImg
				String startDate = res.getString("StratDate"); // StratDate
				String endDate = res.getString("EndDate"); // EndDate
				int renQi = res.getInt("RenQi"); // RenQi
				int teacherId = res.getInt("Fk_TeacherId");
				int isEnd = res.getInt("IsEnd");
				int xn=res.getInt("XnRenQi");
				VideoLive live = new VideoLive();
				live.setId(id);
				live.setTitle(title);
				live.setKeyWord(keyWord);
				live.setDescript(desc);
				live.setClassId(classId);
				live.setVideoUrl(videoUrl);
				live.setXnRenQi(xn);
				live.setVideoImage(videoImage);
				live.setTeacherId(teacherId);
				live.setIsEnd(isEnd);
				live.setStartDate(startDate);
				live.setEndDate(endDate);
				live.setRenQi(renQi);
				chatRooms.add(live);
			}
			return chatRooms;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	
	}

}
