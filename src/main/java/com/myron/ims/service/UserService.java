package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.Role;
import com.myron.ims.bean.User;

public interface UserService {
	//增删改
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	public int deleteUser(String id);
	
	//查询
	public Map<String, Object> findListByPage(User user, Page page);
	public List<Map<String, Object>> findList();
	public List<User> dataGrid();
	
	//登入权限相关
	public User login(String name, String password) throws Exception; 
	public User findByUsername(String username);
	public Set<String> findPermissions(User user);
	public int validName(User user);
	

	
	//未使用
	public int changePassword(User user);
	public int addRoles(User user, Role role);
	public int removeRoles(User user, Role role);
	public List<Role> findRoles(User user);
	
}
