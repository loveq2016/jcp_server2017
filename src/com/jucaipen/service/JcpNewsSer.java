package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.JcpNewsDao;
import com.jucaipen.daoimp.JcpNewsImp;
import com.jucaipen.model.JcpNews;

public class JcpNewsSer{

	public static List<JcpNews> findAll(int pager) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findAll(pager);
	}

	public static List<JcpNews> findNewsBybigId(int bigId, int smallId, int pager) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNewsBybigId(bigId, smallId, pager);
	}
	
	public static JcpNews findHitsAndGoods(int id) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findHitsAndGoods(id);
	}
	
	public static int addGoods(int id, int goods){
		JcpNewsDao dao=new JcpNewsImp();
		return dao.addGoods(id, goods);
	}

	public static List<JcpNews> findNewsByIndex(int isIndex, int pager) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNewsByIndex(isIndex, pager);
	}

	public static List<JcpNews> findNewsByBest(int isBest, int pager) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNewsByBest(isBest, pager);
	}

	public static List<JcpNews> findNewsByTop(int isTop, int pager) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNewsByTop(isTop, pager);
	}

	public static JcpNews findNews(int id) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNews(id);
	}

	public static List<JcpNews> findNewsIndex(int bigId, int smallId, int isIndex) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findNewsIndex(bigId, smallId, isIndex);
	}

	public static List<JcpNews> findRelatedNewsById(int id,int top) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findRelatedNewsById(id,top);
	}

	public static List<JcpNews> findLastNewsByNewsNum(int count) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.findLastNewsByNewsNum(count);
	}

	public static int upDateHits(int xnHits, int hits, int id) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.upDateHits(xnHits, hits, id);
	}

	public static int upDateComments(int Commens, int id) {
		JcpNewsDao dao=new JcpNewsImp();
		return dao.upDateComments(Commens, id);
	}

}
