package com.myron.ims.dao;

import java.util.List;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.RoleResource;

@MyBatisRepository
public interface RoleResourceDao extends BaseMybatisDao<RoleResource>{
	List<RoleResource> selectListByRoleId(String roleId);
}
