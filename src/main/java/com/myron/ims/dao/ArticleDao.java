package com.myron.ims.dao;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.Article;

@MyBatisRepository
public interface ArticleDao extends BaseMybatisDao<Article>{

}
