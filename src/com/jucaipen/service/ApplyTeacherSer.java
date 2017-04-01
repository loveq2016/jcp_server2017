package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.ApplyTeacherDao;
import com.jucaipen.daoimp.ApplyTeacherImp;
import com.jucaipen.model.ApplyTeacher;

/**
 * @author Administrator
 * 
 *         申请名师
 */
public class ApplyTeacherSer {

	/**
	 * @param uId
	 * @return 根据用户id获取申请信息
	 */
	public static ApplyTeacher findApplyByUid(int uId) {
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.findApplyByUid(uId);
	}
	
	public static int updateApplyInfo(int id, ApplyTeacher apply) {
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.updateApplyInfo(id, apply);
	}
	
	public static ApplyTeacher findLastApplyByUidWithStep(int uId, int count, int step){
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.findLastApplyByUidWithStep(uId, count, step);
	}

	/**
	 * @param recommId
	 * @return 根据推荐人获取申请信息
	 */
	public static List<ApplyTeacher> findApplyByRecommId(int recommId) {
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.findApplyByRecommId(recommId);
	}

	public static ApplyTeacher findLastApplyByUid(int uId, int count) {
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.findLastApplyByUid(uId, count);
	}

	/**
	 * @param apply
	 * @return 添加申请信息
	 */
	public static int addApply(ApplyTeacher apply, int step) {
		ApplyTeacherDao dao = new ApplyTeacherImp();
		return dao.addApply(apply, step);
	}

}
