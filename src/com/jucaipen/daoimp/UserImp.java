package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.UserDao;
import com.jucaipen.model.User;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

/**
 * @author ylf
 * 
 *         用户操作类实现
 * 
 */
public class UserImp implements UserDao {
	private Connection dbConn;
	private Statement sta;
	private int isSuccess;
	private ResultSet res;
	private List<User> users;

	/**
	 * @return 查询用户总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_User "
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

	/*
	 * 用户注册
	 */
	public int reginUser(User user) {
		/*
		 * try { dbConn = JdbcUtil.connSqlServer(); sta =
		 * dbConn.createStatement(); isSuccess =
		 * sta.executeUpdate("insert into " + SqlUtil.USER_TABLE + "(" +
		 * SqlUtil.USER_NAME + "," + SqlUtil.USER_MOBILE + "," +
		 * SqlUtil.USER_PASSWORD + "," + SqlUtil.USER_REGFROM + "," +
		 * SqlUtil.USER_REGINDATE + "," + SqlUtil.USER_REGINIP + ") values(" +
		 * user.getMobileNum() + "','" + user.getPassword() + "'," + 5 + ",'" +
		 * user.getRegDate() + "','" + user.getRegIp() + "')"); return
		 * isSuccess; } catch (SQLException e) { e.printStackTrace(); }
		 */
		return 0;
	}

	/*
	 * 查询所有用户信息
	 */
	public List<User> findUser(int page) {
		int totlePage = findTotlePager("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM JCP_User"
							+ ") A " + "WHERE RowNumber > " + 15 * (page - 1));
			users = getUser(res, page, totlePage);
			return users;
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
	 * 根据id查询用户信息
	 */
	public User findUserById(int id) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select UserLevel,UserName,ISNULL(FK_InvestmentTypeId,'') FK_InvestmentTypeId,ISNULL(TrueName,'') TrueName,ISNULL(NickName,'') NickName,ISNULL(Sex,'') Sex,ISNULL(MobileNum,'') MobileNum,ProvinceID,CiytID,AreaID,ISNULL(UserInformation,'') UserInformation,ISNULL(Birthday,'') Birthday,ISNULL(UserFace,'') UserFace from JCP_User where Id="
							+ id);
			while (res.next()) {
				String userName = res.getString("UserName");
				String nickName = res.getString(SqlUtil.USER_NICKNAME);
				String trueName = res.getString("TrueName");
				String sex = res.getString(SqlUtil.USER_SEX);
				String telPhone = res.getString(SqlUtil.USER_MOBILE);
				int localCity = res.getInt(SqlUtil.USER_LOCALCITY);
				int localProvince = res.getInt(SqlUtil.USER_LOCALPROVINCE);
				int localArea = res.getInt(SqlUtil.USER_LOCALAREA);
				String desc = res.getString(SqlUtil.USER_BODYS);
				String birthday = res.getString(SqlUtil.USER_BIRTH);
				String logo = res.getString(SqlUtil.USRE_FACEIMAGE);
				int investmentId = res.getInt("FK_InvestmentTypeId");
				int userLeavel = res.getInt("UserLevel");
				u = new User();
				u.setNickName(nickName);
				u.setSex(sex);
				u.setProvinceId(localProvince);
				u.setAreaId(localArea);
				u.setUserName(userName);
				u.setUserLeval(userLeavel);
				u.setTrueName(trueName);
				u.setMobileNum(telPhone);
				u.setBirthday(birthday);
				u.setCityId(localCity);
				u.setDescript(desc);
				u.setFaceImage(logo);
				u.setInvestId(investmentId);
				return u;
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

	/*
	 * 
	 * 获取用户头像
	 */
	public String querryFaceImage(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT UserFace FROM JCP_User WHERE Id="
					+ id);
			while (res.next()) {
				String face = res.getString("UserFace");
				return face;
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

	// 根据用户ID修改用户手机号
	public int updatePhoneById(int id, String tel) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_User SET MobileNum='"
					+ tel + "'" + "WHERE Id=" + id);
			return isSuccess;
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

	public int updateUserTrueNameAndTelById(String telPhone, User user) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_User SET TrueName='"
					+ user.getTrueName() + "' WHERE MobileNum='" + telPhone
					+ "'");
			return isSuccess;
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

