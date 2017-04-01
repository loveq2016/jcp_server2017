package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.FavoritesDao;
import com.jucaipen.daoimp.FavoritesImp;
import com.jucaipen.model.Favorites;

public class FavoritesSer {
	/**
	 * @param uId
	 * @param nId
	 * @return 收藏信息
	 */
	public static int insertNews( Favorites newsFavorites) {
		FavoritesDao dao = new FavoritesImp();
		return dao.insertFavorites(newsFavorites);
	}

	/**
	 * @param uId
	 * @param nId
	 * @return 取消收藏新闻
	 */
	public static int cancelNews(int uId, int nId,int type) {
		FavoritesDao dao = new FavoritesImp();
		return dao.cancelFavorites(uId, nId,type);
	}

	/**
	 * @param uId
	 * @return 查询该用户下所有收藏的新闻信息
	 */
	public static List<Favorites> findNewsFavoByUser(int uId, int pager) {
		FavoritesDao dao = new FavoritesImp();
		return dao.findFavorites(uId, pager);
	}
	/**
	 * @param uId
	 * @param nId
	 * @return  查询新闻是否被收藏
	 */
	public static Favorites findNewsIsCollect(int uId,int nId,int type){
		FavoritesDao dao=new FavoritesImp();
		return dao.findFavouritesByUidAndNid(uId, nId,type);
	}
	
	/**
	 * @param uId
	 * @param type
	 * @param page
	 * @return 获取用户下收藏的分类信息
	 */
	public static List<Favorites> findFavourateByUidAndType(int uId,int type,int page){
		FavoritesDao dao=new FavoritesImp();
		return dao.findFavourateByUidAndType(uId, type, page);
	}

}
