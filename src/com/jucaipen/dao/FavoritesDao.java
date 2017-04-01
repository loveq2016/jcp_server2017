package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Favorites;
/**
 * @author ylf
 * 
 *   收藏
 *
 */
public interface FavoritesDao {
	/**
	 * @param uId
	 * @param nId
	 * @return  添加收藏
	 */
	public int insertFavorites(Favorites newsFavorites);
	/**
	 * @param uId
	 * @param nId
	 * @return 取消收藏
	 */
	public int cancelFavorites(int uId,int nId,int type);
	/**
	 * @param uId
	 * @return  查询当前用户下所有的收藏
	 */
	public List<Favorites> findFavorites(int uId,int pager);
	/**
	 * @param uId
	 * @param nId
	 * @return  根据用户id、知识/视频id查询收藏信息
	 */
	public Favorites findFavouritesByUidAndNid(int uId,int nId,int type);
	
	/**
	 * @param uId
	 * @param type
	 * @param page
	 * @return  根据用户id查询分类下的收藏
	 */
	public List<Favorites> findFavourateByUidAndType(int uId,int type ,int page);

}
