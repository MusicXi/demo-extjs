package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.RoleResource;

public interface RoleResourceService {
	//增删改
	public int createRoleResource(RoleResource roleResource);
	public int updateRoleResource(RoleResource roleResource);
	
	public int deleteRoleResource(String id);
	
	//查询
	public RoleResource findRoleResourceByPrimaryKey(String id);
	List<RoleResource> findRoleResourceByRoleId(String roleId);
	public Map<String, Object> findListByPage(RoleResource roleResource, Page page);
	public Map<String, Object> findMapListByPage(RoleResource roleResource, Page page);
	public List<Map<String, Object>> findMapList(RoleResource roleResource);
	public List<RoleResource> findList(RoleResource roleResource);
	

	
}
