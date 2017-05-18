package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Contribute;

/**
 * @author Administrator
 * 
 *         ���װ�
 */
public interface ContributeDao {
	/**
	 * @param id
	 * @return ͨ��id��ȡ���װ���Ϣ
	 */
	public Contribute findContributeById(int id);
	
	
	

	/**
	 * @param uId
	 * @return ͨ���û�id��ȡ���װ�
	 */
	public List<Contribute> findContributeByTid(int teacherIds,String type);
	
	public  List<Contribute> findContributeByTid(int tId,String type,int page);
	
	public  List<Contribute> findTopContributeByTid(int tId,String type,int top);
	
	/**
	 * @param teacherId
	 * @param type
	 * @return  ��ȡ���н�ʦ�����ܰ�
	 */
	public List<Contribute>  findContributeGroupByTid(String type);
	
	
	
	/**
	 * @param teacherId
	 * @return  ��ȡ��ʦ���ܰ�
	 */
	public int  findAllContributeByTid(int teacherId);
	

	/**
	 * @param uId
	 * @param tId
	 * @return ��ѯ�û���ĳ����ʦ�Ĺ���
	 */
	public List<Contribute> findContributeByUidAndTid(int uId, int tId);

	/**
	 * @param uId
	 * @param fkId
	 * @return ��ȡ�û������Ĺ�����Ϣ
	 */
	public List<Contribute> findContributeByUidAndFkId(int uId, int fkId);

	/**
	 * @return ��ȡ���еĹ�����Ϣ
	 */
	public List<Contribute> findAllContribute();
	
	/**
	 * @param contribute
	 * @return   ��ӹ���
	 */  
	public int addContribute(Contribute contribute);
}
