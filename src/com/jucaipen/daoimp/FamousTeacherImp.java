package com.jucaipen.daoimp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.FamousTeacherDao;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.utils.JdbcUtil;
/**
 * @author Administrator
 * 
 * 
 *         获取名师信息
 */
public class FamousTeacherImp implements FamousTeacherDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<FamousTeacher> teachers = new ArrayList<FamousTeacher>();

	/**
	 * @return 查询新闻总页数
	 */
	public int findTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Tearcher "
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
	public List<FamousTeacher> findTeacherByKeyWord(String keyWord) {
		teachers.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Tearcher WHERE State=0 AND NickName LIKE '%"+keyWord+"%'");
			teachers = getTeacher(res, 1, 1);
			return teachers;
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
	

	public List<FamousTeacher> findIndexData() {
		teachers.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  Fans,HuiDaCount,ArticleCount from JCP_Tearcher WHERE State=0");
			while (res.next()) {
				int fans = res.getInt(1);
				int answerCount = res.getInt(2);
				int articleCount = res.getInt(3);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setFans(fans);
				teacher.setAnswerCount(answerCount);
				teacher.setArticleCount(articleCount);
				teachers.add(teacher);
			}
			return teachers;
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

	public List<FamousTeacher> findAllFamousTeacher(int page) {
		try {
			int totlePage = findTotlePage("");
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY LiveRenQi,MonthRenQi,WeekRenQi DESC) AS RowNumber,* FROM JCP_Tearcher  WHERE State=0) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			teachers = getTeacher(res, page, totlePage);
			return teachers;
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

	/*
	 * 
	 *   
	 * 
	 */
	public List<FamousTeacher> findFamousTeacherByIndex(int count) {
		teachers.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " * FROM JCP_Tearcher  WHERE State=0 ORDER BY Fans DESC,LiveRenQi DESC");
			while (res.next()) {
				int id = res.getInt("Id");
				int isV = res.getInt("IsV");
				String nickName = res.getString("NickName");
				String headFace = res.getString("HeadFace");
				String touxian = res.getString("TouXian");
				String introduce = res.getString("Jianjie");
				String notice = res.getString("Notice");
				String ShanChang = res.getString("ShanChang");
				int fans = res.getInt("Fans");
				FamousTeacher famousTeacher = new FamousTeacher();
				famousTeacher.setId(id);
				famousTeacher.setIsV(isV);
				famousTeacher.setHoby(ShanChang);
				famousTeacher.setNickName(nickName);
				famousTeacher.setHeadFace(headFace);
				famousTeacher.setLevel(touxian);
				famousTeacher.setFans(fans);
				famousTeacher.setNotice(notice);
				famousTeacher.setIntroduce(introduce);
				teachers.add(famousTeacher);
			}
			return teachers;
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

	public FamousTeacher findFamousTeacherById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Tearcher WHERE Id=" + id
					+ " AND State=0");
			teachers = getTeacher(res, 0, 0);
			if (teachers!=null&&teachers.size() > 0) {
				return teachers.get(0);
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

	public List<FamousTeacher> getTeacher(ResultSet result, int page,
			int totlePage) {
		teachers.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				String trueName = result.getString("TrueName");
				String nickName = result.getString("NickName");
				String headFace = result.getString("HeadFace");
				String touxian = result.getString("TouXian");
				int userId=result.getInt("FK_UserId");
				int isV = result.getInt("IsV");
				int fans = result.getInt("Fans");
				int weekRenQi = result.getInt("WeekRenQi");
				int mothRenQi = result.getInt("MonthRenQi");
				int answerCount = result.getInt("HuiDaCount");
				int articleCount = result.getInt("ArticleCount");
				int articleReadCount = result.getInt("ArticleReadCount");
				int articleGood = result.getInt("ArticleGood");
				String notice = result.getString("Notice");
				String introduce = result.getString("Jianjie");
				String hoby = result.getString("ShanChang");
				String telPhone = result.getString("MobileNum");
				int serviceNum = result.getInt("QianYueCount");
				String joinDate = result.getString("JoinDate");
				int liveGbookIsPass = result.getInt("LiveGbookIsPass");
				int yearPrice = result.getInt("YearPrice");
				int motnPrice = result.getInt("MonthPrice");
				int quarterPrice = result.getInt("QuarterPrice");
				int dayPrice = result.getInt("DayPrice");
				int renQi = result.getInt("LiveRenQi");
				int askNum = result.getInt("AskNum");
				int isTxtFree = result.getInt("TxtLiveIsFree");
				int txtPrice = result.getInt("TxtLivePrice");
				int answerPrice = result.getInt("AnswerPrice");
				int isUserLive = result.getInt("IsUserLiveUrl");
				int isLiveFree = result.getInt("VideoLiveIsFree");
				int livePrice = result.getInt("VideoLivePrice");
				int xnLive = result.getInt("LiveRenQiXuLi");
				int xnReadIdeaNum = result.getInt("ArticleReadCountXuLi");
				int bankId = result.getInt("FK_BankId");
				String bankAccount = result.getString("BankAccount");
				String appLiveUrl=result.getString("AppLiveUrl");
				FamousTeacher teacher = new FamousTeacher();
				teacher.setPage(page);
				teacher.setTotlePage(totlePage);
				teacher.setId(id);
				teacher.setTxtLiveFree(isTxtFree);
				teacher.setTxtLivePrice(txtPrice);
				teacher.setXnLiveRenQi(xnLive);
				teacher.setAppLiveUrl(appLiveUrl);
				teacher.setAnswerCount(answerCount);
				teacher.setFk_UserId(userId);
				teacher.setArticleCount(articleCount);
				teacher.setTrueName(trueName);
				teacher.setTelPhone(telPhone);
				teacher.setXnArticleReadNum(xnReadIdeaNum);
				teacher.setIsUserLiveUrl(isUserLive);
				teacher.setLivePrice(livePrice);
				teacher.setLiveFree(isLiveFree);
				teacher.setNickName(nickName);
				teacher.setHeadFace(headFace);
				teacher.setLevel(touxian);
				teacher.setBankId(bankId);
				teacher.setBankAccount(bankAccount);
				teacher.setIntroduce(introduce);
				teacher.setIsV(isV);
				teacher.setAnswerPrice(answerPrice);
				teacher.setYaerPrice(yearPrice);
				teacher.setMothPrice(motnPrice);
				teacher.setQulaterPrice(quarterPrice);
				teacher.setDayPrice(dayPrice);
				teacher.setFans(fans);
				teacher.setLiveFans(renQi);
				teacher.setWeekRenQi(weekRenQi);
				teacher.setMothRenQi(mothRenQi);
				teacher.setHoby(hoby);
				teacher.setArticleReadCount(articleReadCount);
				teacher.setArticleGood(articleGood);
				teacher.setNotice(notice);
				teacher.setSignCount(serviceNum);
				teacher.setJoinDate(joinDate);
				teacher.setLiveGbookIsPass(liveGbookIsPass);
				teacher.setAskNum(askNum);
				teachers.add(teacher);
			}
			return teachers;
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
	public int findMaxAsk(int id) {
		// 获取讲师最大提问数
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT AskNum FROM JCP_Tearcher WHERE Id="
					+ id + "  AND State=0");
			while (res.next()) {
				return res.getInt(1);
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
		return 0;
	}

	@Override
	public List<FamousTeacher> findAllTeacher() {
		// 获取所有讲师信息 不分页
		teachers.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,NickName FROM JCP_Tearcher WHERE State=0");
			while (res.next()) {
				int id = res.getInt(1);
				String nickName = res.getString(2);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setId(id);
				teacher.setNickName(nickName);
				teachers.add(teacher);
			}
			return teachers;
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
	public FamousTeacher findTeacherBaseInfo(int tId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Fans,AskNum,NickName,QianYUeCount,ReturnRate,HeadFace,FK_UserId,Jianjie,TouXian,VideoLiveIsFree FROM JCP_Tearcher WHERE Id="
							+ tId + "  AND  State=0");
			while (res.next()) {
				int fans = res.getInt(1);
				int askNum = res.getInt(2);
				String nickName = res.getString(3);
				int qianYueCount = res.getInt(4);
				double returnRate = res.getDouble(5);
				String headFace = res.getString(6);
				int fk_uId = res.getInt(7);
				String introduce=res.getString(8);
				String leavel=res.getString(9);
				int isFree=res.getInt(10);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setFans(fans);
				teacher.setId(tId);
				teacher.setFk_UserId(fk_uId);
				teacher.setHeadFace(headFace);
				teacher.setReturnRate(returnRate);
				teacher.setSignCount(qianYueCount);
				teacher.setAskNum(askNum);
				teacher.setLevel(leavel);
				teacher.setLiveFree(isFree);
				teacher.setIntroduce(introduce);
				teacher.setNickName(nickName);
				return teacher;
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
	public int updateFansNum(int fans, int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Tearcher SET Fans=" + fans
					+ " WHERE Id=" + id + "  AND State=0");
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
	public int updateAskNum(int num, int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Tearcher SET AskNum=" + num
					+ " WHERE Id=" + id);
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
	public FamousTeacher findPurchInfo(int tId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT NickName,DayPrice,QuarterPrice,MonthPrice,YearPrice,WeekPrice FROM JCP_Tearcher WHERE Id="
							+ tId + "  AND State=0");
			while (res.next()) {
				String nickName = res.getString(1);
				int dayPrice = res.getInt(2);
				int quartPrice = res.getInt(3);
				int mothPrice = res.getInt(4);
				int yearPrice = res.getInt(5);
				int weekPrice=res.getInt(6);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setNickName(nickName);
				teacher.setDayPrice(dayPrice);
				teacher.setMothPrice(mothPrice);
				teacher.setQulaterPrice(quartPrice);
				teacher.setYaerPrice(yearPrice);
				teacher.setWeekPrice(weekPrice);
				return teacher;
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
	public int updateIdeaReadNum(int id, int readNum, int xnNum) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("UPDATE JCP_Tearcher SET ArticleReadCount="
							+ readNum + ",ArticleReadCountXuLi=" + xnNum
							+ " WHERE Id=" + id);
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
	public FamousTeacher findFamousTeacherByUserId(int userId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id FROM JCP_Tearcher WHERE FK_UserId="
							+ userId + " AND State=0");
			while (res.next()) {
				int id = res.getInt(1);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setId(id);
				return teacher;
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
	public int updateTeacherBaseInfo(int teacherId, FamousTeacher teacher) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Tearcher SET  NickName='"
					+ teacher.getNickName() + "',HeadFace='"
					+ teacher.getHeadFace() + "',Notice='"
					+ teacher.getNotice() + "',Jianjie='"
					+ teacher.getIntroduce() + "',ShanChang='"
					+ teacher.getHoby() + "',FK_BankId=" + teacher.getBankId()
					+ ",BankAccount='" + teacher.getBankAccount() + "')");
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
	public List<FamousTeacher> findHotTeacher(int top) {
		try {
			teachers.clear();
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "+top+" Id,NickName,HeadFace,TouXian FROM JCP_Tearcher WHERE State=0 ORDER BY LiveRenQi DESC");
			while (res.next()) {
				int id = res.getInt(1);
				String name=res.getString(2);
				String headFace=res.getString(3);
				String leavel=res.getString(4);
				FamousTeacher teacher = new FamousTeacher();
				teacher.setId(id);
				teacher.setNickName(name);
				teacher.setHeadFace(headFace);
				teacher.setLevel(leavel);
				teachers.add(teacher);
			}
			return teachers;
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
