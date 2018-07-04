package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Article;
import com.myron.ims.dao.ArticleDao;
import com.myron.ims.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl  implements ArticleService{
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public int createArticle(Article article) {
		return this.articleDao.insertSelective(article);
	}
	
	@Override
	public int updateArticle(Article article) {
		return this.articleDao.updateByPrimaryKeySelective(article);
	}


	@Override
	public int deleteArticle(String id) {
		return this.articleDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public Article findArticleByPrimaryKey(String id) {
		return this.articleDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findMapListByPage(Article article, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("article", article);
		map.put("page", page);
		List<Map<String, Object>> list=this.articleDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}
	
	@Override
	public Map<String, Object> findListByPage(Article article, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("article", article);
		map.put("page", page);
		List<Article> list=this.articleDao.selectListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList(Article article) {
		return this.articleDao.selectMapList(article);
	}
	
	@Override
	public List<Article> findList(Article article){
		return this.articleDao.selectList(article);
	}






}
