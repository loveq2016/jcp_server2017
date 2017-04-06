package com.jucaipen.main.datautils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.AnswerSale;
import com.jucaipen.model.Ask;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.IdeaSale;
import com.jucaipen.model.LiveDetailSale;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.Marker;
import com.jucaipen.model.MyGifts;
import com.jucaipen.model.MyPresent;
import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.RebateIntegeralDetail;
import com.jucaipen.model.SaleRecoder;
import com.jucaipen.model.Sign;
import com.jucaipen.model.SignDetail;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.TacticsSale;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.User;
import com.jucaipen.model.VideoLiveSale;
import com.jucaipen.service.AccountDetailSer;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.ChargeOrderSer;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.MarkerSer;
import com.jucaipen.service.MyGiftsSer;
import com.jucaipen.service.MyPresentSer;
import com.jucaipen.service.RebateSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.SysDetailAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.BaseData;
import com.jucaipen.utils.JdbcUtil;
public class RollBackUtil {
	private static RollBackUtil util;

	private RollBackUtil() {

	}

	public static RollBackUtil getInstance() {
		if (util == null) {
			util = new RollBackUtil();
		}
		return util;
	}

	/**
	 * 开通守护
	 * 
	 * @param guardian
	 * @param rebate
	 * @param account
	 * @param b
	 * @param detail
	 * @param detailIntegeral
	 * @param uId
	 * @param user
	 * @param account2
	 * @param detailAccount
	 * @param contribute
	 * @param teacher
	 * @param sysRebate
	 * @param gurdianId
	 *            开通守护
	 */
	public int synchrGuardian(Guardian guardian, Rebate rebate,
			Account account, int b, AccountDetail detail,
			AccountDetail detailIntegeral, int uId, User user,
			SysAccount account2, SysDetailAccount detailAccount,
			Contribute contribute, FamousTeacher teacher, Rebate sysRebate,
			int gurdianId, int type, LiveRecoderSale sale) {
		Connection dbConn = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			if (type == 1) {
				// 购买单次直播
				// 1、更新单次购买表
				sta.executeUpdate("INSERT INTO JCP_VideoLive_RecordSale("
						+ "LiveCodeId,TeacherId,InsertDate,PayJCB,UserId,Remark) VALUES("
						+ sale.getLiveRecoderId() + "," + sale.getTeacherId()
						+ ",'" + sale.getPurchDate() + "',"
						+ sale.getPayBills() + "," + sale.getUserId() + ",'"
						+ sale.getRemark() + "')");
			} else {
				// 开通守护
				// 1 更新守护 JCP_ShouHuZhe
				if (gurdianId > 0) {
					// 续约
					sta.executeUpdate("UPDATE JCP_ShouHuZhe SET EndDate='"
							+ guardian.getEndDate() + "',State=0 WHERE Id="
							+ gurdianId);
				} else {
					// 签约
					sta.executeUpdate("INSERT INTO JCP_ShouHuZhe(FK_UserId,FK_TearchId,InsertDate,Ip,StartDate,EndDate,State) VALUES ("
							+ guardian.getUserId()
							+ ","
							+ guardian.getTeacherId()
							+ ",'"
							+ guardian.getInsertDate()
							+ "','"
							+ guardian.getIp()
							+ "','"
							+ guardian.getStartDate()
							+ "','"
							+ guardian.getEndDate()
							+ "',"
							+ guardian.getState() + ")");
				}

			}
			// 2、 返利 积分 JCP_Rebate 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			// 3 、总账户 聚财币减少 JCP_Account 积分增加
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (account.getIntegeral() + b) + ",JucaiBi="
					+ (account.getJucaiBills() - b) + " WHERE UserId=" + uId);
			// 4、 账户详细 聚财币变化 JCP_Account_Detail
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");

