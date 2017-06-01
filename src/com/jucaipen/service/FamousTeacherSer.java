package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.FamousTeacherDao;
import com.jucaipen.daoimp.FamousTeacherImp;
import com.jucaipen.model.FamousTeacher;

public class FamousTeacherSer {

	/**
	 * @return 获取所有讲师信息   分页
	 */
	public static List<FamousTeacher> findAllFamousTeacher(int page) {
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findAllFamousTeacher(page);
	}
	
	/**
	 * @return  获取热门讲师
	 */
	public static List<FamousTeacher>  findHotTeacher(int top){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findHotTeacher(top);
	}
	
	/**
	 * @param userId
	 * @return  根据用户id获取讲师
	 */
	public static FamousTeacher findFamousTeacherByUserId(int userId){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findFamousTeacherByUserId(userId);
	}
	
	/**
	 * @param teacherId
	 * @param teacher
	 * @return  更新讲师基本信息
	 */
	public static int updateTeacherBaseInfo(int teacherId, FamousTeacher teacher){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.updateTeacherBaseInfo(teacherId, teacher);
	}
	
	/**
	 * @return  获取所有讲师   不分页
	 */
	public static List<FamousTeacher> findAllTeacher(){
		FamousTeacherDao dao=new FamousTeacherImp();
		return dao.findAllTeacher();
	}
	
	/**
	 * @param tId
	 * @return  获取守护讲师信息
	 */
	public static FamousTeacher findPurchInfo(int tId){
		FamousTeacherDao dao=new FamousTeacherImp();
		return dao.findPurchInfo(tId);
	}
	
	/**
	 * @param id
	 * @param readNum
	 * @param xnNum
	 * @return  更新观点阅读数
	 */
	public static int updateIdeaReadNum(int id,int readNum,int xnNum){
		FamousTeacherDao dao=new FamousTeacherImp();
		return dao.updateIdeaReadNum(id, readNum, xnNum);
	}

	/**
	 * @return  获取首页统计数据  （人气    观点   回答数   粉丝数）
	 */
	public static List<FamousTeacher> findIndexData() {
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findIndexData();
	}
	
	/**
	 * @param tId
	 * @return  获取讲师聊天室id
	 */
	public static int findRoomInfo(int tId){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findRoomInfo(tId);
	}
	
	
	/**
	 * @param tId
	 * @return  获取讲师基本信息
	 */
	public static FamousTeacher findTeacherBaseInfo(int tId){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findTeacherBaseInfo(tId);
	}
	
	
	/**
	 * @param fans
	 * @param id
	 * @return  更新粉丝数量
	 */
	public static int updateFansNum(int fans,int id){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.updateFansNum(fans, id);
	}
	
	/**
	 * @param keyWord
	 * @return  根据关键词搜索讲师
	 */
	public static List<FamousTeacher> findTeacherByKeyWord(String keyWord){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findTeacherByKeyWord(keyWord);
	}
	
	/**
	 * @param num
	 * @param id
	 * @return 更新提问数
	 */
	public static int updateAskNum(int num,int id){
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.updateAskNum(num, id);
	}

	/**
	 * @param count
	 * @return 获取推荐的讲师信息
	 */
	public static List<FamousTeacher> findFamousTeacherByIndex(int count) {
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findFamousTeacherByIndex(count);
	}

	/**
	 * @param id
	 * @return 根据id查询讲师信息
	 */
	public static FamousTeacher findFamousTeacherById(int id) {
		FamousTeacherDao dao = new FamousTeacherImp();
		return dao.findFamousTeacherById(id);
	}
	
	/**
	 * @param id
	 * @return 获取最大提问数
	 */
	public static int findMaxAsk(int id){
		FamousTeacherDao dao=new FamousTeacherImp();
		return dao.findMaxAsk(id);
	}

	public static FamousTeacher findTeacherNameById(int teacherId) {
		FamousTeacherDao dao=new FamousTeacherImp();
		return dao.findTeacherNameById(teacherId);
	}

}
