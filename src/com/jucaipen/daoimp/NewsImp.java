package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.NewsDao;
import com.jucaipen.model.News;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

/**
 * @author YLF
 * 
 *         新闻操作实现类
 * 
 */
public class NewsImp implements NewsDao {
	private Statement sta;
	private ResultSet res;
	private Connection dbConn;

	private List<News> news = new ArrayList<News>();
	private int isSuccess;

	/**
	 * @return 查询新闻总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_News "
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

	/*
	 * 查询所有新闻
	 */
	public List<News> findAll(int pager) {
		int totlePager = findTotlePager("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			if (dbConn != null) {
				sta = dbConn.createStatement();
				res = sta
						.executeQuery("SELECT TOP 15 * FROM "
								+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc,Id desc) AS RowNumber,* FROM JCP_News) A "
								+ "WHERE RowNumber > " + 15 * (pager - 1));
				news = getNews(res, pager, totlePager);
				return news;
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

	/*
	 * 通过分类查询新闻
	 */
	public List<News> findNewsBybigId(int classId, int pager) {
		int totlePager = findTotlePager("Where FK_ClassId=" + classId);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 Id,Title,ImageUrl,ZhaiYao,PageUrl,FK_FromId,HitCount,Zan FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc ,id desc) AS RowNumber,* FROM JCP_News"
							+ " WHERE FK_ClassId="
							+ classId
							+ ") A "
							+ "WHERE RowNumber > " + 15 * (pager - 1));
			news = getNewsList(res, pager, totlePager);
			return news;
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


	/*
	 * 查询首页显示新闻
	 */
	public List<News> findNewsByIndex(int isIndex, int pager) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select  * from JCP_News where IsIndex="
					+ isIndex + " order by InsertDate desc");
			news = getNews(res, pager, -1);
			return news;
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


	/*
	 * 查询精选的新闻
	 */
	public List<News> findNewsByBest(int isBest, int pager) {
		int totlePager = findTotlePager("where IsJingXuan=" + isBest);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc,id desc) AS RowNumber,* FROM JCP_News"
							+ " where IsJingXuan=" + isBest + ") A "
							+ "WHERE RowNumber > " + 15 * (pager - 1));
			news = getNews(res, pager, totlePager);
			return news;
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

	/*
	 * 查询是否置顶的新闻
	 */
	public List<News> findNewsByTop(int isTop, int pager) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select  * from JCP_News where IsTop="
					+ isTop + " order by InsertDate desc");
			news = getNews(res, pager, -1);
			return news;
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

	/*
	 * 查询新闻详细信息
	 */
	public News findNews(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_News where Id=" + id);
			news = getNews(res, -1, -1);
			if(news!=null&&news.size()>0){
				return news.get(0);
			}else {
				return null;
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


	/*
	 * 首页新闻---分类
	 */
	public List<News> findNewsIndex(int classId, int isIndex) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_News where FK_ClassId=" + classId
					+ " and IsIndex=" + isIndex + " order by InsertDate desc");
			news = getNews(res, -1, -1);
			return news;
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

	public int upDateHits(int xnHits,int hits, int id) {
		// 修改点击数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_News SET HitCount=" + hits
					+ ",HitXNCount="+xnHits+" WHERE Id=" + id);
			return isSuccess;
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

	public int upDateComments(int Commens,int id) {
		// 修改评论数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_News SET CommonCount=" + Commens
					+ " WHERE Id=" + id);
			return isSuccess;
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

	/*
	 * 首页显示新闻
	 *    全部（不过滤图片）
	 */
	public List<News> findIndexShow(int classId,int top) {
		news.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select top "+top+" Id,Title,ImageUrl,ZhaiYao from JCP_News where  FK_ClassId="
							+ classId + "  order by InsertDate desc,Id desc");
			while (res.next()) {
				String title = res.getString(SqlUtil.NEWS_TITLE);
				int id = res.getInt(SqlUtil.NEWS_ID);
				String images = res.getString(SqlUtil.NEW_IMAGE);
				String descript = res.getString(SqlUtil.NEWS_DES);
				News n = new News(id);
				n.setTitle(title);
				n.setSummary(descript);
				n.setImageUrl(images);
				news.add(n);
			}
			return news;
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

	
	/**
	 * @param res
	 * @param pager
	 * @return 获取新闻列表
	 */
	public List<News> getNewsList(ResultSet res, int pager, int totlePager) {
		news.clear();
		try {
			while (res.next()) {
				String title = res.getString(SqlUtil.NEWS_TITLE);
				String summary = res.getString(SqlUtil.NEWS_DES);
				int id = res.getInt(SqlUtil.NEWS_ID);
				String imageUrl = res.getString(SqlUtil.NEW_IMAGE);
				int from=res.getInt(SqlUtil.NEWS_COMEFROM);
				String insertDate=res.getString(SqlUtil.NEWS_INSERT);
				int comms=res.getInt(SqlUtil.NEWS_COMMS);
				int goods=res.getInt("Zan");
				int hits=res.getInt("HitCount");
				News n = new News(id);
				n.setPager(pager);
				n.setTotlePager(totlePager);
				n.setTitle(title);
				n.setSummary(summary);
				n.setId(id);
				n.setHits(hits);
				n.setGoods(goods);
				n.setFromId(from);
				n.setPublishDate(insertDate);
				n.setComments(comms);
				n.setImageUrl(imageUrl);
				news.add(n);
			}
			return news;
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

	/**
	 * @param res
	 * @param pager
	 * @return 获取新闻信息
	 */
	public List<News> getNews(ResultSet res, int pager, int totlePager) {
		news.clear();
		try {
			while (res.next()) {
				int classId = res.getInt("FK_ClassId");
				String title = res.getString(SqlUtil.NEWS_TITLE);
				String descript = res.getString(SqlUtil.NEWS_DES);
				int id = res.getInt(SqlUtil.NEWS_ID);
				int comeFrom=res.getInt(SqlUtil.NEWS_COMEFROM);
				String keyWord = res.getString(SqlUtil.NEWS_KEYWORD);
				String reporter = res.getString(SqlUtil.NEWS_REPOTER);
				String bodys = res.getString(SqlUtil.NEWS_BODYS);
				String imageUrl = res.getString(SqlUtil.NEW_IMAGE);
				String date = res.getString(SqlUtil.NEWS_INSERT);
				String htmlPath = res.getString(SqlUtil.NEWS_HTMLPATH);
				int hits=res.getInt("HitCount");
				int xnHits=res.getInt("HitXNCount");
				int goods=res.getInt("Zan");
				News n = new News(id);
				n.setImageUrl(imageUrl);
				n.setBodys(bodys);
				n.setTitle(title);
				n.setGoods(goods);
				n.setHits(hits);
				n.setXnHits(xnHits);
				n.setFromId(comeFrom);
				n.setSummary(descript);
				n.setReporter(reporter);
				n.setKeyWord(keyWord);
				n.setClassId(classId);
				n.setHtmlPath(htmlPath);
				n.setPager(pager);
				n.setTotlePager(totlePager);
				n.setPublishDate(date);
				news.add(n);
			}
			return news;
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

	public List<News> findRelatedNewsById(int id) {
		// 根据指定新闻id 查询相关新闻
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT Title FROM JCPNews WHERE Id=" + id);
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

	public List<News> findLastNewsByNewsNum(int count) {
		// 获取最新的count条新闻
		news.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " Id,Title,ImageUrl,InsertDate,CommonCount,FK_FromId FROM JCPNews ORDER BY InsertDate DESC,Id DESC");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				String title = res.getString(SqlUtil.NEWS_TITLE);
				String image=res.getString(SqlUtil.NEW_IMAGE);
				String insertDate=res.getString(SqlUtil.NEWS_INSERT);
				int comments=res.getInt(SqlUtil.NEWS_COMMS);
				int from=res.getInt(SqlUtil.NEWS_COMEFROM);
				News n = new News(id);
				n.setTitle(title);
				n.setFromId(from);
				n.setImageUrl(image);
				n.setPublishDate(insertDate);
				n.setComments(comments);
				news.add(n);
			}
			return news;
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
