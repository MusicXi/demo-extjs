package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.UserRole;
import com.myron.ims.dao.UserRoleDao;
import com.myron.ims.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl  implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public int createUserRole(UserRole userRole) {
		return this.userRoleDao.insertSelective(userRole);
	}
	
	@Override
	public int updateUserRole(UserRole userRole) {
		return this.userRoleDao.updateByPrimaryKeySelective(userRole);
	}


	@Override
	public int deleteUserRole(UserRole userRole) {
		return this.userRoleDao.deleteByPrimaryKey(userRole.getUserId());
	}
	
	@Override
	public UserRole findUserRoleByPrimaryKey(String id) {
		return this.userRoleDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findMapListByPage(UserRole userRole, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userRole", userRole);
		map.put("page", page);
		List<Map<String, Object>> list=this.userRoleDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}
	
	@Override
	public Map<String, Object> findListByPage(UserRole userRole, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userRole", userRole);
		map.put("page", page);
		List<UserRole> list=this.userRoleDao.selectListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList(UserRole userRole) {
		return this.userRoleDao.selectMapList(userRole);
	}
	
	@Override
	public List<UserRole> findList(UserRole userRole){
		return this.userRoleDao.selectList(userRole);
	}

	@Override
	public int clearUserRole(String userId) {
		return this.userRoleDao.deleteByUserId(userId);
	}

	@Override
	public int clearRoleUser(String roleId) {
		return this.userRoleDao.deleteByRoleId(roleId);
	}






}
