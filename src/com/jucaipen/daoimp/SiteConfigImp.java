package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.SiteConfigDao;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.utils.JdbcUtil;

public class SiteConfigImp implements SiteConfigDao {

	private SiteConfig config;
	private Connection dbConn;
	private Statement state;
	private ResultSet res;

	public SiteConfig findSiteConfig() {
		// 获取配置信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			state = dbConn.createStatement();
			res = state.executeQuery("SELECT * FROM JCP_Config");
			res.next();
			int askNum = res.getInt("AskNum");
			int commType = res.getInt("CommType");
			int newsMom=res.getInt("NewsMoM");
			int videoMom=res.getInt("VideoMoM");
			int recommIntegeral = res.getInt("RecommendIntegral");
			int commIntegeral = res.getInt("UserCommIntegral");
			int signIntegeral = res.getInt("QianDaoIntegral");
			int regIntegeral = res.getInt("RegIntegral");
			double subRate = res.getDouble("SubscriptionRatio");
			double liveRate = res.getDouble("TearchLiveRebate");
			double answerRate = res.getDouble("TearchAnswerRebate");
			double givenRate = res.getDouble("UserGiveRebate");
			double isShouhu = res.getDouble("UserIsShouhu");
			int liveDetailCount=res.getInt("LiveDetailCount");
			config = new SiteConfig();
			config.setAskNum(askNum);
			config.setCommType(commType);
			config.setNewsMom(newsMom);
			config.setVideoMom(videoMom);
			config.setRecommIntegeral(recommIntegeral);
			config.setSignIntegeral(signIntegeral);
			config.setCommIntegeral(commIntegeral);
			config.setRecommIntegeral(recommIntegeral);
			config.setRegIntegeral(regIntegeral);
			config.setSubRat(subRate);
			config.setLiveDetailCount(liveDetailCount);
			config.setTeacherLiveRebat(liveRate);
			config.setTeacherAnswerRebat(answerRate);
			config.setUserGiveRebat(givenRate);
			config.setUserIsShouhu(isShouhu);
			return config;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(state, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
