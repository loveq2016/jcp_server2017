package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.AccountDetail;

/**
 * @author Administrator
 *
 *   账户详细信息
 */
public interface AccountDetailDao {
	
	/**
	 * @param uId
	 * @return  根据用户id获取账户详细信息
	 */
	public List<AccountDetail> findAccountDetailByuId(int uId,int page);
	
	/**
	 * @param uId
	 * @param state
	 * @return 根据用户id和类型获取详细信息
	 */
	public List<AccountDetail> findAccountDetailByUIdAndState(int uId,int state,int page);
	
	/**
	 * @param uId
	 * @param type
	 * @return   根据用户id和 分类获取详细信息
	 */
	public List<AccountDetail> findAccountDetailByUidAndType(int uId,int type,int page);
	/**
	 * @param uId
	 * @param state
	 * @param type
	 * @param page
	 * @return  获取用户分类下的不同状态账户信息
	 */
	public List<AccountDetail> findDetailByUidAndState(int uId,int state,int page);
	
	
	/**
	 * @param uId
	 * @param state
	 * @param type
	 * @param page
	 * @return  获取用户分类下的不同状态账户信息
	 */
	public AccountDetail findDetailByOrderCode(String orderCode);
	/**
	 * @param detail
	 * @return  添加账户详细信息
	 */
	public int addAccountDetails(AccountDetail detail);
	/**
	 * @param id
	 * @return  删除账户详细信息
	 */
	public int delAccountDetails(int id);
	

}