	/*
	 * 
	 * 修改用户头像URL
	 */
	public int updateUserLogoById(int id, String logoPath) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_User SET UserFace='"
					+ logoPath + "' WHERE Id=" + id);
			return isSuccess;
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

	/*
	 * 根据id查询用户密码
	 */
	public User findPasswordById(int id) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select PassWord from JCP_User where Id="
					+ id);
			res.next();
			String password = res.getString(SqlUtil.USER_PASSWORD);
			u = new User();
			u.setPassword(password);
			return u;
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

	/**
	 * @param id
	 * @return 根据id查询用户昵称---用于聊天
	 */
	public User findUserInfoById(int id) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("select ISNULL (TrueName,'') TrueName,ISNULL (NickName,'') NickName,ISNULL (UserFace,'') UserFace,ISNULL (MobileNum,'') MobileNum,UserName from JCP_User where Id="
							+ id);
			while (res.next()) {
				String NickName = res.getString(SqlUtil.USER_NICKNAME);
				String trueName = res.getString(SqlUtil.USER_TRUENAME);
				String faceImage = res.getString(SqlUtil.USRE_FACEIMAGE);
				String telPhone = res.getString("MobileNum");
				String userName = res.getString("UserName");
				u = new User();
				u.setId(id);
				u.setFaceImage(faceImage);
				u.setTrueName(trueName);
				u.setMobileNum(telPhone);
				u.setUserName(userName);
				u.setNickName(NickName);
				return u;
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

	/*
	 * 删除用户信息
	 */
	public int deleteUserById(int id) {
		return 0;
	}

	/*
	 * 修改用户信息密码
	 */
	public int updataPasswordById(int id, String md5Pwd) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("update JCP_User set PassWord='"
					+ md5Pwd + "' where Id=" + id);
			return isSuccess;
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

	/*
	 * 修改用户信息
	 */
	public int updataUserById(int id, User user) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			return sta.executeUpdate("update JCP_User set NickName='"
					+ user.getNickName() + "',TrueName='" + user.getTrueName()
					+ "',Sex='" + user.getSex() + "',UserInformation='"
					+ user.getDescript() + "',Birthday='" + user.getBirthday()
					+ "',FK_InvestmentTypeId=" + user.getInvestId()
					+ ",ProvinceID=" + user.getProvinceId() + ",CiytID="
					+ user.getCityId() + ",AreaID=" + user.getAreaId()
					+ " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/*
	 * 根据Email查询用户信息
	 */
	public User findUserByAccount(String email) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_User where Email='"
					+ email + "'");
			users = getUser(res, -1, -1);
			if (users != null && users.size() > 0) {
				u = users.get(0);
			}
			return u;
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
	
	
	public int delAccountId(int id, int accountType) {
		// 解除用户的第三方账号信息-----accountType 0 (QQ) 1 (微信) 2 (新浪)
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (accountType == 0) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET QQOpenId=NULL"
						+" WHERE Id=" + id);
			} else if (accountType == 1) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET WeiXinId=NULL"
						+  " WHERE Id=" + id);
			} else if (accountType == 2) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET WeiboId=NULL"
						+  " WHERE Id=" + id);
			}
			return isSuccess;
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
	
	

	public int upDataAccountId(int id, int accountType, String accountId) {
		// 修改用户的第三方账号信息-----accountType 0 (QQ) 1 (微信) 2 (新浪)
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (accountType == 0) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET QQOpenId='"
						+ accountId + "' WHERE Id=" + id);
			} else if (accountType == 1) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET WeiXinId='"
						+ accountId + "' WHERE Id=" + id);
			} else if (accountType == 2) {
				isSuccess = sta.executeUpdate("UPDATE JCP_User SET WeiboId='"
						+ accountId + "' WHERE Id=" + id);
			}
			return isSuccess;
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

	public User otherAccountLogin(int accountType, String accountId) {
		// 第三方账号登录 -----accountType 0 (QQ) 1 (微信) 2 (新浪)
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			if (accountType == 0) {
				res = sta
						.executeQuery("SELECT * FROM JCP_User WHERE QQOpenId='"
								+ accountId + "'");
			} else if (accountType == 1) {
				res = sta
						.executeQuery("SELECT * FROM JCP_User WHERE WeiXinId='"
								+ accountId + "'");
			} else if (accountType == 2) {
				res = sta.executeQuery("SELECT * FROM JCP_User WHERE WeiboId='"
						+ accountId + "'");
			}
			users = getUser(res, -1, -1);
			if (users != null && users.size() > 0) {
				u = users.get(0);
			}
			return u;
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

	public User querryOtherAccount(int id) {
		// 查询第三方账号
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT ISNULL(QQOpenId,'') QQOpenId,ISNULL(WeiXinId,'') WeiXinId,ISNULL(WeiboId,'') WeiboId FROM JCP_User WHERE Id="
							+ id);
			while (res.next()) {
				String QQOpenId = res.getString(SqlUtil.USER_QQID);
				String WeiXinId = res.getString(SqlUtil.USER_WEIXINID);
				String WeiboId = res.getString(SqlUtil.USER_SINAID);
				User user = new User();
				user.setQqId(QQOpenId);
				user.setWeiXinId(WeiXinId);
				user.setWeiBoId(WeiboId);
				return user;
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

	/*
	 * 根据手机号查询用户信息
	 */
	public User findUserByTelPhone(String telPhone) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_User where MobileNum='"
					+ telPhone + "' AND State<>2");
			users = getUser(res, -1, -1);
			if (users != null && users.size() > 0) {
				u = users.get(0);
			}
			return u;
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
	 * 根据qqId 查询用户信息
	 */
	public User findUserByQqopenId(String qqId) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select Id from JCP_User where QQOpenId='"
					+ qqId + "'");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				u = new User();
				u.setId(id);
				return u;
			}
			return u;
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
	 * 根据微信Id 查询用户信息
	 */
	public User findUserByWeixinId(String wenxinId) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_User where WeiXinId='"
					+ wenxinId + "'");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				u = new User();
				u.setId(id);
				return u;
			}
			return u;
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
	 * 根据新浪Id 查询用户信息
	 */
	public User findUserBySinaId(String sinaId) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_User where WeiboId='"
					+ sinaId + "'");
			while (res.next()) {
				int id = res.getInt(SqlUtil.NEWS_ID);
				u = new User();
				u.setId(id);
				return u;
			}
			return u;
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
	 * 根据手机号和Email查询用户信息 ----登录
	 */
	public User findUserByOther(String account) {
		User u = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_User where MobileNum='"
					+ account + "' or  Email= '" + account + "'");
			users = getUser(res, -1, -1);
			if (users != null && users.size() > 0) {
				u = users.get(0);
			}
			return u;
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
	 * 获取登录成功后的信息
	 */
	public User findLoginInfoById(int id) {
		User user = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TrueName,IsRoomAdmin,IsSysAdmin,RegisterDate FROM JCP_User WHERE Id="
							+ id);
			while (res.next()) {
				String trueName = res.getString(1);
				int isRoom = res.getInt(2);
				int isSys = res.getInt(3);
				String reginDate = res.getString(4);
				user = new User();
				user.setTrueName(trueName);
				user.setRegDate(reginDate);
				user.setIsRoomAdmin(isRoom);
				user.setIsSysAdmin(isSys);
				user.setId(id);
				return user;
			}
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

	/**
	 * @param res
	 * @return 获取用户信息
	 */
	public List<User> getUser(ResultSet res, int page, int totlePage) {
		users = new ArrayList<User>();
		try {
			while (res.next()) {
				String password = res.getString(SqlUtil.USER_PASSWORD);
				String birthday = res.getString(SqlUtil.USER_BIRTH);
				int userId = res.getInt(SqlUtil.NEWS_ID);
				String sex = res.getString(SqlUtil.USER_SEX);
				String trueName = res.getString(SqlUtil.USER_TRUENAME);
				String nickName = res.getString(SqlUtil.USER_NICKNAME);
				String mobileNum = res.getString(SqlUtil.USER_MOBILE);
				String faceImage = res.getString(SqlUtil.USRE_FACEIMAGE);
				String email = res.getString(SqlUtil.USER_EMAIL);
				String descript = res.getString(SqlUtil.USER_BODYS);
				int localProvince = res.getInt(SqlUtil.USER_LOCALPROVINCE);
				int localCity = res.getInt(SqlUtil.USER_LOCALCITY);
				int localArea = res.getInt(SqlUtil.USER_LOCALAREA);
				String RegDate = res.getString(SqlUtil.USER_REGINDATE);
				int issysAdmin = res.getInt("IsSysAdmin");
				int isRoomAdmin = res.getInt("IsRoomAdmin");
				int allIntegral = res.getInt("AllIntegral");
				User user = new User();
				user.setId(userId);
				user.setIsRoomAdmin(isRoomAdmin);
				user.setAllIntegral(allIntegral);
				user.setIsSysAdmin(issysAdmin);
				user.setRegDate(RegDate);
				user.setPassword(password);
				user.setBirthday(birthday);
				user.setSex(sex);
				user.setTrueName(trueName);
				user.setNickName(nickName);
				user.setMobileNum(mobileNum);
				user.setFaceImage(faceImage);
				user.setEmail(email);
				user.setDescript(descript);
				user.setProvinceId(localProvince);
				user.setCityId(localCity);
				user.setAreaId(localArea);
				user.setTotlePage(totlePage);
				user.setPage(page);
				users.add(user);
			}
			return users;
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
	public int updateIntegeral(int integeral, int uId) {
		// 修改用户积分信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ integeral + " WHERE Id=" + uId);
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
	public int updateUserLeavel(int uId, int leavel) {
		// 修改用户等级信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + leavel);
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
	public User querryIntegeralByUid(int uId) {
		// 获取积分 等级信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT UserLevel,AllIntegral FROM JCP_User WHERE Id="
							+ uId);
			while (res.next()) {
				int leavel = res.getInt(1);
				int integeral = res.getInt(2);
				User user = new User();
				user.setAllIntegral(integeral);
				user.setUserLeval(leavel);
				return user;
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
	public User findUserByInvestCode(String investCode) {
		// 根据邀请码获取用户信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,AllIntegral, FROM JCP_User WHERE InvitationCode='"
							+ investCode + "'");
			res.next();
			int id = res.getInt(1);
			int integeral = res.getInt(2);
			User user = new User();
			user.setId(id);
			user.setAllIntegral(integeral);
			return user;
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
	public User findBaseInfoById(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT NickName,UserLevel,ISNULL(UserFace,'') UserFace,UserName,MobileNum,LoginTime,AllIntegral,InvitationCode,IsMobileVerification,PassWord FROM JCP_User WHERE Id="
							+ id);
			while (res.next()) {
				String nickName = res.getString(1);
				int leavel = res.getInt(2);
				String userFace = res.getString(3);
				String userName = res.getString(4);
				String telPhone = res.getString(5);
				int loginNum = res.getInt(6);
				int integeral = res.getInt(7);
				String investCode = res.getString(8);
				int isCheckMobile=res.getInt(9);
				String pwd=res.getString(10);
				User user = new User();
				user.setId(id);
				user.setLoginNum(loginNum);
				user.setUserLeval(leavel);
				user.setFaceImage(userFace);
				user.setNickName(nickName);
				user.setInvitationCode(investCode);
				user.setUserName(userName);
				user.setIsChechMobile(isCheckMobile);
				user.setMobileNum(telPhone);
				user.setPassword(pwd);
				user.setAllIntegral(integeral);
				return user;
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
	public User findUserChatInfo(int uId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT NickName,BuyProductId,IsSysAdmin,IsRoomAdmin,"
							+ "FK_RoomTearchId,Isteacher,IsRoomManager,UserLevel,TrueName,UserName,"
							+ "ServerId FROM JCP_User WHERE Id=" + uId);
			while (res.next()) {
				String nickName = res.getString(1);
				int proId = res.getInt(2);
				int isSysAdmin = res.getInt(3);
				int isRoom = res.getInt(4);
				int fk_roomId = res.getInt(5);
				int isTeacher = res.getInt(6);
				int isChatMess = res.getInt(7);
				int userLeavel = res.getInt(8);
				String trueName=res.getString(9);
				String UserName=res.getString(10);
				User user = new User();
				user.setNickName(nickName);
				user.setIsRoomAdmin(isRoom);
				user.setIsRoomManager(isChatMess);
				user.setUserLeval(userLeavel);
				user.setBuyProductId(proId);
				user.setIsSysAdmin(isSysAdmin);
				user.setFk_roomTeacherId(fk_roomId);
				user.setIsTeacher(isTeacher);
				user.setUserName(UserName);
				user.setTrueName(trueName);
				return user;
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
	public int updateLoginNum(int num, int uId, String ip) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_User SET LoginTime=" + num
					+ ",LastLoginIp='" + ip + "' WHERE Id=" + uId);
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
	public User findUserIsCheck(int userId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT IsEmailVerification,IsMobileVerification,IsTxtTearcher,IsVideoTearcher,Isteacher FROM JCP_User WHERE Id="
							+ userId);
			while (res.next()) {
				int isEmailCheck = res.getInt(1);
				int isMobileCheck = res.getInt(2);
				int isTxtTeacher = res.getInt(3);
				int isVideoTeacher = res.getInt(4);
				int isTeacher = res.getInt(5);
				User user = new User();
				user.setIsChechEmail(isEmailCheck);
				user.setIsChechMobile(isMobileCheck);
				user.setIsVideoTeacher(isVideoTeacher);
				user.setIsTxtTeacher(isTxtTeacher);
				user.setIsTeacher(isTeacher);
				return user;
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
	public User findUserByUserNameOrMobile(String uName) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id FROM JCP_User WHERE UserName='"
							+ uName+"' OR MobileNum='"+uName+"'");
			while (res.next()) {
				int id = res.getInt(1);
				User user = new User();
				user.setId(id);
				return user;
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

}
