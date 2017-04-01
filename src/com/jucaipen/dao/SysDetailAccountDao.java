package com.jucaipen.dao;

import com.jucaipen.model.SysDetailAccount;

public interface SysDetailAccountDao {
	/**
	 * @param detail
	 * @return  添加总账户详细信息
	 */
	public int addDetails(SysDetailAccount detail);
	
	/**
	 * @param id
	 * @return  删除总账户详细信息
	 */
	public int delDetail(int id);

}
