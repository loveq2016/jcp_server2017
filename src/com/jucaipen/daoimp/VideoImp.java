package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.VideoDao;
import com.jucaipen.model.Video;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class VideoImp implements VideoDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Video> videos = new ArrayList<Video>();

	/**
	 * @return 查询视频总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/20.0) as totlePager from JCP_Video "
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

	public List<Video> findAll(int page) {
		// 获取所有视频信息
		videos.clear();
		int totlePage = getTotlePage("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Id,Title,ImagesUrl,Description,VideoPageUrl,FK_ClassId,FK_Pecial,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video"
							+ ") A " + "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String pageUrl = res.getString("VideoPageUrl");
				int classId = res.getInt("FK_ClassId");
				int pecialId = res.getInt("FK_Pecial");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setTotlePage(totlePage);
				video.setPage(page);
				video.setDescript(desc);
				video.setClassId(classId);
				video.setXnHitCount(xnHits);
				video.setVideoDate(videoDate);
				video.setPecialId(pecialId);
				video.setHtmlUrl(pageUrl);
				video.setImages(Images);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Video> findVideoByClassId(String classId, int page) {
		// 根据分类获取视频列表 FK_Pecial 所属专辑
		int totlePage = getTotlePage(" WHERE FK_ClassId IN (" + classId + ")");
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,Description,IsMySite,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoUrl,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId IN ("
							+ classId
							+ ")  ) A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				int isMySiteVideo = res.getInt("IsMySite");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int pecialId = res.getInt("FK_Pecial");
				int hits = res.getInt("PlayCount");
				int videoType = res.getInt("VideoType");
				String VideoUrl = res.getString("VideoUrl");
				String VideoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setClassId(cId);
				video.setVideoDate(VideoDate);
				video.setVideoUrl(VideoUrl);
				video.setVideoType(videoType);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				video.setPecialId(pecialId);
				video.setHtmlUrl(pageUrl);
				video.setTotlePage(totlePage);
				video.setPage(page);
				video.setIsMySiteVideo(isMySiteVideo);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByClassId(int classId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT FK_ClassId,Id,Title,ImagesUrl,Description,IsMySite,VideoUrl,PlayCount,VideoDate,FK_Pecial,VideoType FROM JCP_Video WHERE FK_ClassId="
							+ classId);
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				int isMySiteVideo = res.getInt("IsMySite");
				String videoUrl = res.getString("VideoUrl");
				int cId = res.getInt("FK_ClassId");
				int playCount = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setClassId(cId);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setHitCount(playCount);
				video.setVideoDate(videoDate);
				video.setVideoUrl(videoUrl);
				video.setIsMySiteVideo(isMySiteVideo);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTeacherId(int teacherId, int page) {
		int totlePage = getTotlePage("WHERE TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Id,Title,ImagesUrl,Description,InsertDate,PlayCount,VideoType,FK_Pecial,VideoPageUrl,FK_ClassId,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE TearcherId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String insertDate = res.getString("InsertDate");
				int hits = res.getInt("PlayCount");
				int videoType = res.getInt("VideoType");
				int specialId = res.getInt("FK_Pecial");
				String pageUrl = res.getString("VideoPageUrl");
				int classId = res.getInt("FK_ClassId");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setHitCount(hits);
				video.setClassId(classId);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setInsertDate(insertDate);
				video.setTotlePage(totlePage);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setDescript(desc);
				video.setVideoType(videoType);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByType(int typeId, int page) {
		int totlePage = getTotlePage("WHERE FK_TypeId=" + typeId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Id,Title,ImagesUrl,Description FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_TypeId="
							+ typeId
							+ ") A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setTotlePage(totlePage);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTeacherIdAndType(int teacherId, int type,
			int page) {
		int totlePage = getTotlePage("WHERE FK_TypeId=" + type
				+ " AND TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Id,Title,ImagesUrl,Description,VideoUrl FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_TypeId="
							+ type
							+ " AND TearcherId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String videoUrl = res.getString("VideoUrl");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setTotlePage(totlePage);
				video.setVideoUrl(videoUrl);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTeacherIdAndClassId(int teacherId,
			String classId, int page) {
		if (classId.length() <= 0) {
			return null;
		}
		int totlePage = getTotlePage("WHERE FK_ClassId  IN (" + classId
				+ ") AND TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,Description,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoDate,VideoUrl,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId IN ("
							+ classId
							+ ") AND TearcherId="
							+ teacherId
							+ ") A " + "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				String videoUrl=res.getString("VideoUrl");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setVideoDate(videoDate);
				video.setHitCount(hits);
				video.setVideoUrl(videoUrl);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setTotlePage(totlePage);
				video.setClassId(cId);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTypeAndClassId(int type, String classId,
			int page) {
		int totlePage = getTotlePage("WHERE FK_ClassId IN (" + classId
				+ ") AND FK_TypeId=" + type);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoDate,PlayXNCount,VideoUrl FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId IN ("
							+ classId
							+ ") AND FK_TypeId="
							+ type
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				String videoUrl=res.getString("VideoUrl");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setVideoUrl(videoUrl);
				video.setVideoDate(videoDate);
				video.setTotlePage(totlePage);
				video.setHtmlUrl(pageUrl);
				video.setClassId(cId);
				video.setXnHitCount(xnHits);
				video.setHitCount(hits);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTypeAndClassIdAndTeacherId(int type,
			String classId, int teacherId, int page) {
		int totlePage = getTotlePage("WHERE FK_ClassId IN (" + classId
				+ ") AND FK_TypeId=" + type + " AND TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,VideoPageUrl,PlayCount,FK_Pecial,VideoType,VideoDate,PlayXNCount,VideoUrl FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId IN ("
							+ classId
							+ ") AND FK_TypeId="
							+ type
							+ " AND TearcherId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int specialId = res.getInt("FK_Pecial");
				int xnHits = res.getInt("PlayXNCount");
				String videoUrl=res.getString("VideoUrl");
				Video video = new Video(id, title);
				video.setVideoUrl(videoUrl);
				video.setImages(Images);
				video.setHtmlUrl(pageUrl);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				video.setVideoDate(videoDate);
				video.setPecialId(specialId);
				video.setVideoType(videoType);
				video.setTotlePage(totlePage);
				video.setClassId(cId);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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

	public Video findVideoById(int id) {
		// 根据id 获取视频详细信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select PlayXNCount,Id,Title,KeyWords,PlayCount,InsertDate,Description,ImagesUrl,VideoType,VideoPageUrl,FK_Pecial,FK_ClassId,ShiFuMoney,JiDuPrice,NianPrice,VideoUrl from JCP_Video where Id="
							+ id);
			while (res.next()) {
				int vId = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String keyWord = res.getString(SqlUtil.VIDEO_KEYWORD);
				String insertDate = res.getString(SqlUtil.VIDEO_INSERTDATE);
				int hitCount = res.getInt(SqlUtil.VIDEO_HITCOUNT);
				String Description = res.getString(SqlUtil.VIDEO_DESC);
				String imageUrl = res.getString("ImagesUrl");
				String pageUrl = res.getString("VideoPageUrl");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int classId = res.getInt("FK_ClassId");
				int monthBills = res.getInt("ShiFuMoney");
				int quartBills = res.getInt("JiDuPrice");
				int yearBills = res.getInt("NianPrice");
				int xnHits = res.getInt("PlayXNCount");
				String videoUrl=res.getString("VideoUrl");
				Video video = new Video(vId, title);
				video.setKeyWords(keyWord);
				video.setHtmlUrl(pageUrl);
				video.setVideoUrl(videoUrl);
				video.setClassId(classId);
				video.setPecialId(specialId);
				video.setVideoType(videoType);
				video.setImages(imageUrl);
				video.setInsertDate(insertDate);
				video.setHitCount(hitCount);
				video.setMothPrice(monthBills);
				video.setXnHitCount(xnHits);
				video.setQuartPrice(quartBills);
				video.setYearPrice(yearBills);
				video.setDescript(Description);
				return video;
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

	public Video findVideoRecouresById(int id) {
		// 根据id 获取视频资源信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select VideoUrl,VideoType,FK_Pecial,PlayCount,PlayXNCount,VideoPageUrl from JCP_Video where Id="
							+ id);

			while (res.next()) {
				String videoUrl = res.getString(SqlUtil.VIDEO_URL);
				int videoType = res.getInt("VideoType");
				int specialId = res.getInt("FK_Pecial");
				int hits = res.getInt("PlayCount");
				int xnHits = res.getInt("PlayXNCount");
				String htmlPage = res.getString("VideoPageUrl");
				Video video = new Video();
				video.setVideoUrl(videoUrl);
				video.setVideoType(videoType);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(htmlPage);
				video.setPecialId(specialId);
				video.setHitCount(hits);
				return video;
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

	public List<Video> findVideoByIsIndex(int isIndex) {
		// 获取首页视频
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsIndex="
					+ isIndex + " order by InsertDate desc");
			while (res.next()) {
				int vId = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String images = res.getString(SqlUtil.VIDEO_IMAGES);
				Video video = new Video(vId, title);
				video.setImages(images);
				videos.add(video);
			}
			return videos;
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

	public List<Video> findVideoBySpecialId(int specialId) {
		videos.clear();
		// 获取专辑下的视频
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where FK_Pecial="
					+ specialId + " ORDER BY VideoDate DESC");
			while (res.next()) {
				int vId = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String images = res.getString(SqlUtil.VIDEO_IMAGES);
				int xnHits = res.getInt("PlayXNCount");
				String videoUrl = res.getString("VideoUrl");
				String pageUrl = res.getString("VideoPageUrl");
				int classId = res.getInt("FK_ClassId");
				Video video = new Video(vId, title);
				video.setImages(images);
				video.setClassId(classId);
				video.setXnHitCount(xnHits);
				video.setVideoUrl(videoUrl);
				video.setPecialId(specialId);
				video.setHtmlUrl(pageUrl);
				videos.add(video);
			}
			return videos;
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

	public List<Video> findVideoByIsReComm(int isRecomm) {
		videos.clear();
		// 获取推荐视频
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsTuijian="
					+ isRecomm);
			while (res.next()) {
				int vId = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String images = res.getString(SqlUtil.VIDEO_IMAGES);
				Video video = new Video(vId, title);
				video.setImages(images);
				videos.add(video);
			}
			return videos;
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

	public List<Video> findVideoByIsTop(int isTop) {
		// 查询置顶新闻
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsTop="
					+ isTop);
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				Video video = new Video(id, title);
				video.setDescript(descript);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
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

	public List<Video> findVideoByIsMySite(int isMySite) {
		// 查询本站视频
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsMySite="
					+ isMySite);
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				Video video = new Video(id, title);
				video.setDescript(descript);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<Video> findVideoByIndexId(int top, int classId) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select Top "
							+ top
							+ " Id,Title,ImagesUrl,VideoUrl,Description from JCP_Video where FK_ClassId="
							+ classId + " ORDER BY VideoDate DESC");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String videoUrl = res.getString(SqlUtil.VIDEO_URL);
				String desc = res.getString(SqlUtil.VIDEO_DESC);
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setVideoUrl(videoUrl);
				video.setDescript(desc);
				videos.add(video);
			}
			return videos;
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

	public List<Video> findVideoByIsRecommId(int isRecomm, int classId) {
		// 查询推荐下的分类信息
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsTuijian="
					+ isRecomm + " FK_ClassId=" + classId
					+ " ORDER BY VideoDate DESC");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setDescript(descript);
				video.setXnHitCount(xnHits);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Video> findVideoByIsTopId(int isTop, int classId) {
		// 查询置顶的分类信息
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video WHERE IsTop="
					+ isTop + " AND  FK_ClassId=" + classId
					+ " ORDER BY VideoDate DESC");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setXnHitCount(xnHits);
				video.setDescript(descript);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Video> findVideoByMySitId(int isMySit, int classId) {
		// 查询分类下的本站视频
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Video where IsMySite="
					+ isMySit + " FK_ClassId=" + classId);
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				Video video = new Video(id, title);
				video.setDescript(descript);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 根据分类id获取前几天视频信息
	 * 
	 * 新版 Id,Title,Description,ImagesThumb,VideoUrl 添加 HitCount CommCount 点赞数
	 */
	public List<Video> findVideoByClassIdLast(int count, int classId) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select TOP "
							+ count
							+ " FK_Pecial,FK_ClassId,Id,Title,Description,ImagesUrl,PlayCount,VideoDate,VideoUrl,VideoType,PlayXNCount,VideoPageUrl from JCP_Video"
							+ " WHERE FK_ClassId=" + classId
							);

			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				String images = res.getString("ImagesUrl");
				int playCount = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				String VideoUrl = res.getString("VideoUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int xnHits = res.getInt("PlayXNCount");
				String pageUrl = res.getString("VideoPageUrl");
				Video video = new Video(id, title);
				video.setDescript(descript);
				video.setImages(images);
				video.setVideoType(videoType);
				video.setVideoUrl(VideoUrl);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setHitCount(playCount);
				video.setVideoDate(videoDate);
				video.setPecialId(specialId);
				video.setClassId(cId);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
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
	public List<Video> findVideoByLast(int count) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select TOP "
							+ count
							+ " FK_Pecial,FK_ClassId,Id,Title,Description,ImagesUrl,PlayCount,VideoDate,VideoPageUrl,VideoUrl,PlayXNCount from JCP_Video"
							+ " WHERE IsIndex=1 AND IsTuiJian=1 ORDER BY InsertDate DESC");

			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				String images = res.getString("ImagesUrl");
				int playCount = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				String videoPageUrl = res.getString("VideoPageUrl");
				String VideoUrl=res.getString("VideoUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setDescript(descript);
				video.setImages(images);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(videoPageUrl);
				video.setHitCount(playCount);
				video.setVideoUrl(VideoUrl);
				video.setVideoDate(videoDate);
				video.setPecialId(specialId);
				video.setClassId(cId);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
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
	public List<Video> findLastVideoByCommId(int commId, int count) {
		videos.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " Id,ImagesUrl,Title,PlayXNCount,VideoDate,VideoType,VideoPageUrl,FK_Pecial,FK_ClassId,Description FROM JCP_Video WHERE ColumnId="
							+ commId + " ORDER BY VideoDate DESC");
			while (res.next()) {
				int id = res.getInt(1);
				String imageUrl = res.getString(2);
				String title = res.getString(3);
				int xnHits = res.getInt(4);
				String videoDate = res.getString(5);
				int videoType = res.getInt(6);
				String pageUrl = res.getString(7);
				int specialId = res.getInt(8);
				int classId = res.getInt(9);
				String desc = res.getString(10);
				Video video = new Video();
				video.setId(id);
				video.setTitle(title);
				video.setPecialId(specialId);
				video.setImages(imageUrl);
				video.setHtmlUrl(pageUrl);
				video.setClassId(classId);
				video.setDescript(desc);
				video.setXnHitCount(xnHits);
				video.setVideoDate(videoDate);
				video.setVideoType(videoType);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findAllVideoByCommId(int commId, int page) {
		int totlePage = getTotlePage("WHERE ColumnId  =" + commId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Description,Id,ImagesUrl,Title,PlayXNCount,VideoDate,VideoType,VideoPageUrl,FK_Pecial,FK_ClassId FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE ColumnId = "
							+ commId
							+ ") A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String imageUrl = res.getString("ImagesUrl");
				String title = res.getString("Title");
				int xnHits = res.getInt("PlayXNCount");
				String videoDate = res.getString("VideoDate");
				int videoType = res.getInt("VideoType");
				String pageUrl = res.getString("VideoPageUrl");
				int specialId = res.getInt("FK_Pecial");
				int classId = res.getInt("FK_ClassId");
				String desc = res.getString("Description");
				Video video = new Video();
				video.setId(id);
				video.setTotlePage(totlePage);
				video.setPage(page);
				video.setClassId(classId);
				video.setTitle(title);
				video.setDescript(desc);
				video.setPecialId(specialId);
				video.setImages(imageUrl);
				video.setHtmlUrl(pageUrl);
				video.setXnHitCount(xnHits);
				video.setVideoDate(videoDate);
				video.setVideoType(videoType);
				videos.add(video);
			}
			return videos;
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
	public int updateHits(int hits, int xnHits, int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Video SET PlayCount=" + hits
					+ ", PlayXNCount=" + xnHits + " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Video> findVideoByIsBestLast(int count, int isBest) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select TOP "
							+ count
							+ " FK_Pecial,FK_ClassId,Id,Title,Description,ImagesUrl,PlayCount,VideoDate,VideoUrl,VideoType,PlayXNCount,VideoPageUrl from JCP_Video"
							+ " WHERE IsJinXuan=" + isBest
							+ " ORDER BY PlayXNCount DESC");

			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String descript = res.getString(SqlUtil.VIDEO_DESC);
				String images = res.getString("ImagesUrl");
				int playCount = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				String VideoUrl = res.getString("VideoUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int xnHits = res.getInt("PlayXNCount");
				String pageUrl = res.getString("VideoPageUrl");
				Video video = new Video(id, title);
				video.setDescript(descript);
				video.setImages(images);
				video.setVideoType(videoType);
				video.setVideoUrl(VideoUrl);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setHitCount(playCount);
				video.setVideoDate(videoDate);
				video.setPecialId(specialId);
				video.setClassId(cId);
				videos.add(video);
			}
			return videos;
		} catch (Exception e) {
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
	public List<Video> findVideoByClassId(int classId, int page) {
		// 根据分类获取视频列表 FK_Pecial 所属专辑
		int totlePage = getTotlePage(" WHERE FK_ClassId=" + classId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,Description,IsMySite,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoUrl,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId="
							+ classId
							+ " ) A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				int isMySiteVideo = res.getInt("IsMySite");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int pecialId = res.getInt("FK_Pecial");
				int hits = res.getInt("PlayCount");
				int videoType = res.getInt("VideoType");
				String VideoUrl = res.getString("VideoUrl");
				String VideoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setClassId(cId);
				video.setVideoDate(VideoDate);
				video.setVideoUrl(VideoUrl);
				video.setVideoType(videoType);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				video.setPecialId(pecialId);
				video.setHtmlUrl(pageUrl);
				video.setTotlePage(totlePage);
				video.setPage(page);
				video.setIsMySiteVideo(isMySiteVideo);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTypeAndClassIdAndTeacherId(int type,
			int classId, int teacherId, int page) {
		int totlePage = getTotlePage("WHERE FK_ClassId=" + classId
				+ " AND FK_TypeId=" + type + " AND TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,VideoPageUrl,PlayCount,FK_Pecial,VideoType,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId="
							+ classId
							+ " AND FK_TypeId="
							+ type
							+ " AND TearcherId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int specialId = res.getInt("FK_Pecial");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setHtmlUrl(pageUrl);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				video.setVideoDate(videoDate);
				video.setPecialId(specialId);
				video.setVideoType(videoType);
				video.setTotlePage(totlePage);
				video.setClassId(cId);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTypeAndClassId(int type, int classId, int page) {
		int totlePage = getTotlePage("WHERE FK_ClassId=" + classId
				+ " AND FK_TypeId=" + type);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId ="
							+ classId
							+ " AND FK_TypeId="
							+ type
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setVideoDate(videoDate);
				video.setTotlePage(totlePage);
				video.setHtmlUrl(pageUrl);
				video.setClassId(cId);
				video.setXnHitCount(xnHits);
				video.setHitCount(hits);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findVideoByTeacherIdAndClassId(int teacherId,
			int classId, int page) {
		int totlePage = getTotlePage("WHERE FK_ClassId  =" + classId
				+ " AND TearcherId=" + teacherId);
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,Description,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE FK_ClassId ="
							+ classId
							+ " AND TearcherId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int specialId = res.getInt("FK_Pecial");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setVideoDate(videoDate);
				video.setHitCount(hits);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setTotlePage(totlePage);
				video.setClassId(cId);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findSpecialByLast(int count, int specialId) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select Top " + count
					+ " Id,Title,TearcherId from JCP_Video where FK_Pecial="
					+ specialId);
			while (res.next()) {
				int id = res.getInt(1);
				String title = res.getString(2);
				int teacherId = res.getInt(3);
				Video video = new Video(id, title);
				video.setTeacherId(teacherId);
				videos.add(video);
			}
			return videos;
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
	public List<Video> findSpecialVideo(int page, int specialId) {
		try {
			videos.clear();
			int totlePage = getTotlePage(" WHERE FK_Pecial=" + specialId);
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 Id,Title,ImagesUrl,Description,VideoPageUrl,FK_ClassId,FK_Pecial,VideoDate,PlayXNCount,VideoType,PlayCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video"
							+ " WHERE FK_Pecial="
							+ specialId
							+ ") A "
							+ "WHERE RowNumber > " + 20 * (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int videoType = res.getInt("VideoType");
				int hits = res.getInt("PlayCount");
				String videoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setVideoDate(videoDate);
				video.setHitCount(hits);
				video.setVideoType(videoType);
				video.setPecialId(specialId);
				video.setTotlePage(totlePage);
				video.setClassId(cId);
				video.setXnHitCount(xnHits);
				video.setHtmlUrl(pageUrl);
				video.setPage(page);
				videos.add(video);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Video findHitsAndGoods(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT PlayCount,PlayXNCount,Zan FROM JCP_Video WHERE Id="
							+ id);
			while (res.next()) {
				int hits = res.getInt(1);
				int xnHits = res.getInt(2);
				int goods = res.getInt(3);
				Video video = new Video();
				video.setGoods(goods);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				return video;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateGoods(int id, int goods) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Video SET Zan=" + goods
					+ " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Video> findVideoByKeyWord(String keyWord, int page) {
		videos.clear();
		int totlePage = getTotlePage(" WHERE Title LIKE '%" + keyWord + "%'");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 20 FK_ClassId,Id,Title,ImagesUrl,Description,IsMySite,VideoPageUrl,FK_Pecial,VideoType,PlayCount,VideoUrl,VideoDate,PlayXNCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Video WHERE Title LIKE '%"
							+ keyWord
							+ "%'  ) A "
							+ "WHERE RowNumber > "
							+ 20
							* (page - 1));
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String desc = res.getString("Description");
				int isMySiteVideo = res.getInt("IsMySite");
				String pageUrl = res.getString("VideoPageUrl");
				int cId = res.getInt("FK_ClassId");
				int pecialId = res.getInt("FK_Pecial");
				int hits = res.getInt("PlayCount");
				int videoType = res.getInt("VideoType");
				String VideoUrl = res.getString("VideoUrl");
				String VideoDate = res.getString("VideoDate");
				int xnHits = res.getInt("PlayXNCount");
				Video video = new Video(id, title);
				video.setImages(Images);
				video.setDescript(desc);
				video.setClassId(cId);
				video.setVideoDate(VideoDate);
				video.setVideoUrl(VideoUrl);
				video.setVideoType(videoType);
				video.setHitCount(hits);
				video.setXnHitCount(xnHits);
				video.setPecialId(pecialId);
				video.setHtmlUrl(pageUrl);
				video.setTotlePage(totlePage);
				video.setPage(page);
				video.setIsMySiteVideo(isMySiteVideo);
				videos.add(video);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Video> findHotVideoList(int top) {
		videos.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ top
							+ " Title,Id FROM JCP_Video ORDER BY PlayXNCount DESC,VideoDate DESC");
			while (res.next()) {
				String title = res.getString(1);
				int id=res.getInt(2);
				Video video = new Video();
				video.setTitle(title);
				video.setId(id);
				videos.add(video);
			}
			return videos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Video findLastVideoByTeacher(int teacherId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 1 Title,ImagesUrl,VideoPageUrl FROM JCP_Video WHERE TearcherId="
							+ teacherId+" ORDER BY VideoDate DESC");
			while (res.next()) {
				String title = res.getString(SqlUtil.VIDEO_TITLE);
				String Images = res.getString(SqlUtil.VIDEO_IMAGES);
				String videoUrl = res.getString("VideoPageUrl");
				Video video = new Video();
				video.setImages(Images);
				video.setVideoUrl(videoUrl);
				video.setTitle(title);
				return video;
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

}
