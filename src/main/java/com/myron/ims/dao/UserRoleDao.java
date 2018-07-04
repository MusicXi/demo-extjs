package com.myron.ims.dao;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.UserRole;

@MyBatisRepository
public interface UserRoleDao extends BaseMybatisDao<UserRole>{
	public int deleteByUserId(String userId);
	public int deleteByRoleId(String roleId);
}
