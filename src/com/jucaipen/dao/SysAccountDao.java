package com.jucaipen.dao;

import com.jucaipen.model.SysAccount;

public interface SysAccountDao {
	
	/**
	 * @return  ��ֵ  �����˻�
	 */
	public int updatePurchInfo(int sysAccount,int childAccount,int userAccount);
	
	
	/**
	 * @param child
	 * @param user
	 * @param daShang
	 * @return  ���´�������
	 */
	public int updateDaShangInfo(int child,int user,int daShang);
	
	
	/**
	 * @param teacherAccount
	 * @param rebateAccount
	 * @return  ���½�ʦ��������
	 */
	public int updateTeacherRebate(int teacherAccount,int rebateAccount);
	
	
	/**
	 * @param child
	 * @param user
	 * @param gift
	 * @return   ������Ʒ���ݸ���
	 */
	public int updateGiftInfo(int child,int user,int gift);
	
	/**
	 * @param count
	 * @return   ��ȡ���˻���Ϣ
	 */
	public SysAccount findAccountInfo();
	
	
	
	/**
	 * @param sysAccount
	 * @param childAccount
	 * @param userAccount
	 * @return   �ػ�  �������˻���Ϣ
	 */
	public int updateGurdianInfo(int sysAccount,int childAccount,int userAccount,int gurdianAccount);

}
