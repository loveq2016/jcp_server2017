package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.KnowledgeDao;
import com.jucaipen.model.Knowledge;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         知识
 */
public class KnowledgeImp implements KnowledgeDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Knowledge> knowledges = new ArrayList<Knowledge>();

	/**
	 * @return 查询知识总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_BasicKnowledge "
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
	public Knowledge findKnowledgeById(int id) {
		// 根据id获取知识信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_BasicKnowledge WHERE Id="
					+ id);
			while (res.next()) {
				String title = res.getString(2); // Title
				String keyWord = res.getString(3); // KeyWord
				String bodys = res.getString(4); // Bodys
				int classId = res.getInt(5); // FK_ClassId
				int hits = res.getInt(6); // HitCount
				int xnHits = res.getInt(7); // HitXNCount
				String insertDate = res.getString(8); // InsertDate
				int fromId = res.getInt(9); // FK_FromId
				// String publisher=res.getString(10); //FaBuRen
				String imageUrl = res.getString(11); // ImageUrl
				// String writer=res.getString(12); //ZuoZhe
				int goods = res.getInt(14); // Zan
				int comms = res.getInt(15); // CommonCount
				// int isTop=res.getInt(16); //IsTop
				// int isBest=res.getInt(17); //IsJingXuan
				String desc = res.getString(18); // ZhaiYao
				// int isIndex=res.getInt(19); //IsIndex
				// IsTuiJian SortId
				Knowledge knowledge = new Knowledge();
				knowledge.setKeyWord(keyWord);
				knowledge.setTitle(title);
				knowledge.setBodys(bodys);
				knowledge.setFk_ClassId(classId);
				knowledge.setHits(hits);
				knowledge.setXnHits(xnHits);
				knowledge.setId(id);
				knowledge.setFromId(fromId);
				knowledge.setInsertDate(insertDate);
				knowledge.setImageUrl(imageUrl);
				knowledge.setGoods(goods);
				knowledge.setComms(comms);
				knowledge.setZhaiYao(desc);
				return knowledge;
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
	public List<Knowledge> findKnowledgeByClassId(int classId, int page) {
		// 根据分类获取知识信息
		knowledges.clear();
		int totlePage = getTotlePage("WHERE FK_ClassId=" + classId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id ASC) AS RowNumber,* FROM JCP_BasicKnowledge WHERE FK_ClassId="
							+ classId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id"); // Id
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("KeyWord"); // KeyWord
				String body = res.getString("Bodys"); // Bodys
				int hits = res.getInt("HitCount"); // HitCount
				int xnHits = res.getInt("HitXNCount"); // HitXNCount
				String insertDate = res.getString("InsertDate"); // InsertDate
				int fromId = res.getInt("FK_FromId"); // FK_FromId
				String image = res.getString("ImageUrl"); // ImageUrl
				String zuoZhe = res.getString("ZuoZhe"); // ZuoZhe
				int zans = res.getInt("Zan"); // Zan
				int comms = res.getInt("CommonCount"); // CommonCount
				String zhaiYao = res.getString("ZhaiYao"); // ZhaiYao
				Knowledge knowledge = new Knowledge();
				knowledge.setTotlePage(totlePage);
				knowledge.setPage(page);
				knowledge.setId(id);
				knowledge.setTitle(title);
				knowledge.setKeyWord(keyWord);
				knowledge.setBodys(body);
				knowledge.setHits(hits);
				knowledge.setXnHits(xnHits);
				knowledge.setInsertDate(insertDate);
				knowledge.setFromId(fromId);
				knowledge.setImageUrl(image);
				knowledge.setZuozhe(zuoZhe);
				knowledge.setGoods(zans);
				knowledge.setComms(comms);
				knowledge.setZhaiYao(zhaiYao);
				knowledges.add(knowledge);
			}
			return knowledges;
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
	public List<Knowledge> findAllKnowledge(int page) {
		// 获取所有的知识信息
		knowledges.clear();
		int totlePage = getTotlePage("");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_BasicKnowledge"
							+ ") A " + "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id"); // Id
				String title = res.getString("Title"); // Title
				String keyWord = res.getString("KeyWord"); // KeyWord
				String body = res.getString("Bodys"); // Bodys
				int hits = res.getInt("HitCount"); // HitCount
				int xnHits = res.getInt("HitXNCount"); // HitXNCount
				String insertDate = res.getString("InsertDate"); // InsertDate
				int fromId = res.getInt("FK_FromId"); // FK_FromId
				String image = res.getString("ImageUrl"); // ImageUrl
				String zuoZhe = res.getString("ZuoZhe"); // ZuoZhe
				int zans = res.getInt("Zan"); // Zan
				int comms = res.getInt("CommonCount"); // CommonCount
				String zhaiYao = res.getString("ZhaiYao"); // ZhaiYao
				int classId = res.getInt("FK_ClassId"); // FK_ClassId
				Knowledge knowledge = new Knowledge();
				knowledge.setTotlePage(totlePage);
				knowledge.setPage(page);
				knowledge.setFk_ClassId(classId);
				knowledge.setId(id);
				knowledge.setTitle(title);
				knowledge.setKeyWord(keyWord);
				knowledge.setBodys(body);
				knowledge.setHits(hits);
				knowledge.setXnHits(xnHits);
				knowledge.setInsertDate(insertDate);
				knowledge.setFromId(fromId);
				knowledge.setImageUrl(image);
				knowledge.setZuozhe(zuoZhe);
				knowledge.setGoods(zans);
				knowledge.setComms(comms);
				knowledge.setZhaiYao(zhaiYao);
				knowledges.add(knowledge);
			}
			return knowledges;
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
	public Knowledge findTitleById(int id, int classId) {
		// 获取上下篇信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Title FROM JCP_BasicKnowledge WHERE FK_ClassId="
							+ classId + " AND Id=" + id);
			while (res.next()) {
				String title = res.getString(1);
				Knowledge knowledge = new Knowledge();
				knowledge.setTitle(title);
				return knowledge;
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
	public int addHits(int id, int hits, int xnHits) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_BasicKnowledge SET HitCount="
					+ hits + ",HitXNCount=" + xnHits + " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Knowledge findHitAndGoods(int id) {
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT Zan,HitCount,HitXNCount FROM JCP_BasicKnowledge WHERE Id="+id);
		    while (res.next()) {
				int goods=res.getInt(1);
				int hits=res.getInt(2);
				int xnHits=res.getInt(3);
				Knowledge knowledge=new Knowledge();
				knowledge.setGoods(goods);
				knowledge.setHits(hits);
				knowledge.setXnHits(xnHits);
				return knowledge;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addGoods(int id, int goods) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_BasicKnowledge SET Zan="
					+ goods +" WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
