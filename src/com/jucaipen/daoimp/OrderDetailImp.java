package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.OrderDetailDao;
import com.jucaipen.model.OrderDetail;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 * 
 *         "SELECT TOP 15 * FROM " +
 *         "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Product"
 *         + " ) A " + "WHERE RowNumber > " + 15 * (pager - 1)
 * 
 * 
 *         订单详细信息
 */
public class OrderDetailImp implements OrderDetailDao {
	private List<OrderDetail> payProducts = new ArrayList<OrderDetail>();
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;

	/**
	 * @return 查询产品总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Order_Detail"
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

	public List<OrderDetail> findAllPayProductList(int page) {
		// 获取全部订单详细信息
		int totlePage = findTotlePager("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail"
							+ " WHERE IsDelete=0 ) A " + "WHERE RowNumber > "
							+ 15 * (page - 1));
			payProducts = getOrderDetails(res, page, totlePage);
			return payProducts;
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

	public List<OrderDetail> findPayProductByUid(int uId, int page) {
		// 根据用户ID获取订单详细信息
		int totlePage = findTotlePager("WHERE UserId=" + uId+" AND IsDelete=0");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail WHERE UserId="
							+ uId + " AND IsDelete=0 ) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			payProducts = getOrderDetails(res, page, totlePage);
			return payProducts;
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

	public List<OrderDetail> findPayProductByProductId(int fk_OrderId, int page) {
		// 根据商品信息获取订单信息
		int totlePage = findTotlePager("WHERE FK_OrderId=" + fk_OrderId+" AND IsDelete=0");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail WHERE FK_OrderId="
							+ fk_OrderId + " AND IsDelete=0 ) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			payProducts = getOrderDetails(res, page, totlePage);
			return payProducts;
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

	public OrderDetail findPayProductByOrderId(String orderId) {
		// 根据订单ID获取订单详细信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Order_Detail WHERE OrderCode='"
							+ orderId + "' AND IsDelete=0");
			payProducts = getOrderDetails(res, 1, 1);
			if (payProducts.size() > 0) {
				return payProducts.get(0);
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

	public List<OrderDetail> findPayProductByIsDeleted(int isDeleted, int page) {
		// 根据商品状态获取订单详细信息
		int totlePage = findTotlePager("WHERE IsDelete=" + isDeleted);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail WHERE IsDelete="
							+ isDeleted + " ) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			payProducts = getOrderDetails(res, page, totlePage);
			return payProducts;
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

	public List<OrderDetail> findPayProductByProductType(int productType,
			int page) {
		// 根据商品类型获取订单详细信息
		int totlePage = findTotlePager("WHERE ProductType=" + productType+" AND IsDelete=0");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail WHERE ProductType="
							+ productType + " AND IsDelete=0) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			return getOrderDetails(res, page, totlePage);
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

	public OrderDetail findPayProductById(int id) {
		// 根据ID获取订单详细信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Order_Detail WHERE Id="
					+ id+" AND IsDelete=0");
			payProducts = getOrderDetails(res, 1, 1);
			if (payProducts.size() > 0) {
				return payProducts.get(0);
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

	public List<OrderDetail> findLastPayProduct(int lastCount) {
		// 获取最近几条订单详细信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + lastCount
					+ " * FROM JCP_Order_Detail WHERE IsDelete=0 ORDER BY Id");
			return getOrderDetails(res, 1, 1);
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

	public int addPayProductInfo(OrderDetail payProduct) {
		// 添加订单详细信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO JCP_Order_Detail"
							+ "(OrderCode,FK_OrderId,Price,NowPrice,BuyCount,AllMoney,ProductTitle,"
							+ "XuYueYear,XuYueMonth,XuYueDay,IsDelete,ProductType,UserId) VALUES ('"
							+ payProduct.getOrderId()
							+ "',"
							+ payProduct.getFk_OrderId()
							+ ",'"
							+ payProduct.getPrice()
							+ "','"
							+ payProduct.getNowPrice()
							+ "',"
							+ payProduct.getBuyCount()
							+ ",'"
							+ payProduct.getAllMoney()
							+ "','"
							+ payProduct.getProductTitle()
							+ "',"
							+ payProduct.getXuYueYear()
							+ ","
							+ payProduct.getXuYueMonth()
							+ ","
							+ payProduct.getXuYueDay()
							+ ","
							+ payProduct.getIsDelete()
							+ ","
							+ payProduct.getUserId() + ")");
			return isSuccess;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public List<OrderDetail> getOrderDetails(ResultSet result, int page,
			int totlePage) {
		payProducts.clear();
		try {
			while (result.next()) {
				int id = result.getInt("ID");
				String orderCode = result.getString("OrderCode");
				int fk_OrderId = result.getInt("FK_OrderId");
				String price = result.getString("Price");
				String nowPrice = result.getString("NowPrice");
				int buyCount = result.getInt("BuyCount");
				String allMoney = result.getString("AllMoney");
				String productTitle = result.getString("ProductTitle");
				int xuYueYear = result.getInt("XuYueYear");
				int xuYueMonth = result.getInt("XuYueMonth");
				int xuYueDay = result.getInt("XuYueDay");
				int isDelete = result.getInt("IsDelete");
				int productType = result.getInt("ProductType");
				int userId = result.getInt("UserId");
				OrderDetail payProduct = new OrderDetail();
				payProduct.setId(id);
				payProduct.setPage(page);
				payProduct.setTotlePage(totlePage);
				payProduct.setOrderId(orderCode);
				payProduct.setFk_OrderId(fk_OrderId);
				payProduct.setPrice(price);
				payProduct.setNowPrice(nowPrice);
				payProduct.setBuyCount(buyCount);
				payProduct.setAllMoney(allMoney);
				payProduct.setProductTitle(productTitle);
				payProduct.setXuYueYear(xuYueYear);
				payProduct.setXuYueMonth(xuYueMonth);
				payProduct.setXuYueDay(xuYueDay);
				payProduct.setIsDelete(isDelete);
				payProduct.setProductType(productType);
				payProduct.setUserId(userId);
				payProduct.setTotlePage(totlePage);
				payProduct.setPage(page);
				payProducts.add(payProduct);
			}
			return payProducts;
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
	public List<OrderDetail> findDetailByUIdAndType(int userId, int type,
			int page) {
		// 获取用户对应下分类的订单信息
		dbConn = JdbcUtil.connSqlServer();
		int totlePage = findTotlePager("WHERE UserId=" + userId
				+ " AND ProductType=" + type + " AND IsDelete=" + 0);
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNumber,* FROM JCP_Order_Detail WHERE ProductType="
							+ type + " AND UserId=" + userId + " AND IsDelete="
							+ 0 + " ) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			getOrderDetails(res, page, totlePage);
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
	public int deleteDetail(int id) {
		// 删除订单
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Order_Detail SET IsDelete="
					+ 1 + " WHERE Id=" + id);
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

}
