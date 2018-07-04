package com.myron.ims.dao;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.DictionaryItem;


@MyBatisRepository
public interface DictionaryDao extends BaseMybatisDao<DictionaryItem>{


}
