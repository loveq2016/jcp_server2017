package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Comment;
/**
 * @author Administrator
 *
 * 观点、文字直播 评论       JCP_LogOrLive_Comm
 */
public interface CommentDao {
	/**
	 * @param uId
	 * @param nId
	 * @return 添加评论   回复
	 */
	public int insertComm( Comment comment);

	/**
	 * @param uId
	 * @param nId
	 * @return 删除评论  回复
	 */
	public int cancelComm(int id);

	/**
	 * @param uId
	 * @return 查询当前用户下所有的观点、文字直播 评论评论、回复
	 */
	public List<Comment> findComment(int uId, int type, int pager,int parentId);
	
	/**
	 * @param uId
	 * @return 查询当前用户下所有的观点、文字直播 评论评论、回复
	 */
	public List<Comment> findComment(int uId, int type, int page);

	/**
	 * @param nId
	 * @return 查询当前证券 视频下所有评论  回复
	 */
	public List<Comment> findCommenBykId(int fkId, int pager,int parentId,int type);

	/**
	 * @param id
	 * @return  根据id获取评论 回复信息
	 */
	public Comment findCommentById(int id);

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
