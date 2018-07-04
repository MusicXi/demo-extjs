package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Role;

public interface RoleService {
	//增删改
	public int createRole(Role role);
	public int updateRole(Role role);
	public int deleteRole(Role role);
	public int deleteRole(String id);
	
	//查询
	public Map<String, Object> findListByPage(Role role, Page page);
	public List<Map<String, Object>> findMapList();
	public List<Role> findList();
	
	List<Role> listRoleByUserId(String userId);
	

	
}
