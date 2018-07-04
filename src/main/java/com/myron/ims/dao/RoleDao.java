package com.myron.ims.dao;

import java.util.List;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.Role;

@MyBatisRepository
public interface RoleDao extends BaseMybatisDao<Role>{
	List<Role> selectListByUserId(String id);

}
