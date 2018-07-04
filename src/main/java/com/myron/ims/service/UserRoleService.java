package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.UserRole;

public interface UserRoleService {
	//增删改
	public int createUserRole(UserRole userRole);
	public int updateUserRole(UserRole userRole);
	
	public int deleteUserRole(UserRole userRole);
	
	/**
	 * 清除用户所有角色权限
	 * @param userId
	 * @return
	 */
	public int clearUserRole(String userId);
	
	/**
	 * 清除该角色所有用户的角色权限
	 * @param roleId
	 * @return
	 */
	public int clearRoleUser(String roleId);
	
	//查询
	public UserRole findUserRoleByPrimaryKey(String id);
	public Map<String, Object> findListByPage(UserRole userRole, Page page);
	public Map<String, Object> findMapListByPage(UserRole userRole, Page page);
	public List<Map<String, Object>> findMapList(UserRole userRole);
	public List<UserRole> findList(UserRole userRole);
	

	
}
