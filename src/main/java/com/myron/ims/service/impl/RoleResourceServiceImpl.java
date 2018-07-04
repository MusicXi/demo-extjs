package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.RoleResource;
import com.myron.ims.dao.RoleResourceDao;
import com.myron.ims.service.RoleResourceService;

@Service("roleResourceService")
public class RoleResourceServiceImpl  implements RoleResourceService{
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Override
	public int createRoleResource(RoleResource roleResource) {
		return this.roleResourceDao.insertSelective(roleResource);
	}
	
	@Override
	public int updateRoleResource(RoleResource roleResource) {
		return this.roleResourceDao.updateByPrimaryKeySelective(roleResource);
	}


	@Override
	public int deleteRoleResource(String id) {
		return this.roleResourceDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public RoleResource findRoleResourceByPrimaryKey(String id) {
		return this.roleResourceDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findMapListByPage(RoleResource roleResource, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("roleResource", roleResource);
		map.put("page", page);
		List<Map<String, Object>> list=this.roleResourceDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}
	
	@Override
	public Map<String, Object> findListByPage(RoleResource roleResource, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("roleResource", roleResource);
		map.put("page", page);
		List<RoleResource> list=this.roleResourceDao.selectListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList(RoleResource roleResource) {
		return this.roleResourceDao.selectMapList(roleResource);
	}
	
	@Override
	public List<RoleResource> findList(RoleResource roleResource){
		return this.roleResourceDao.selectList(roleResource);
	}

	@Override
	public List<RoleResource> findRoleResourceByRoleId(String roleId) {
		return this.roleResourceDao.selectListByRoleId(roleId);
	}






}
