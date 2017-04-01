package com.jucaipen.service;

import com.jucaipen.dao.SysDetailAccountDao;
import com.jucaipen.daoimp.SysDetailAccountImp;
import com.jucaipen.model.SysDetailAccount;

public class SysDetailAccountSer {

	public static int addDetails(SysDetailAccount detail) {
		SysDetailAccountDao dao = new SysDetailAccountImp();
		return dao.addDetails(detail);
	}

	public static int delDetail(int id) {
		SysDetailAccountDao dao = new SysDetailAccountImp();
		return dao.delDetail(id);
	}

}
