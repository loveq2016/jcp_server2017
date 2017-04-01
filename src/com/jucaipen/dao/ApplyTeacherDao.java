package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.ApplyTeacher;

/**
 * @author Administrator
 *
 *  申请讲师
 */
public interface ApplyTeacherDao {
	
	/**
	 * @param uId
	 * @return 根据用户id获取申请信息
	 */
	public  ApplyTeacher findApplyByUid(int uId);
	
	
	/**
	 * @param uId
	 * @param count
	 * @return  获取最近一条申请信息
	 */
	public  ApplyTeacher findLastApplyByUid(int uId,int count);
	
	/**
	 * @param uId
	 * @param count
	 * @return  获取最近一条申请信息
	 */
	public  ApplyTeacher findLastApplyByUidWithStep(int uId,int count,int step);
	
	/**
	 * @param recommId
	 * @return  根据推荐用户id获取申请信息
	 */
	public List<ApplyTeacher> findApplyByRecommId(int recommId);
	/**
	 * @param apply
	 * @return  添加申请信息
	 */
	public int addApply(ApplyTeacher apply,int step);
	
	public int updateApplyInfo(int id,ApplyTeacher apply);

}
