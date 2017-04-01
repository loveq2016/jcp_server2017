package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.CommentDao;
import com.jucaipen.daoimp.CommentsImp;
import com.jucaipen.model.Comment;

/**
 * @author Administrator
 *   
 *  JCP_LogOrLive_Comm 观点、文字直播 评论  回复信息
 */
public class CommentSer {

	/**
	 * @param comment
	 * @return 添加评论  回复信息
	 */
	public static  int insertComm(Comment comment) {
		CommentDao dao=new CommentsImp();
		return dao.insertComm(comment);
	}

	/**
	 * @param id
	 * @return 删除评论  回复信息
	 */
	public static int cancelComm(int id) {
		CommentDao dao=new CommentsImp();
		return dao.cancelComm(id);
	}

	/**
	 * @param uId
	 * @param type
	 * @param pager
	 * @param parentId
	 * @return  获取用户下的分类  评论  回复信息
	 */
	public static List<Comment> findComment(int uId, int type, int pager, int parentId) {
		CommentDao dao=new CommentsImp();
		return dao.findComment(uId, type, pager, parentId);
	}
	

	/**
	 * @param uId
	 * @param type
	 * @param pager
	 * @param parentId
	 * @return  获取用户下的分类  评论  回复信息
	 */
	public static List<Comment> findComment(int uId, int type, int page) {
		CommentDao dao=new CommentsImp();
		return dao.findComment(uId, type, page);
	}

	/**
	 * @param fkId
	 * @param pager
	 * @param parentId
	 * @param type
	 * @return  获取观点  文字直播  下的评论回复信息
	 */
	public static List<Comment> findCommenBykId(int fkId, int pager, int parentId,
			int type) {
		CommentDao dao=new CommentsImp();
		return dao.findCommenBykId(fkId, pager, parentId, type);
	}

	/**
	 * @param id
	 * @return 根据id获取评论  回复信息
	 */
	public static Comment findCommentById(int id) {
		CommentDao dao=new CommentsImp();
		return dao.findCommentById(id);
	}

	/**
	 * @param id
	 * @param goodNum
	 * @return 修改评论点赞数
	 */
	public static int updateCommentGoods(int id, int goodNum) {
		CommentDao dao=new CommentsImp();
		return dao.updateCommentGoods(id, goodNum);
	}

	/**
	 * @param id
	 * @param respCount
	 * @return  修改评论 回复数
	 */
	public static  int updateCommentRespCount(int id, int respCount) {
		CommentDao dao=new CommentsImp();
		return dao.updateCommentRespCount(id, respCount);
	}

}
