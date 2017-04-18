package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoDao;
import com.jucaipen.daoimp.VideoImp;
import com.jucaipen.model.Video;

public class VideoServer {
	/**
	 * @return 查询所有视频信息
	 */
	public static List<Video> findAll(int page) {
		VideoDao dao = new VideoImp();
		return dao.findAll(page);
	}
	
	public static List<Video>  findVideoBySpecialId(int specialId){
		VideoDao dao = new VideoImp();
		return dao.findVideoBySpecialId(specialId);
	}
	
	public static List<Video> findHotVideoList(int top){
		VideoDao dao = new VideoImp();
		return dao.findHotVideoList(top);
	}
	
	public static List<Video> findVideoByKeyWord(String keyWord,int page) {
		VideoDao dao = new VideoImp();
		return dao.findVideoByKeyWord(keyWord, page);
	}
	
	public static  Video findHitsAndGoods(int id) {
		VideoDao dao = new VideoImp();
		return dao.findHitsAndGoods(id);
	}
	
	public static int updateGoods(int id, int goods){
		VideoDao dao = new VideoImp();
		return dao.updateGoods(id, goods);
	}

	/**
	 * @param classId
	 * @return 根据分类id 查询相应视频
	 */
	public static List<Video> findVideoByClassId(String classId,int page) {
		VideoDao dao = new VideoImp();
		return dao.findVideoByClassId(classId,page);
	}
	
	public static List<Video> findSpecialByLast(int count, int specialId) {
		VideoDao dao = new VideoImp();
		return dao.findSpecialByLast(count,specialId);
	}
	
	/**
	 * @param classId
	 * @return 根据分类id 查询相应视频
	 */
	public static List<Video> findVideoByClassId(int classId,int page) {
		VideoDao dao = new VideoImp();
		return dao.findVideoByClassId(classId,page);
	}
	
	public static List<Video> findVideoByIsRecommId(int isRecomm,int classId){
		VideoDao dao = new VideoImp();
		return dao.findVideoByIsRecommId(isRecomm, classId);
	}
	
	/**
	 * @param teacherId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTeacherId(int teacherId,int page){
		VideoDao dao=new VideoImp();
		return dao.findVideoByTeacherId(teacherId, page);
	}
	
	public static List<Video> findSpecialVideo(int page, int specialId) {
		VideoDao dao=new VideoImp();
		return dao.findSpecialVideo(page, specialId);
	}
	
	public static List<Video> findAllVideoByCommId(int commId,int page){
		VideoDao dao=new VideoImp();
		return dao.findAllVideoByCommId(commId, page);
	}
	
	public static List<Video> findLastVideoByCommId(int commId,int count){
		VideoDao dao=new VideoImp();
		return dao.findLastVideoByCommId(commId, count);
	}
	
	public static List<Video> findVideoByIsTopId(int isTop,int classId){
		VideoDao dao=new VideoImp();
		return dao.findVideoByIsTopId(isTop, classId);
	}
	
	/**
	 * @param typeId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByType(int typeId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByType(typeId, page);
	}
	
	/**
	 * @param teacherId
	 * @param type
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTeacherIdAndType(int teacherId, int type,
			int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTeacherIdAndType(teacherId, type, page);
	}
	
	/**
	 * @param teacherId
	 * @param classId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTeacherIdAndClassId(int teacherId,
			String classId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTeacherIdAndClassId(teacherId, classId, page);
	}
	
	/**
	 * @param teacherId
	 * @param classId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTeacherIdAndClassId(int teacherId,
			int classId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTeacherIdAndClassId(teacherId, classId, page);
	}
	
	/**
	 * @param type
	 * @param classId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTypeAndClassId(int type, String classId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTypeAndClassId(type, classId, page);
	}
	
	
	public static  Video findLastVideoByTeacher(int teacherId){
		VideoDao dao=new VideoImp();
		return dao.findLastVideoByTeacher(teacherId);
	}
	
	
	
	/**
	 * @param type
	 * @param classId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTypeAndClassId(int type, int classId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTypeAndClassId(type, classId, page);
	}
	
	/**
	 * @param type
	 * @param classId
	 * @param teacherId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTypeAndClassIdAndTeacherId(int type,
			String classId, int teacherId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTypeAndClassIdAndTeacherId(type, classId, teacherId, page);
	}
	
	/**
	 * @param type
	 * @param classId
	 * @param teacherId
	 * @param page
	 * @return
	 */
	public static List<Video> findVideoByTypeAndClassIdAndTeacherId(int type,
			int classId, int teacherId, int page) {
		VideoDao dao=new VideoImp();
		return dao.findVideoByTypeAndClassIdAndTeacherId(type, classId, teacherId, page);
	}
	

	/**
	 * @param id
	 * @return 根据id 查询视频详细信息
	 */
	public static Video findVideoById(int id) {
		VideoDao dao = new VideoImp();
		return dao.findVideoById(id);
	}

	/**
	 * @param isIndex
	 * @return   查询首页视频
	 */
	public static List<Video> findVideoByIsIndex(int isIndex) {
		VideoDao dao = new VideoImp();
		return dao.findVideoByIsIndex(isIndex);
	}
	/**
	 * @param isIndex
	 * @param classId
	 * @return   查询首页下的分类信息
	 */
	public static List<Video> findVideoByIsIndexId(int isIndex,int classId){
		VideoDao dao=new VideoImp();
		return dao.findVideoByIndexId(isIndex, classId);
	}
	/** 
	 * @param id
	 * @return  通过id获取视频播放资源
	 */ 
	public static Video findVideoResourceById(int id){ 
		VideoDao dao=new VideoImp();
		return dao.findVideoRecouresById(id);
	}
	
	/**
	 * @param count
	 * @param classId
	 * @return    根据分类id获取最近的视频信息
	 */
	public static List<Video> findVideoByClassIdLast(int count,int classId){
		VideoDao dao=new VideoImp();
		return dao.findVideoByClassIdLast(count, classId);
	}
	
	public static List<Video> findVideoByIsBestLast(int count, int isBest){
		VideoDao dao=new VideoImp();
		return dao.findVideoByIsBestLast(count, isBest);
	}
	
	
	
	public static List<Video>  findVideoByClassId(int classId){
		VideoDao dao=new VideoImp();
		return dao.findVideoByClassId(classId);
		
	}
	
	public static List<Video> findVideoByLast(int count){
		VideoDao dao=new VideoImp();
		return dao.findVideoByLast(count);
	}
	
	public static int updateHits(int hits,int xnHits,int id){
		VideoDao dao=new VideoImp();
		return dao.updateHits(hits, xnHits, id);
		
	}
	
}
