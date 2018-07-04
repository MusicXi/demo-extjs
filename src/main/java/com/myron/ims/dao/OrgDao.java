package com.myron.ims.dao;



import java.util.List;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.Organization;



@MyBatisRepository
public interface OrgDao extends BaseMybatisDao<Organization>{

	public List<Organization> getChildren(String id);

}
