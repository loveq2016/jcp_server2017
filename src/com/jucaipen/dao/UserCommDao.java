package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.UserComm;
/**
 * @author ylf
 * 
 *         证券 和  视频 评论
 * 
 */
public interface UserCommDao {
	/**
	 * @param uId
	 * @param nId
	 * @return 添加评论   回复
	 */
	public int insertComm( UserComm comment);
	

	/**
	 * @param uId
	 * @param nId
	 * @return 删除评论  回复
	 */
	public int cancelComm(int id);
	
	/**
	 * @param uId
	 * @return 查询当前用户下所有的证券视频 评论
	 */
	public List<UserComm> findComment(int uId, int type, int page);

	/**
	 * @param uId
	 * @return 查询当前用户下所有的证券视频 评论
	 */
	public List<UserComm> findComment(int uId, int type, int pager,int parentId);

	/**
	 * @param nId
	 * @return 查询当前证券 视频下所有评论  回复
	 */
	public List<UserComm> findCommenBykId(int fkId, int pager,int parentId,int type);

	/**
	 * @param id
	 * @return  根据id获取评论 回复信息
	 */
	public UserComm findCommentById(int id);

	/**
	 * @param id
	 * @return 修改评论信息  回复信息  点赞数
	 */
	public int updateCommentGoods(int id, int goodNum);
	
	/**
	 * @param id
	 * @return 修改评论信息 回复数
	 */
	public int updateCommentRespCount(int id, int respCount);
	
}
