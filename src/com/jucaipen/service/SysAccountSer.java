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
	
	public static int updateTeacherRebate(int teacherAccount, int rebateAccount) {
		SysAccountDao dao=new SysAccountImp();
		return dao.updateTeacherRebate(teacherAccount, rebateAccount);
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
	
	public static int updateDaShangInfo(int child,int user,int daShang){
		SysAccountDao dao=new SysAccountImp();
		return dao.updateDaShangInfo(child, user, daShang);
	}
	
	
	public static  int updateGiftInfo(int child, int user, int gift) {
		SysAccountDao dao=new SysAccountImp();
		return dao.updateGiftInfo(child, user, gift);
	}

}
