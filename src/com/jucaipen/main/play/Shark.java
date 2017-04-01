package com.jucaipen.main.play;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.SharkDetail;
import com.jucaipen.model.SharkInfo;
import com.jucaipen.service.AccountDetailSer;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.SharkDetailServer;
import com.jucaipen.service.SharkInfoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author 杨朗飞 2017年1月23日 下午1:19:08 摇一摇结果
 * 
 *         （1手机网页端，2手机安卓端，3手机IOS端） pathWay
 */
public class Shark extends HttpServlet {
	private static final long serialVersionUID = -6439675981383682638L;
	// 400-500
	private static final int Max_Price = 3;
	// 300-400
	private static final int Middle_Price = 6;
	// 200-300
	private static final int Low_Price = 9;
	private boolean isExit = true;
	private boolean isRest = true;
	private boolean isAllRest = true;
	private int ran;
	private String result;
	private static final int MAX_COUNT = 1;
	private int syPrice;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String pathWay = request.getParameter("pathWay");
		// 1、判断摇一摇时间
		if (checkTime()) {
			// 2、判断用户是否登录
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					// 3、判断当前用户摇一摇次数
					if (checkCount(uId)) {
						if (StringUtil.isNotNull(pathWay)
								&& StringUtil.isInteger(pathWay)) {
							int path = Integer.parseInt(pathWay);
							if (createRandomBills(uId, path)) {
								if (isRest) {
									result = JsonUtil.getRetMsgJu(0, "恭喜你获取"
											+ ran + "个聚财币", ran);
								} else {
									result = JsonUtil.getRetMsgJu(0, "恭喜你获取"
											+ syPrice + "个聚财币", syPrice);
								}
							} else {
								if (isExit) {
									result = JsonUtil.getRetMsgJu(1,
											"摇一摇失败，请重新摇一摇", 0);
								} else {
									if (isAllRest) {
										// 8 所有额度已经抢完
										result = JsonUtil.getRetMsgJu(8,
												"本次活动已结束", 0);
									} else {
										// 7 今日额度已抢完，明日再来
										result = JsonUtil.getRetMsgJu(7,
												"今日已被抢完", 0);
									}
								}
							}
						} else {
							result = JsonUtil.getRetMsgJu(2, "pathWay 参数异常", 0);
						}
					} else {
						result = JsonUtil.getRetMsgJu(3, "摇一摇次数已经用完", 0);
					}
				} else {
					result = JsonUtil.getRetMsgJu(4, "请先登录账号", 0);
				}
			} else {
				result = JsonUtil.getRetMsgJu(5, "userId 参数异常", 0);
			}
		} else {
			result = JsonUtil.getRetMsgJu(6, "摇一摇活动还没开始", 0);
		}
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * @param uId
	 * @return  摇一摇次数是否用完
	 */
	private boolean checkCount(int uId) {
		// 获取当前用户摇一摇次数
		int count = SharkDetailServer.getSharkCount(uId);
		return count < MAX_COUNT;
	}

	/**
	 * @return  摇一摇时间是否有效
	 */
	private boolean checkTime() {
		// 获取摇一摇时间
		SharkInfo info = SharkInfoServer.getSharkInfo();
		if (info != null) {
			String startDate = info.getStartDate();
			String endDate = info.getEndDate();
			return TimeUtils.isLive(startDate, endDate);
		} else {
			return false;
		}
	}

	public boolean createRandomBills(int uId, int path) {
		// 50-500 产生随机个聚财币
		createBill();
		return createRecoder(uId, path, ran)
				&& createAccountDeatil(uId, path, ran)
				&& createAccount(uId, path, ran);
	}

	/**
	 * @return 在 50-500 之间产生随机的额度
	 */
	private void createBill() {
		ran = ran(50, 500);
		getCount1(ran);
	}

	/**
	 * @param ran   对第一次摇一摇的结果进行判断处理
	 */
	public void getCount1(int ran) {
		//  400-500
		if (ran > 400 && ran <= 500) {
			int num = SharkDetailServer.getMaxCount(400, 500);
			if (num >= Max_Price) {
				ran = ran(300, 400);
				getCount2(ran);
			}
			//  300-400
		} else if (ran > 300 && ran <= 400) {
			int num = SharkDetailServer.getMaxCount(300, 400);
			if (num >= Middle_Price) {
				ran = ran(200, 300);
				getCount3(ran);
			}
			//  200-300
		} else if (ran > 200 && ran <= 300) {
			int num = SharkDetailServer.getMaxCount(200, 300);
			if (num >= Low_Price) {
				getCount4();
			}
		}
	}

	/**
	 * @param min
	 * @param max
	 * @return 在 min-max 之间产生随机数
	 */
	public static int ran(int min, int max) {
		// Random random=new Random();
		// return random.nextInt(max)%(max-min+1) + min;
		return (int) ((max - min) * Math.random() + min);
	}

	/**
	 * @param ran
	 * @return //产生的额度在 300-400之间
	 */
	public void getCount2(int ran) {
		int num = SharkDetailServer.getMaxCount(300, 400);
		if (num >= Middle_Price) {
			ran = ran(200, 300);
			getCount3(ran);
		}

	}

	/**
	 * @param ran
	 * @return // 产生的额度在 200-300 之间
	 */
	public void getCount3(int ran) {
		int num = SharkDetailServer.getMaxCount(200, 300);
		if (num >= Low_Price) {
			getCount4();
		}
	}

	/**
	 * @param ran
	 * @return // 产生的额度在 50-200 之间
	 */
	public void getCount4() {
		ran = ran(50, 200);
	}

	/**
	 * @param uId
	 * @param path
	 * @param ran
	 * @return  修改总账户聚财币数量
	 */
	private boolean createAccount(int uId, int path, int ran) {
		Account account = AccountSer.findAccountByUserId(uId);
		if (!isRest) {
			ran = syPrice;
		}
		if (account == null) {
			account = new Account();
			account.setIntegeral(0);
			account.setUserId(uId);
			account.setJucaiBills(ran);
			return AccountSer.addAccount(account) > 0;
		} else {
			return AccountSer.updateBills(uId, ran + account.getJucaiBills()) > 0;
		}
	}

	/**
	 * @param uId
	 * @param path
	 * @param ran
	 * @return 添加账单聚财币增加记录
	 */
	private boolean createAccountDeatil(int uId, int path, int ran) {
		AccountDetail detail = new AccountDetail();
		if (!isRest) {
			ran = syPrice;
		}
		detail.setDetailMoney(ran);
		detail.setDetailType(0);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detail.setOrderCode("");
		detail.setRemark("【聚财盆学堂送红包贺新春】活动聚财币红包收入+" + ran);
		detail.setUserId(uId);
		detail.setState(0);
		return AccountDetailSer.addDetails(detail) > 0;
	}

	/**
	 * @param uId
	 * @param path
	 * @param ran
	 * @return 添加摇一摇记录
	 */
	private boolean createRecoder(int uId, int path, int ran) {
		SharkInfo info = SharkInfoServer.getSharkInfo();
		syPrice = info.getSyPrice();
		if (syPrice > 0) {
			isExit = true;
			if (syPrice < ran) {
				isRest = false;
				SharkDetail detail = new SharkDetail();
				detail.setInsertDate(TimeUtils.format(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				detail.setPathWay(path);
				detail.setPrice(syPrice);
				detail.setRemark("摇一摇获取红包" + syPrice + "个聚财币,当前余额：0");
				detail.setUserId(uId);
				return SharkInfoServer.updatePrice(0) > 0
						&& SharkDetailServer.addDetail(detail) > 0;
			} else {
				isRest = true;
				SharkDetail detail = new SharkDetail();
				detail.setInsertDate(TimeUtils.format(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				detail.setPathWay(path);
				detail.setPrice(ran);
				detail.setRemark("摇一摇获取红包" + ran + "个聚财币,当前余额："
						+ (syPrice - ran));
				detail.setUserId(uId);
				return SharkInfoServer.updatePrice(syPrice - ran) > 0
						&& SharkDetailServer.addDetail(detail) > 0;
			}
		} else {
			ran = 0;
			isExit = false;
			String date = TimeUtils.format(TimeUtils.addDay(1), "yyyy-MM-dd");
			SharkInfo inf = SharkInfoServer.getSharkByIsEnd(date);
			if (inf != null) {
				// 今日额度抢完
				isAllRest = false;
			} else {
				// 所有额度用完
				isAllRest = true;
			}
			return false;
		}
	}
}
