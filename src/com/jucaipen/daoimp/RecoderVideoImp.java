package com.jucaipen.daoimp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.RecoderVideoDao;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.utils.JdbcUtil;
public class RecoderVideoImp implements RecoderVideoDao {
	private List<RecoderVideo> recoderVideos = new ArrayList<RecoderVideo>();
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	/**
	 * @return 查询地区信息总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Live_Recorded "
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

	@Override
	public RecoderVideo getLastRecoderVideo(int teacherId) {
		try {
			Connection dbConn = JdbcUtil.connSqlServer();
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT TOP 1 VideoUrl_1,VideoUrl_2,VideoUrl_3,ImageUrl,LiveIdFree,FreePrice,Ext_1,PlayCountSham FROM JCP_Live_Recorded WHERE TeacherId=? AND VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0 ORDER BY EndDate DESC");
			statement.setInt(1, teacherId);
			ResultSet query = statement.executeQuery();
			while (query.next()) {
				String url1 = query.getString(1);
				String url2 = query.getString(2);
				String url3 = query.getString(3);
				String image = query.getString(4);
				int isFree = query.getInt(5);
				int plice = query.getInt(6);
				String ext1 = query.getString(7);
				int xn=query.getInt(8);
				RecoderVideo recoderVideo = new RecoderVideo();
				recoderVideo.setLiveUrl1(url1);
				recoderVideo.setLiveUrl2(url2);
				recoderVideo.setLiveUrl3(url3);
				recoderVideo.setImageUrl(image);
				recoderVideo.setLiveIsFree(isFree);
				recoderVideo.setLivePrice(plice);
				recoderVideo.setXnPlayCount(xn);
				recoderVideo.setExt1(ext1 != null && !ext1.equals("") ? ext1
						: 0 + "");
				return recoderVideo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RecoderVideo> getAllRecoderVideo(int teacherId, int page) {
		try {
			recoderVideos.clear();
			int totlePage = findTotlePager(" 	WHERE TeacherId=" + teacherId+" AND VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0");
			Connection dbConn = JdbcUtil.connSqlServer();
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT TOP 5 Id,Title,LiveIdFree,FreePrice,VideoUrl_1,ImageUrl,PlayCountSham,StartDate,Ext_1 FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate DESC) AS RowNumber,* FROM JCP_Live_Recorded"
							+ " WHERE TeacherId=? AND VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0) A "
							+ "WHERE RowNumber > "
							+ 5 * (page - 1));
			statement.setInt(1, teacherId);
			ResultSet query = statement.executeQuery();
			while (query.next()) {
				int id = query.getInt(1);
				String title = query.getString(2);
				int isFree = query.getInt(3);
				int plice = query.getInt(4);
				String url1 = query.getString(5);
				String image = query.getString(6);
				int xnPlayCount = query.getInt(7);
				String startDate = query.getString(8);
				String ext1 = query.getString(9);

				RecoderVideo recoderVideo = new RecoderVideo();
				recoderVideo.setLiveUrl1(url1);
				recoderVideo.setTotlePage(totlePage);
				recoderVideo.setPage(page);
				recoderVideo.setImageUrl(image);
				recoderVideo.setLiveIsFree(isFree);
				recoderVideo.setLivePrice(plice);
				recoderVideo.setXnPlayCount(xnPlayCount);
				recoderVideo.setTitle(title);
				recoderVideo.setId(id);
				recoderVideo.setExt1(ext1 != null && !ext1.equals("") ? ext1
						: 0 + "");
				recoderVideo.setStartDate(startDate);
				recoderVideos.add(recoderVideo);
			}
			return recoderVideos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RecoderVideo getRecoderVideioById(int id) {
		try {
			Connection dbConn = JdbcUtil.connSqlServer();
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT PlayCount,PlayCountSham FROM JCP_Live_Recorded WHERE Id=? AND VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0");
			statement.setInt(1, id);
			ResultSet query = statement.executeQuery();
			while (query.next()) {
				int playCount = query.getInt(1);
				int xnCount = query.getInt(2);
				RecoderVideo video = new RecoderVideo();
				video.setPlayCount(playCount);
				video.setXnPlayCount(xnCount);
				return video;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateRecoderViderHits(int id, int hits, int xnHits) {
		try {
			Connection dbConn = JdbcUtil.connSqlServer();
			PreparedStatement statement = dbConn
					.prepareStatement("UPDATE JCP_Live_Recorded SET PlayCount=?,PlayCountSham=? WHERE Id=? AND VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0");
			statement.setInt(1, hits);
			statement.setInt(2, xnHits);
			statement.setInt(3, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<RecoderVideo> getHotVideos(int top) {
		try {
			recoderVideos.clear();
			Connection dbConn = JdbcUtil.connSqlServer();
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT TOP "+top+" VideoUrl_1,VideoUrl_2,VideoUrl_3,ImageUrl,LiveIdFree,FreePrice,Ext_1,PlayCountSham FROM JCP_Live_Recorded WHERE VideoUrl_1 IS NOT NULL AND DATALENGTH(VideoUrl_1)>0 ORDER BY PlayCountSham DESC");
			ResultSet query = statement.executeQuery();
			while (query.next()) {
				String url1 = query.getString(1);
				String url2 = query.getString(2);
				String url3 = query.getString(3);
				String image = query.getString(4);
				int isFree = query.getInt(5);
				int plice = query.getInt(6);
				String ext1 = query.getString(7);
				int xn=query.getInt(8);
				RecoderVideo recoderVideo = new RecoderVideo();
				recoderVideo.setLiveUrl1(url1);
				recoderVideo.setLiveUrl2(url2);
				recoderVideo.setLiveUrl3(url3);
				recoderVideo.setImageUrl(image);
				recoderVideo.setLiveIsFree(isFree);
				recoderVideo.setLivePrice(plice);
				recoderVideo.setXnPlayCount(xn);
				recoderVideo.setExt1(ext1 != null && !ext1.equals("") ? ext1
						: 0 + "");
				recoderVideos.add(recoderVideo);
			}
			return recoderVideos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
