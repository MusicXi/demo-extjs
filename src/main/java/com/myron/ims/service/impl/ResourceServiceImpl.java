package com.myron.ims.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myron.ims.bean.Resource;
import com.myron.ims.bean.User;
import com.myron.ims.dao.ResourceDao;
import com.myron.ims.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
	private static final String CACHE="ResourceCache";
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public int createResource(Resource resource) {
		return this.resourceDao.insert(resource);
	}

	@Override
	public int deleteResource(Resource resource) {
		return this.resourceDao.deleteByPrimaryKey(resource.getId());
	}

	@CacheEvict(value=CACHE, allEntries = true)//移除所有数据  
	@Override
	public int updateResource(Resource resource) {
		return this.resourceDao.updateByPrimaryKey(resource);
	}

	@Override
	public Set<String> findPermissions(List<Resource> resourceList) {
		Set<String> permissions=new HashSet<String>();
		for(Resource resource: resourceList){
			resource=this.resourceDao.selectByPrimaryKey(resource.getId());
			if(resource!=null){
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		List<Resource> menuList = new ArrayList<Resource>();
		Resource menu=null;
		/*List<Resource> allMenu=this.resourceDao.findAll();
		
		for(Resource menu: allMenu){
			
		}*/
		for(String permission: permissions){
			menu=this.resourceDao.findbyPermission(permission);
			menuList.add(menu);
		}
		return menuList;
	}
	
	
	@Override
	public List<Resource> findMenusByUser(User user) {
		return this.resourceDao.findMenuByUser(user);
	}

	@Override
	public Set<String> findPermissions(User user) {
		Set<String> permissions=new HashSet<String>();
		List<Resource> resourceList=this.resourceDao.findListByUser(user);
		if(resourceList!=null){//资源列表不为null,获取权限标识集合
			for(Resource resource: resourceList){
				if(!(resource==null || "".equals(resource.getPermission()) || resource.getPermission()==null)){
					permissions.add(resource.getPermission());
				}
			}			
			return permissions;
		}
			
		return null;	
	}

	@Cacheable(value=CACHE, key="#menu.id")
	@Override
	public List<Resource> getChildMenu(Resource menu) {
		return this.resourceDao.getChildMenu(menu);
	}

	@Override
	public List<Resource> getChildren(Resource resource) {
		return this.resourceDao.getChildren(resource);
	}



}