			// 积分
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailIntegeral.getOrderCode() + "','"
					+ detailIntegeral.getDetailMoney() + "',"
					+ detailIntegeral.getDetailType() + ","
					+ detailIntegeral.getState() + ",'"
					+ detailIntegeral.getRemark() + "','"
					+ detailIntegeral.getInsertDate() + "'," + 0 + ","
					+ detailIntegeral.getUserId() + ")");
			// 5、 用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (user.getAllIntegral() + b) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral() + b);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			// 6、系统账户 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (account2.getSysChildAccount() + b) + ",UserAccount="
					+ (account2.getUserAccount() - b) + ",OpenShAccount="
					+ (account2.getOpenGurdianAccount() + b));
			// 7、 系统账户详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detailAccount.getUserId() + ","
					+ detailAccount.getType() + ","
					+ detailAccount.getRecoderType() + ","
					+ detailAccount.getOrderId() + ","
					+ detailAccount.getPrice() + ",'"
					+ detailAccount.getInsertDate() + "','"
					+ detailAccount.getRemark() + "','" + detailAccount.getIp()
					+ "'," + detailAccount.getIsDel() + ")");
			// 8 、贡献 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES ("
					+ contribute.getUserId() + "," + contribute.getTeacherId()
					+ "," + contribute.getFk_id() + ",'"
					+ contribute.getInsertDate() + "',"
					+ contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 9、讲师 签约人数
			sta.executeUpdate("UPDATE JCP_Tearcher SET QianYueCount="
					+ (teacher.getSignCount() + 1) + " WHERE Id="
					+ teacher.getId());
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param ip
	 * @param payDate
	 * @param pState
	 * @param orderCode
	 * @param bills
	 * @param a
	 * @param uId
	 * @param detail
	 * @param account2
	 * @param detailAccount
	 * @param type
	 * @return 充值 --------------------NO
	 */
	public int recharge(String orderCode, int pState, String payDate,
			String ip, int bills, Account a, int uId, AccountDetail detail,
			SysAccount account2, SysDetailAccount detailAccount, int type,
			String prePayDate, List<String> orderCodes) {
		int update = 0;
		Connection dbConn = null;
		Statement sta=null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			dbConn.setAutoCommit(false);
			sta = dbConn.createStatement();
			// 1 充值 JCP_AddOrder
			if (pState == 2) {
				update = sta
						.executeUpdate("UPDATE JCP_AddOrder SET OrderState="
								+ pState + ",PaymentDate='" + payDate
								+ "',IP='" + ip + "',PaymentMethod=" + type
								+ ",InsertDate='" + prePayDate
								+ "'  WHERE OrderCode='" + orderCode + "'");
				if (update > 0) {
					orderCodes.add(orderCode);
				}
			} else {
				sta.executeUpdate("UPDATE JCP_AddOrder SET OrderState="
						+ pState + ",IP='" + ip + "',PaymentMethod=" + type
						+ ",InsertDate='" + prePayDate + "' WHERE OrderCode='"
						+ orderCode + "'");
			}
			if (pState == 2 && update > 0) {
				// 2 、总账户 聚财币减少 JCP_Account
				if (a != null) {
					sta.executeUpdate("UPDATE JCP_Account SET JucaiBi="
							+ (a.getJucaiBills() + bills) + " WHERE UserId="
							+ uId);
				} else {
					Account account = new Account();
					account.setIntegeral(0);
					account.setJucaiBills(bills);
					account.setUserId(uId);
					sta.executeUpdate("INSERT INTO JCP_Account(UserId,Integral,JucaiBi) VALUES("
							+ account.getUserId()
							+ ","
							+ account.getIntegeral()
							+ ","
							+ account.getJucaiBills() + ")");
				}
				// 3、 账户详细 聚财币变化 JCP_Account_Detail
				sta.executeUpdate("INSERT INTO JCP_Account_Detail"
						+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
						+ "InsertDate,IsDel,UserId) VALUES ('"
						+ detail.getOrderCode() + "','"
						+ detail.getDetailMoney() + "',"
						+ detail.getDetailType() + "," + detail.getState()
						+ ",'" + detail.getRemark() + "','"
						+ detail.getInsertDate() + "'," + 0 + ","
						+ detail.getUserId() + ")");
				// 4、系统账户 JCP_SysAccount
				sta.executeUpdate("UPDATE JCP_SysAccount SET SysAccount="
						+ (account2.getSysAccount() + bills)
						+ ",SysChildAccount="
						+ (account2.getSysChildAccount())
						+ ",UserAccount="
						+ (account2.getSysAccount() + bills - account2
								.getSysChildAccount()));
				// 5、 系统账户详细 JCP_SysAccountDateil
				sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
						+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
						+ " VALUES(" + detailAccount.getUserId() + ","
						+ detailAccount.getType() + ","
						+ detailAccount.getRecoderType() + ","
						+ detailAccount.getOrderId() + ","
						+ detailAccount.getPrice() + ",'"
						+ detailAccount.getInsertDate() + "','"
						+ detailAccount.getRemark() + "','"
						+ detailAccount.getIp() + "',"
						+ detailAccount.getIsDel() + ")");
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				sta.close();
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param ip
	 * @param payDate
	 * @param pState
	 * @param orderCode
	 * @param bills
	 * @param a
	 * @param uId
	 * @param detail
	 * @param account2
	 * @param detailAccount
	 * @param type
	 * @return 充值 --------------------NO
	 */
	public int rechargeNoRollBack(String orderCode, int pState, String payDate,
			String ip, int bills, Account a, int uId, AccountDetail detail,
			SysAccount account2, SysDetailAccount detailAccount, int type,
			String prePayDate) {
		int updateBills = 0;
			// 1 充值 JCP_AddOrder
			if (pState == 2) {
				int update = ChargeOrderSer.updatePayState(orderCode, pState,
						prePayDate, ip, prePayDate, type);
				if (update > 0) {
					// 2 、总账户 聚财币减少 JCP_Account
					if (a != null) {
						updateBills = AccountSer.updateBills(uId,
								(a.getJucaiBills() + bills));
					} else {
						Account account = new Account();
						account.setIntegeral(0);
						account.setJucaiBills(bills);
						account.setUserId(uId);
						updateBills = AccountSer.addAccount(account);
					}
					// 3、 账户详细 聚财币变化 JCP_Account_Detail
					AccountDetail accountDetail = AccountDetailSer
							.findDetailByOrderCode(orderCode);
					if (accountDetail == null && updateBills > 0) {
						int addDetails = AccountDetailSer.addDetails(detail);
						// 4、系统账户 JCP_SysAccount
						if (addDetails > 0) {
							int purchInfo = SysAccountSer
									.updatePurchInfo(
											(account2.getSysAccount() + bills),
											(account2.getSysChildAccount()),
											(account2.getSysAccount() + bills - account2
													.getSysChildAccount()));
							// 5、 系统账户详细 JCP_SysAccountDateil
							if (purchInfo > 0) {
								SysDetailAccountSer.addDetails(detailAccount);
							}
						}
					}

				}
		} 
		return 0;
	}

	/**
	 * @param marker
	 * @param detail
	 * @param uId
	 * @param jucaiBills
	 * @param markerMoney
	 * @param integeral
	 * @param contribute
	 * @param sysAccount
	 * @param sysDetail
	 * @param user
	 * @param sysRebate
	 * @param rebate
	 * @param detailIntegeral
	 * @return 打赏
	 */
	public int addReward(Marker marker, AccountDetail detail, int integeral,
			int markerMoney, int jucaiBills, int uId, Contribute contribute,
			SysAccount sysAccount, SysDetailAccount sysDetail, User user,
			Rebate rebate, Rebate sysRebate, AccountDetail detailIntegeral) {
		Connection dbConn=null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加打赏信息 JCP_MarkerWord_Dateil
			sta.executeUpdate("INSERT INTO JCP_MarkerWord_Dateil(MarkerType,FK_UserId,FK_LogId,MaekerCount,InsertDate,IP) VALUES("
					+ marker.getType()
					+ ","
					+ marker.getUserId()
					+ ","
					+ marker.getIdeaId()
					+ ","
					+ marker.getMarkerCount()
					+ ",'"
					+ marker.getInsertDate() + "','" + marker.getIp() + "')");
			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (integeral + markerMoney) + ",JucaiBi="
					+ (jucaiBills - markerMoney) + " WHERE UserId=" + uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailIntegeral.getOrderCode() + "','"
					+ detailIntegeral.getDetailMoney() + "',"
					+ detailIntegeral.getDetailType() + ","
					+ detailIntegeral.getState() + ",'"
					+ detailIntegeral.getRemark() + "','"
					+ detailIntegeral.getInsertDate() + "'," + 0 + ","
					+ detailIntegeral.getUserId() + ")");

			// 4、返利 JCP_Rebate 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			// 5、贡献 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES ("
					+ contribute.getUserId() + "," + contribute.getTeacherId()
					+ "," + contribute.getFk_id() + ",'"
					+ contribute.getInsertDate() + "',"
					+ contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + markerMoney)
					+ ",UserAccount="
					+ (sysAccount.getUserAccount() - markerMoney)
					+ ",DaShangAccount="
					+ (sysAccount.getDaShangAccount() + markerMoney));

			// 7、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detail.getUserId() + ","
					+ sysDetail.getType() + "," + sysDetail.getRecoderType()
					+ "," + sysDetail.getOrderId() + "," + sysDetail.getPrice()
					+ ",'" + sysDetail.getInsertDate() + "','"
					+ sysDetail.getRemark() + "','" + sysDetail.getIp() + "',"
					+ sysDetail.getIsDel() + ")");

			// 8、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (integeral + markerMoney) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral()
					+ markerMoney);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param marker
	 * @param detail
	 * @param uId
	 * @param jucaiBills
	 * @param markerMoney
	 * @param integeral
	 * @param contribute
	 * @param sysAccount
	 * @param sysDetail
	 * @param user
	 * @param sysRebate
	 * @param rebate
	 * @param detailIntegeral
	 * @return 打赏
	 */
	public int addRewardNoCommit(Marker marker, AccountDetail detail,
			int integeral, int markerMoney, int jucaiBills, int uId,
			Contribute contribute, SysAccount sysAccount,
			SysDetailAccount sysDetail, User user, Rebate rebate,
			Rebate sysRebate, AccountDetail detailIntegeral) {
		// 1、添加打赏信息 JCP_MarkerWord_Dateil
		int addMarker = MarkerSer.addMarker(marker);
		if (addMarker == 1) {
			// 2、总账户信息 积分聚财币 JCP_Account
			int billsIntegers = AccountSer.updateBillsIntegers(uId,
					(jucaiBills - markerMoney), (integeral + markerMoney));
			if (billsIntegers == 1) {
				// 3、账户详细信息 JCP_Account_Detail 聚财币减少
				int addDetails = AccountDetailSer.addDetails(detail);
				if (addDetails == 1) {
					// 积分增加
					int addDetails2 = AccountDetailSer
							.addDetails(detailIntegeral);
					if (addDetails2 == 1) {
						// 4、返利 JCP_Rebate 系统返利
						int addRebate = RebateSer.addRebate(rebate);
						if (addRebate == 1) {
							// 讲师返利
							int addRebate2 = RebateSer.addRebate(sysRebate);
							if (addRebate2 == 1) {
								// 5、贡献 JCP_Contribute
								int addContribute = ContributeSer
										.addContribute(contribute);
								if (addContribute == 1) {
									// 6、系统总表 JCP_SysAccount
									int updateDaShangInfo = SysAccountSer
											.updateDaShangInfo(
													(sysAccount
															.getSysChildAccount() + markerMoney),
													(sysAccount
															.getUserAccount() - markerMoney),
													(sysAccount
															.getDaShangAccount() + markerMoney));
									if (updateDaShangInfo == 1) {
										// 7、系统明细表 JCP_SysAccountDateil
										int addDetails3 = SysDetailAccountSer
												.addDetails(sysDetail);
										if (addDetails3 == 1) {
											// 8、用户表 积分 JCP_User
											int updateIntegeral = UserServer
													.updateIntegeral(
															uId,
															(integeral + markerMoney));
											if (updateIntegeral == 1) {
												// 更新用户等级
												int currentLeavel = user
														.getUserLeval();
												int newLeavel = BaseData
														.getLeavel(user
																.getAllIntegral()
																+ markerMoney);
												if (currentLeavel < newLeavel) {
													UserServer
															.updateUserLeavel(
																	uId,
																	newLeavel);
												}
												return 1;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * @param sale
	 * @param account
	 * @param uId
	 * @param bills
	 * @param detailInteger
	 * @param detail
	 * @param sysRebate
	 * @param teacherRebate
	 * @param contribute
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @param user
	 * @param saleId
	 * @return 订阅战法
	 */
	public int orderTactisc(TacticsSale sale, Account account, int bills,
			int uId, AccountDetail detail, AccountDetail detailInteger,
			Rebate teacherRebate, Rebate sysRebate, Contribute contribute,
			SysAccount sysAccount, SysDetailAccount sysDetailAccount, User user) {
		 Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加战法订阅信息 JCP_TacticsSale
			sta.executeUpdate("INSERT INTO  JCP_TacticsSale(UserId,"
					+ "TacticsId,MobileNum,InsetDate,StratDate,EndDate,IsStop,IP,Remarks) VALUES("
					+ sale.getUserId() + "," + sale.getTacticsId() + ",'"
					+ sale.getTelPhone() + "','" + sale.getInsertDate() + "','"
					+ sale.getStartDate() + "','" + sale.getEndDate() + "',"
					+ sale.getIsStop() + ",'" + sale.getIp() + "','"
					+ sale.getRemark() + "')");
			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (account.getIntegeral() + bills) + ",JucaiBi="
					+ (account.getJucaiBills() - bills) + " WHERE UserId="
					+ uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + ","
					+ detailInteger.getUserId() + ")");

			// 4、返利 JCP_Rebate 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ teacherRebate.getTeacherId()
					+ ","
					+ teacherRebate.getType()
					+ ",'"
					+ teacherRebate.getRebateMoney()
					+ "',"
					+ teacherRebate.getFromId()
					+ ",'"
					+ teacherRebate.getInsertDate()
					+ "','"
					+ teacherRebate.getRemark() + "')");

			// 5、贡献 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES ("
					+ contribute.getUserId() + "," + contribute.getTeacherId()
					+ "," + contribute.getFk_id() + ",'"
					+ contribute.getInsertDate() + "',"
					+ contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + bills)
					+ ",UserAccount=" + (sysAccount.getUserAccount() - bills)
					+ ",TacticsAccount="
					+ (sysAccount.getTactivsAccount() + bills));

			// 7、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detail.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");

			// 8、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (account.getIntegeral() + bills) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral() + bills);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param myVideo
	 * @param a
	 * @param uId
	 * @param b
	 * @param detail
	 * @param detailInteger
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @param user
	 * @param contribute
	 * @return 购买视频
	 */
	public int purchVideo(MyVideo myVideo, Account a, int uId, int b,
			AccountDetail detail, AccountDetail detailInteger,
			SysAccount sysAccount, SysDetailAccount sysDetailAccount, User user) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加添加我的视频信息 JCP_MyVideo
			sta.executeUpdate("INSERT INTO JCP_MyVideo"
					+ "(FK_UserId,FK_VideoId,InsertDate,Remark,IsDel,IsStop,StartDate,EndDate)"
					+ " VALUES(" + uId + "," + myVideo.getVideoId() + ",'"
					+ myVideo.getInsertDate() + "','" + myVideo.getRemark()
					+ "'," + myVideo.getIsDel() + "," + myVideo.getIsStop()
					+ ",'" + myVideo.getStartDate() + "','"
					+ myVideo.getEndDate() + "')");

			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (a.getIntegeral() + b) + ",JucaiBi="
					+ (a.getJucaiBills() - b) + " WHERE UserId=" + uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + ","
					+ detailInteger.getUserId() + ")");

			// 4、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TacticsAccount="
					+ (sysAccount.getPayVideoAccount() + b));

			// 5、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detail.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");

			// 6、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (a.getIntegeral() + b) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral() + b);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
	/**
	 * @param a
	 * @param uId
	 * @param user
	 * @param sysAccount
	 * @param bs
	 * @param detail
	 * @param detailInteger
	 * @param sysDetailAccount
	 * @param sale
	 * @param rebate
	 * @return 购买问答
	 */
	public int purchQuestion(Account a, int uId, User user,
			SysAccount sysAccount, int bs, AccountDetail detail,
			AccountDetail detailInteger, SysDetailAccount sysDetailAccount,
			AnswerSale sale, Rebate rebate) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加问答购买信息 JCP_AnswerSale
			sta.executeUpdate("INSERT INTO JCP_AnswerSale (FK_UserId,FK_TearchId,OrderCode,FK_AskId,InsertDate) VALUES ("
					+ sale.getUserId()
					+ ","
					+ sale.getTeacherId()
					+ ",'"
					+ sale.getOrderCode()
					+ "',"
					+ sale.getAskId()
					+ ",'"
					+ sale.getInsetDate() + "')");

			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (a.getIntegeral() + bs) + ",JucaiBi="
					+ (a.getJucaiBills() - bs) + " WHERE UserId=" + uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + "," + uId
					+ ")");

			// 4、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + bs) + ",UserAccount="
					+ (sysAccount.getUserAccount() - bs) + ",TacticsAccount="
					+ (sysAccount.getTactivsAccount() + bs));

			// 5、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + uId + "," + sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");

			// 6、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (a.getIntegeral() + bs) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(a.getIntegeral() + bs);
			if (currentLeavel != newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			// 7、返现 JCP_Rebate 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	/**
	 * @param present
	 * @param b
	 * @param a
	 * @param uId
	 * @param detail
	 * @param detailInteger
	 * @param contribute
	 * @param user
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @return 购买礼品
	 */
	public int purchGifts(MyPresent presentExit, MyPresent present, Account a,
			int b, int uId, AccountDetail detail, AccountDetail detailInteger,
			User user, SysAccount sysAccount, SysDetailAccount sysDetailAccount) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加添加礼品信息 JCP_MyPresent
			if (presentExit == null) {
				sta.executeUpdate("INSERT INTO JCP_MyPresent (FK_UserId,PresentNum,FK_LiPinId) VALUES("
						+ present.getUserId()
						+ ","
						+ present.getPresentNum()
						+ "," + present.getPresentId() + ")");
			} else {
				sta.executeUpdate("UPDATE JCP_MyPresent SET PresentNum="
						+ (presentExit.getPresentNum() + present
								.getPresentNum()) + " WHERE FK_LiPinId="
						+ presentExit.getPresentId());
			}
			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (a.getIntegeral() + b) + ",JucaiBi="
					+ (a.getJucaiBills() - b) + " WHERE UserId=" + uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + ","
					+ detailInteger.getUserId() + ")");

			// 4、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",GiftAccount="
					+ (sysAccount.getGiftAccount() + b));

			// 5、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detail.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");

			// 6、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (a.getIntegeral() + b) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral() + b);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


	/**
	 * @param num
	 * @param present
	 * @param account
	 * @param bill
	 * @param uId
	 * @param sysAccount
	 * @param rebate
	 * @param sysRebate
	 * @param contribute
	 * @param gifts
	 * @return 赠送礼品
	 */
	public int sendGifts(MyPresent present, int num, int bill, int uId,
			SysAccount sysAccount, Rebate sysRebate, Rebate rebate,
			Contribute contribute, MyGifts gifts) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、更新礼品背包 JCP_MyPresent
			sta.executeUpdate("UPDATE JCP_MyPresent SET PresentNum="
					+ (present.getPresentNum() - num) + " WHERE FK_UserId="
					+ uId + " AND FK_LiPinId=" + present.getPresentId());

			// 2、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET TeacherAccount="
					+ (sysAccount.getTeacherRebateAccount() + bill)
					+ ",SysRebateAccount="
					+ (sysAccount.getSysRebateAccount() + bill));

			// 3 返利表
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			// 讲师返利

			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");

			// 4贡献表
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES("
					+ uId + "," + contribute.getTeacherId() + ","
					+ contribute.getFk_id() + ",'" + contribute.getInsertDate()
					+ "'," + contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");

			// 5 送出礼品记录
			sta.executeUpdate("INSERT INTO JCP_MyLiPin (FK_SendUserId,FK_ReceiveUserId,FK_LiPinId,InsertDate,SortId,LiPinNum,ReMark) VALUES ("
					+ gifts.getSenderId()
					+ ","
					+ gifts.getReceiverId()
					+ ","
					+ gifts.getGiftId()
					+ ",'"
					+ gifts.getInsertDate()
					+ "',"
					+ gifts.getSortId()
					+ ","
					+ gifts.getGiftNum()
					+ ",'"
					+ gifts.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	
	/**
	 * @param present
	 * @param b
	 * @param a
	 * @param uId
	 * @param detail
	 * @param detailInteger
	 * @param contribute
	 * @param user
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @param sysRebate 
	 * @param rebate 
	 * @param contribute 
	 * @param gifts 
	 * @return 购买礼品+赠送礼品
	 */
	public int purchGiftsNoCommit(MyPresent presentExit, MyPresent present, Account a,
			int b, int uId, AccountDetail detail, AccountDetail detailInteger,
			User user, SysAccount sysAccount, SysDetailAccount sysDetailAccount, MyGifts gifts, Contribute contribute, Rebate rebate, Rebate sysRebate) {
		    int addPresent;
			// 1、添加添加礼品信息 JCP_MyPresent
		    if(presentExit==null){
		    	addPresent = MyPresentSer.addPresent(present);
		    }else{
		    	addPresent= MyPresentSer.updatePresents(uId, presentExit.getPresentId(), presentExit.getPresentNum());
		    }
		    if(addPresent>0){
		    	// 2、总账户信息 积分聚财币 JCP_Account    ----购买
		    	int updateBillsIntegers = AccountSer.updateBillsIntegers(uId, (a.getJucaiBills() - b), (a.getIntegeral() + b));
		    	if(updateBillsIntegers>0){
		    		// 3、账户详细信息 JCP_Account_Detail 聚财币减少 ---购买
					int addDetails = AccountDetailSer.addDetails(detail);
					// 积分增加
					int addDetails2 = AccountDetailSer.addDetails(detailInteger);
		    		if(addDetails>0&&addDetails2>0){
		    			// 4、系统总表 JCP_SysAccount
		    			int updateGiftInfo = SysAccountSer.updateGiftInfo((sysAccount.getSysChildAccount() + b), (sysAccount.getUserAccount() - b), (sysAccount.getGiftAccount() + b));
		    			if(updateGiftInfo>0){
		    				// 5、系统明细表 JCP_SysAccountDateil
		    				int addDetails3 = SysDetailAccountSer.addDetails(sysDetailAccount);
		    				if(addDetails3>0){
		    					// 6、用户表 积分 JCP_User
		    					int updateIntegeral = UserServer.updateIntegeral(uId,(a.getIntegeral() + b));
		    					if(updateIntegeral>0){
		    						// 更新用户等级
			    					int currentLeavel = user.getUserLeval();
			    					int newLeavel = BaseData.getLeavel(user.getAllIntegral() + b);
			    					if (currentLeavel < newLeavel) {
			    						UserServer.updateUserLeavel(uId, newLeavel);
			    					}
			    					//赠送礼品
			    					// 1、更新礼品背包 JCP_MyPresent
			    					int updatePresents = MyPresentSer.updatePresents(uId, present.getPresentId(), (present.getPresentNum() - presentExit.getPresentNum()));
			    					if(updatePresents>0){
			    						// 2、系统总表 JCP_SysAccount
				    					int updateTeacherRebate = SysAccountSer.updateTeacherRebate(sysAccount.getTeacherRebateAccount()+b, sysAccount.getSysRebateAccount()+b);
				    					if(updateTeacherRebate>0){
				    						// 3 返利表
					    					// 系统返利
					    					int addRebate = RebateSer.addRebate(sysRebate);
					    					if(addRebate>0){
					    						// 讲师返利
						    					int addRebate2 = RebateSer.addRebate(rebate);
						    					if(addRebate2>0){
						    						// 4贡献表
							    					int addContribute = ContributeSer.addContribute(contribute);
							    					if(addContribute>0){
							    						// 5 送出礼品记录
								    					return  MyGiftsSer.addGifts(gifts);
							    					}
						    					}
					    					}
				    					}
			    					}
		    					}
		    				}
		    			}
		    		}
		    	}
		    }
		return 0;
	}

	/**
	 * @param mySpecial
	 * @param a
	 * @param uId
	 * @param b
	 * @param detail
	 * @param detailInteger
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @param user
	 * @return 购买专辑
	 */
	public int purchSpecial(MySpecial mySpecial, Account a, int uId, int b,
			AccountDetail detail, AccountDetail detailInteger,
			SysAccount sysAccount, SysDetailAccount sysDetailAccount, User user) {

		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加添加我的专辑信息 JCP_MySpecial
			sta.executeUpdate("INSERT INTO JCP_MySpecial"
					+ "(FK_UserId,FK_SpecialId,InsertDate,Remark,IsDel"
					+ ",IsStop,StartDate,EndDate)" + " VALUES("
					+ mySpecial.getUserId() + "," + mySpecial.getSpecialId()
					+ ",'" + mySpecial.getInsertDate() + "','"
					+ mySpecial.getRemark() + "'," + mySpecial.getIsDel() + ","
					+ mySpecial.getIsStop() + ",'" + mySpecial.getStartDate()
					+ "','" + mySpecial.getEndDate() + "')");

			// 2、总账户信息 积分聚财币 JCP_Account
			sta.executeUpdate("UPDATE JCP_Account SET Integral="
					+ (a.getIntegeral() + b) + ",JucaiBi="
					+ (a.getJucaiBills() - b) + " WHERE UserId=" + uId);
			// 3、账户详细信息 JCP_Account_Detail 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + ","
					+ detailInteger.getUserId() + ")");

			// 4、系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TacticsAccount="
					+ (sysAccount.getPaySpecialAccount() + b));

			// 5、系统明细表 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detail.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");

			// 6、用户表 积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral="
					+ (a.getIntegeral() + b) + " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(user.getAllIntegral() + b);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param sign
	 * @param detail
	 * @param inDetail
	 * @param accountDetail
	 * @param a
	 * @param signIntegeral
	 * @param uId
	 * @param user
	 * @return 签到
	 */
	public int signIn(SignDetail detail, Sign sign,
			RebateIntegeralDetail inDetail, AccountDetail accountDetail,
			Account a, int uId, int signIntegeral, User user) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加添加签到详细表 JCP_QianDao_Detail
			sta.executeUpdate("INSERT INTO JCP_QianDao_Detail(UserId,InsertDate,Ip,Remark) VALUES("
					+ detail.getUserId()
					+ ",'"
					+ detail.getInsertDate()
					+ "','"
					+ detail.getIp()
					+ "','"
					+ detail.getRemark()
					+ "')");

			// 2、更新签到总表 JCP_QianDao
			sta.executeUpdate("UPDATE JCP_QianDao SET LastDate='"
					+ sign.getLastDate() + "',Ip='" + sign.getIp()
					+ "',QDCount=" + sign.getSignNum() + " WHERE UserId="
					+ sign.getUserId());
			// 3、返利积分 JCP_RebateIntegral_Detail
			sta.executeUpdate("INSERT INTO JCP_RebateIntegral_Detail(FK_UserId,IntegralNum,InsertDate,ReMark,Type,FK_FromId) VALUES("
					+ inDetail.getUserId()
					+ ","
					+ inDetail.getIntegralNum()
					+ ",'"
					+ inDetail.getInsertDate()
					+ "','"
					+ inDetail.getRemark()
					+ "',"
					+ inDetail.getType()
					+ ","
					+ inDetail.getFromId() + ")");
			// 更新账户明细表 JCP_Account_Detail
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 4、账户总表 JCP_Account
			if (a != null) {
				int newIntegeral = signIntegeral + a.getIntegeral();
				sta.executeUpdate("UPDATE JCP_Account SET Integral="
						+ newIntegeral + " WHERE UserId=" + uId);
			} else {
				a = new Account();
				a.setIntegeral(signIntegeral);
				a.setJucaiBills(0);
				a.setUserId(uId);
				sta.executeUpdate("INSERT INTO JCP_Account(UserId,Integral,JucaiBi) VALUES("
						+ a.getUserId()
						+ ","
						+ a.getIntegeral()
						+ ","
						+ a.getJucaiBills() + ")");
			}

			// 5、用户表积分 JCP_User
			int newIntegeral = user.getAllIntegral() + signIntegeral;
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param ideaSale
	 * @param accountDetailIntegeral
	 * @param accountDetail
	 * @param uId
	 * @param b
	 * @param acount
	 * @param user
	 * @param contribute
	 * @param sysAccount
	 * @param detailAccount
	 * @param sysRebate
	 * @param rebate
	 * @return 购买名家日志观点
	 */
	public int purchTeacherLogs(IdeaSale ideaSale, AccountDetail accountDetail,
			AccountDetail accountDetailIntegeral, Account acount, int b,
			int uId, User user, Contribute contribute, SysAccount sysAccount,
			SysDetailAccount detailAccount, Rebate rebate, Rebate sysRebate) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加购买日志观点表 JCP_TeacherlogSale
			sta.executeUpdate("INSERT  INTO JCP_TeacherlogSale"
					+ "(FK_UserId,FK_TearchId,OrderCode,FK_LogId,InsertDate) VALUES("
					+ ideaSale.getUserId() + "," + ideaSale.getTeacherId()
					+ ",'" + ideaSale.getOrderCode() + "',"
					+ ideaSale.getLogId() + ",'" + ideaSale.getInsertDate()
					+ "')");
			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetailIntegeral.getOrderCode() + "','"
					+ accountDetailIntegeral.getDetailMoney() + "',"
					+ accountDetailIntegeral.getDetailType() + ","
					+ accountDetailIntegeral.getState() + ",'"
					+ accountDetailIntegeral.getRemark() + "','"
					+ accountDetailIntegeral.getInsertDate() + "'," + 0 + ","
					+ accountDetailIntegeral.getUserId() + ")");
			// 3、账户总表 JCP_Account
			int newIntegeral = b + acount.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " , JucaiBi=" + (acount.getJucaiBills() - b)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}
			// 5、 贡献表 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES("
					+ uId + "," + contribute.getTeacherId() + ","
					+ contribute.getFk_id() + ",'" + contribute.getInsertDate()
					+ "'," + contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TacticsAccount="
					+ (sysAccount.getPayLogAccount() + b));
			// 7、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detailAccount.getUserId() + ","
					+ detailAccount.getType() + ","
					+ detailAccount.getRecoderType() + ","
					+ detailAccount.getOrderId() + ","
					+ detailAccount.getPrice() + ",'"
					+ detailAccount.getInsertDate() + "','"
					+ detailAccount.getRemark() + "','" + detailAccount.getIp()
					+ "'," + detailAccount.getIsDel() + ")");
			// 8、 返利 JCP_Rebate
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param sale
	 * @param b
	 * @param uId
	 * @param accountDetailIntegeral
	 * @param accountDetail
	 * @param account
	 * @param contribute
	 * @param sysAccount
	 * @param detailAccount
	 * @param user
	 * @param sysRebate
	 * @param rebate
	 * @return 购买文字直播 （整个）
	 */
	public int purchTxtLive(Account account, AccountDetail accountDetail,
			AccountDetail accountDetailIntegeral, int uId, int b,
			TxtLiveSale sale, Contribute contribute, SysAccount sysAccount,
			SysDetailAccount detailAccount, User user, Rebate rebate,
			Rebate sysRebate) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加文字直播购买表 JCP_TxtLiveSale
			sta.executeUpdate("INSERT INTO JCP_TxtLiveSale"
					+ "(FK_UserId,FK_TearchId,OrderCode,FK_TxtLiveId,InsertDate,StartDate,EndDate,Remark,PayPrice) VALUES ("
					+ sale.getUserId() + "," + sale.getTeacherId() + ",'"
					+ sale.getOrderCode() + "'," + sale.getFk_txtId() + ",'"
					+ sale.getInsertDate() + "','" + sale.getStartDate()
					+ "','" + sale.getEndDate() + "','" + sale.getRemark()
					+ "'," + sale.getPayPrice() + ")");
			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetailIntegeral.getOrderCode() + "','"
					+ accountDetailIntegeral.getDetailMoney() + "',"
					+ accountDetailIntegeral.getDetailType() + ","
					+ accountDetailIntegeral.getState() + ",'"
					+ accountDetailIntegeral.getRemark() + "','"
					+ accountDetailIntegeral.getInsertDate() + "'," + 0 + ","
					+ accountDetailIntegeral.getUserId() + ")");
			// 3、账户总表 JCP_Account
			int newIntegeral = b + account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ ", JucaiBi=" + (account.getJucaiBills() - b)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 贡献表 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES("
					+ uId + "," + contribute.getTeacherId() + ","
					+ contribute.getFk_id() + ",'" + contribute.getInsertDate()
					+ "'," + contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TacticsAccount="
					+ (sysAccount.getTxtLiveAccount() + b));
			// 7、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detailAccount.getUserId() + ","
					+ detailAccount.getType() + ","
					+ detailAccount.getRecoderType() + ","
					+ detailAccount.getOrderId() + ","
					+ detailAccount.getPrice() + ",'"
					+ detailAccount.getInsertDate() + "','"
					+ detailAccount.getRemark() + "','" + detailAccount.getIp()
					+ "'," + detailAccount.getIsDel() + ")");
			// 8、 返利 JCP_Rebate
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param sale
	 * @param sysRebate
	 * @param rebate
	 * @param sysAccount
	 * @param b
	 * @param user
	 * @param uId
	 * @param accountDetailIntegeral
	 * @param accountDetail
	 * @param account
	 * @param detailAccount
	 * @param recoder
	 * @return 购买直播详情
	 */
	public int purchTxtDetail(User user, int b, SysAccount sysAccount,
			Rebate rebate, Rebate sysRebate, LiveDetailSale sale,
			Account account, AccountDetail accountDetail,
			AccountDetail accountDetailIntegeral, int uId,
			SysDetailAccount detailAccount, Contribute contribute,
			SaleRecoder recoder) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加文字直播详情购买表 JCP_LiveDetailSale
			sta.executeUpdate("INSERT INTO JCP_LiveDetailSale "
					+ "(FK_UserId,FK_TearchId,OrderCode,FK_LiveDetailId,InsertDate,Remark,PayPrice) "
					+ "VALUES(" + sale.getUserId() + "," + sale.getTeacherId()
					+ ",'" + sale.getOrderCode() + "',"
					+ sale.getLiveDetailId() + ",'" + sale.getInsertDate()
					+ "','" + sale.getRemark() + "'," + sale.getPayPrice()
					+ ")");

			// 试看记录表
			sta.executeUpdate("INSERT INTO JCP_ShiKanSale "
					+ "(UserId,Type,GuandianId,LiveId,ReadCount,InsertDate,Remark,IsDel) "
					+ "VALUES(" + recoder.getUserId() + "," + recoder.getType()
					+ "," + recoder.getGuandianId() + "," + recoder.getLiveId()
					+ "," + recoder.getReadCount() + ",'"
					+ recoder.getInsertDate() + "','" + recoder.getRemark()
					+ "'," + recoder.getIsDel() + ")");

			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetailIntegeral.getOrderCode() + "','"
					+ accountDetailIntegeral.getDetailMoney() + "',"
					+ accountDetailIntegeral.getDetailType() + ","
					+ accountDetailIntegeral.getState() + ",'"
					+ accountDetailIntegeral.getRemark() + "','"
					+ accountDetailIntegeral.getInsertDate() + "'," + 0 + ","
					+ accountDetailIntegeral.getUserId() + ")");
			// 3、账户总表 JCP_Account
			int newIntegeral = b + account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " , JucaiBi=" + (account.getJucaiBills() - b)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 贡献表 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES("
					+ uId + "," + contribute.getTeacherId() + ","
					+ contribute.getFk_id() + ",'" + contribute.getInsertDate()
					+ "'," + contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TacticsAccount="
					+ (sysAccount.getTxtLiveAccount() + b));
			// 7、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detailAccount.getUserId() + ","
					+ detailAccount.getType() + ","
					+ detailAccount.getRecoderType() + ","
					+ detailAccount.getOrderId() + ","
					+ detailAccount.getPrice() + ",'"
					+ detailAccount.getInsertDate() + "','"
					+ detailAccount.getRemark() + "','" + detailAccount.getIp()
					+ "'," + detailAccount.getIsDel() + ")");
			// 8、 返利 JCP_Rebate
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param ask
	 * @param bs
	 * @param user
	 * @param uId
	 * @param account
	 * @param sysAccount
	 * @param detailInteger
	 * @param accountDetail
	 * @param sysDetailAccount
	 * @return 付费提问
	 */
	public int payAsk(Ask ask, Account account, int uId, User user, int bs,
			SysAccount sysAccount, AccountDetail accountDetail,
			AccountDetail detailInteger, SysDetailAccount sysDetailAccount) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加提问表 JCP_Ask
			sta.executeUpdate("INSERT INTO JCP_Ask"
					+ "(FK_UserId,ParentId,AskBodys,AskDate,"
					+ "AskState,Hits,IsReply,FK_TearchId,FK_ClassId,Ip,"
					+ "IsPay,PayJucaibi,AskFrom,IsReturnJcb,ReplyCount) "
					+ "VALUES("
					+ ask.getUserId()
					+ ","
					+ ask.getParentId()
					+ ",'"
					+ ask.getAskBody()
					+ "','"
					+ ask.getAskDate()
					+ "',"
					+ ask.getAskState()
					+ ","
					+ ask.getHits()
					+ ","
					+ ask.getIsReply()
					+ ","
					+ ask.getTeacherId()
					+ ","
					+ ask.getClassId()
					+ ",'"
					+ ask.getIp()
					+ "',"
					+ ask.getIsPay()
					+ ","
					+ ask.getJucaiBills()
					+ ","
					+ ask.getAskFrom()
					+ ","
					+ ask.getIsReturnJcb()
					+ ","
					+ ask.getReplyCount() + ")");
			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detailInteger.getOrderCode() + "','"
					+ detailInteger.getDetailMoney() + "',"
					+ detailInteger.getDetailType() + ","
					+ detailInteger.getState() + ",'"
					+ detailInteger.getRemark() + "','"
					+ detailInteger.getInsertDate() + "'," + 0 + ","
					+ detailInteger.getUserId() + ")");

			// 3、账户总表 JCP_Account
			int newIntegeral = bs + account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " , JucaiBi=" + (account.getJucaiBills() - bs)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel != newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + bs) + ",UserAccount="
					+ (sysAccount.getUserAccount() - bs) + ",TacticsAccount="
					+ (sysAccount.getPayAskAccount() + bs));
			// 6、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + sysDetailAccount.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param user
	 * @param uId
	 * @param sysRebate
	 * @param sysAccount
	 * @param rebate
	 * @param accountDetail
	 * @param account
	 * @param aId
	 * @param g
	 * @param bills
	 * @param contribute
	 * @return 采纳回答
	 */
	public int catchAnswers(Account account, AccountDetail accountDetail,
			Rebate rebate, SysAccount sysAccount, Rebate sysRebate, int uId,
			User user, int aId, int g, int bills, Contribute contribute) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、更新回答表 JCP_Answer
			sta.executeUpdate("UPDATE JCP_Answer SET IsCaiNa=1 AND Grade=" + g
					+ " WHERE Id=" + aId);
			// 2、更新账户明细表 JCP_Account_Detail

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");

			// 3、账户总表 JCP_Account
			int newIntegeral = bills + account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel != newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + bills)
					+ ",UserAccount=" + (sysAccount.getUserAccount() - bills)
					+ ",TacticsAccount="
					+ (sysAccount.getPayAskAccount() + bills));
			// 6、 贡献表 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES ("
					+ contribute.getUserId() + "," + contribute.getTeacherId()
					+ "," + contribute.getFk_id() + ",'"
					+ contribute.getInsertDate() + "',"
					+ contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 7、返利表 JCP_Rebate
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param aId
	 * @param accountDetail
	 * @param account
	 * @param user
	 * @param uId
	 * @param sysAccount
	 * @param detailAccount
	 * @param ask
	 * @param detailAccount2
	 * @param bs
	 * @param accountDetailIntegeral
	 * @return 付费追问
	 */
	public int payRecycleAsk(Ask ask, SysDetailAccount detailAccount,
			SysAccount sysAccount, int uId, User user, Account account,
			AccountDetail accountDetail, int aId,
			SysDetailAccount detailAccount2, int bs,
			AccountDetail accountDetailIntegeral) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加提问表 JCP_Ask
			sta.executeUpdate("INSERT INTO JCP_Ask"
					+ "(FK_UserId,ParentId,AskBodys,AskDate,"
					+ "AskState,Hits,IsReply,FK_TearchId,FK_ClassId,Ip,"
					+ "IsPay,PayJucaibi,AskFrom,IsReturnJcb,ReplyCount) "
					+ "VALUES("
					+ ask.getUserId()
					+ ","
					+ ask.getParentId()
					+ ",'"
					+ ask.getAskBody()
					+ "','"
					+ ask.getAskDate()
					+ "',"
					+ ask.getAskState()
					+ ","
					+ ask.getHits()
					+ ","
					+ ask.getIsReply()
					+ ","
					+ ask.getTeacherId()
					+ ","
					+ ask.getClassId()
					+ ",'"
					+ ask.getIp()
					+ "',"
					+ ask.getIsPay()
					+ ","
					+ ask.getJucaiBills()
					+ ","
					+ ask.getAskFrom()
					+ ","
					+ ask.getIsReturnJcb()
					+ ","
					+ ask.getReplyCount() + ")");
			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetail.getOrderCode() + "','"
					+ accountDetail.getDetailMoney() + "',"
					+ accountDetail.getDetailType() + ","
					+ accountDetail.getState() + ",'"
					+ accountDetail.getRemark() + "','"
					+ accountDetail.getInsertDate() + "'," + 0 + ","
					+ accountDetail.getUserId() + ")");
			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ accountDetailIntegeral.getOrderCode() + "','"
					+ accountDetailIntegeral.getDetailMoney() + "',"
					+ accountDetailIntegeral.getDetailType() + ","
					+ accountDetailIntegeral.getState() + ",'"
					+ accountDetailIntegeral.getRemark() + "','"
					+ accountDetailIntegeral.getInsertDate() + "'," + 0 + ","
					+ accountDetailIntegeral.getUserId() + ")");

			// 3、账户总表 JCP_Account
			int newIntegeral = +account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " , JucaiBi=" + (account.getJucaiBills() - bs)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel != newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + bs) + ",UserAccount="
					+ (sysAccount.getUserAccount() - bs) + ",TacticsAccount="
					+ (sysAccount.getPayAskAccount() + bs));
			// 6、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + detailAccount.getUserId() + ","
					+ detailAccount.getType() + ","
					+ detailAccount.getRecoderType() + ","
					+ detailAccount.getOrderId() + ","
					+ detailAccount.getPrice() + ",'"
					+ detailAccount.getInsertDate() + "','"
					+ detailAccount.getRemark() + "','" + detailAccount.getIp()
					+ "'," + detailAccount.getIsDel() + ")");
			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * @param integeralDetail
	 * @param detail
	 * @param sale
	 * @param account
	 * @param b
	 * @param uId
	 * @param user
	 * @param contribute
	 * @param sysAccount
	 * @param sysDetailAccount
	 * @param sysRebate
	 * @param rebate
	 * @return
	 * 
	 *         购买视频直播
	 */
	public int purchLiveVideo(VideoLiveSale sale, AccountDetail detail,
			AccountDetail integeralDetail, int b, Account account, int uId,
			User user, Contribute contribute, SysAccount sysAccount,
			SysDetailAccount sysDetailAccount, Rebate rebate, Rebate sysRebate) {
		Connection dbConn = JdbcUtil.connSqlServer();
		try {
			dbConn.setAutoCommit(false);
			Statement sta = dbConn.createStatement();
			// 1、添加视频直播购买表 JCP_VideoLiveSale
			sta.executeUpdate("INSERT INTO JCP_VideoLiveSale "
					+ "(FK_UserId,FK_TearchId,OrderCode,FK_VideoLiveId,InsertDate,StartDate,EndDate,IsStop,Remark,PayPrice) "
					+ "VALUES(" + sale.getUserId() + "," + sale.getTeacherId()
					+ ",'" + sale.getOrderCode() + "'," + sale.getFk_liveId()
					+ ",'" + sale.getInsertDate() + "','" + sale.getStartDate()
					+ "','" + sale.getEndDate() + "'," + sale.getIsStop()
					+ ",'" + sale.getRemark() + "'," + sale.getPayPrice() + ")");
			// 2、更新账户明细表 JCP_Account_Detail
			// 聚财币减少
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ detail.getOrderCode() + "','" + detail.getDetailMoney()
					+ "'," + detail.getDetailType() + "," + detail.getState()
					+ ",'" + detail.getRemark() + "','"
					+ detail.getInsertDate() + "'," + 0 + ","
					+ detail.getUserId() + ")");

			// 积分增加
			sta.executeUpdate("INSERT INTO JCP_Account_Detail"
					+ "(OrderCode,DetailMoney,DetailType,State,Remark,"
					+ "InsertDate,IsDel,UserId) VALUES ('"
					+ integeralDetail.getOrderCode() + "','"
					+ integeralDetail.getDetailMoney() + "',"
					+ integeralDetail.getDetailType() + ","
					+ integeralDetail.getState() + ",'"
					+ integeralDetail.getRemark() + "','"
					+ integeralDetail.getInsertDate() + "'," + 0 + ","
					+ integeralDetail.getUserId() + ")");
			// 3、账户总表 JCP_Account
			int newIntegeral = b + account.getIntegeral();
			sta.executeUpdate("UPDATE JCP_Account SET Integral=" + newIntegeral
					+ " , JucaiBi=" + (account.getJucaiBills() - b)
					+ " WHERE UserId=" + uId);

			// 4、用户表积分 JCP_User
			sta.executeUpdate("UPDATE JCP_User SET AllIntegral=" + newIntegeral
					+ " WHERE Id=" + uId);

			// 更新用户等级
			int currentLeavel = user.getUserLeval();
			int newLeavel = BaseData.getLeavel(newIntegeral);
			if (currentLeavel < newLeavel) {
				sta.executeUpdate("UPDATE JCP_User SET UserLevel=" + newLeavel
						+ " WHERE Id=" + uId);
			}

			// 5、 贡献表 JCP_Contribute
			sta.executeUpdate("INSERT INTO JCP_Contribute"
					+ "(FK_UserId,FK_TearchId,FK_Id,InsertDate,AllJucaibi,ComType) VALUES("
					+ uId + "," + contribute.getTeacherId() + ","
					+ contribute.getFk_id() + ",'" + contribute.getInsertDate()
					+ "'," + contribute.getAllJucaiBills() + ","
					+ contribute.getComType() + ")");
			// 6、 系统总表 JCP_SysAccount
			sta.executeUpdate("UPDATE JCP_SysAccount SET SysChildAccount="
					+ (sysAccount.getSysChildAccount() + b) + ",UserAccount="
					+ (sysAccount.getUserAccount() - b) + ",TeacherAccount="
					+ (sysAccount.getTeacherRebateAccount() + b)
					+ ",SysRebateAccount="
					+ (sysAccount.getSysRebateAccount() + b)
					+ ",TxtLiveAccount=" + (sysAccount.getTxtLiveAccount() + b));
			// 7、 系统详细 JCP_SysAccountDateil
			sta.executeUpdate("INSERT INTO JCP_SysAccountDateil"
					+ "(UserId,AccType,RecordType,OrderId,Price,InsertDate,Remark,IP,IsDel)"
					+ " VALUES(" + sysDetailAccount.getUserId() + ","
					+ sysDetailAccount.getType() + ","
					+ sysDetailAccount.getRecoderType() + ","
					+ sysDetailAccount.getOrderId() + ","
					+ sysDetailAccount.getPrice() + ",'"
					+ sysDetailAccount.getInsertDate() + "','"
					+ sysDetailAccount.getRemark() + "','"
					+ sysDetailAccount.getIp() + "',"
					+ sysDetailAccount.getIsDel() + ")");
			// 8、 返利 JCP_Rebate
			// 系统返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ rebate.getTeacherId()
					+ ","
					+ rebate.getType()
					+ ",'"
					+ rebate.getRebateMoney()
					+ "',"
					+ rebate.getFromId()
					+ ",'"
					+ rebate.getInsertDate()
					+ "','"
					+ rebate.getRemark() + "')");
			// 讲师返利
			sta.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
					+ sysRebate.getTeacherId()
					+ ","
					+ sysRebate.getType()
					+ ",'"
					+ sysRebate.getRebateMoney()
					+ "',"
					+ sysRebate.getFromId()
					+ ",'"
					+ sysRebate.getInsertDate()
					+ "','" + sysRebate.getRemark() + "')");

			dbConn.commit();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbConn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				dbConn.setAutoCommit(true);
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
