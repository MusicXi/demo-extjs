package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.Role;
import com.myron.ims.dao.RoleDao;
import com.myron.ims.service.RoleService;

@Service("roleService")
public class RoleServiceImpl  implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int createRole(Role role) {
		return this.roleDao.insertSelective(role);
	}
	
	@Override
	public int updateRole(Role role) {
		return this.roleDao.updateByPrimaryKeySelective(role);
	}

	@Override
	public int deleteRole(String id) {
		return this.roleDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteRole(Role role) {
		return this.roleDao.deleteByPrimaryKey(role.getRoleId());
	}


	@Override
	public Map<String, Object> findListByPage(Role role, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("role", role);
		map.put("page", page);
		List<Map<String, Object>> list=this.roleDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList() {
		Role role=new Role();
		return this.roleDao.selectMapList(role);
	}
	
	@Override
	public List<Role> findList(){
		return this.roleDao.selectList(new Role());
	}

	@Override
	public List<Role> listRoleByUserId(String userId) {
		return this.roleDao.selectListByUserId(userId);
	}






}
