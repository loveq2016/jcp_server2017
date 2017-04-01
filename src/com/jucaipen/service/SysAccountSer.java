package com.jucaipen.service;

import com.jucaipen.dao.SysAccountDao;
import com.jucaipen.daoimp.SysAccountImp;
import com.jucaipen.model.SysAccount;

public class SysAccountSer{

	public static int updatePurchInfo( int sysAccount, int childAccount,
			int userAccount) {
		SysAccountDao dao=new SysAccountImp();
		return dao.updatePurchInfo(sysAccount, childAccount, userAccount);
	}

	public static SysAccount findAccountInfo() {
		SysAccountDao dao=new SysAccountImp();
		return dao.findAccountInfo();
	}

	public static int updateGurdianInfo( int sysAccount, int childAccount,
			int userAccount, int gurdianAccount) {
		SysAccountDao dao=new SysAccountImp();
		return dao.updateGurdianInfo( sysAccount, childAccount, userAccount, gurdianAccount);
	}

}
