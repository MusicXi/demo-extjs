package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Article;

public interface ArticleService {
	//增删改
	public int createArticle(Article article);
	public int updateArticle(Article article);
	
	public int deleteArticle(String id);
	
	//查询
	public Article findArticleByPrimaryKey(String id);
	public Map<String, Object> findListByPage(Article article, Page page);
	public Map<String, Object> findMapListByPage(Article article, Page page);
	public List<Map<String, Object>> findMapList(Article article);
	public List<Article> findList(Article article);
	

	
}
