package com.myron.ims.service;

import java.util.List;
import java.util.Set;

import com.myron.ims.bean.Resource;
import com.myron.ims.bean.User;

public interface ResourceService {
	public int createResource(Resource resource);
	public int deleteResource(Resource resource);
	public int updateResource(Resource resource);
	
	public List<Resource> getChildMenu(Resource resource);
	public List<Resource> getChildren(Resource resource);
	
	
	/**
	 * 得到资源对应的权限字符串
	 * @param resourceIds
	 * @return
	 */
	public Set<String> findPermissions(List<Resource> resourceList);
	
	/**
	 * 根据用户权限字符串集合得到菜单
	 * @param permissions
	 * @return
	 */
	public List<Resource> findMenus(Set<String> permissions);
	
	/**
	 * 查询 用户拥有的权限字符串集合
	 * @param user
	 * @return
	 */
	public Set<String> findPermissions(User user);
	
	/**
	 * 根据用户ID获取菜单
	 * @param user
	 * @return
	 */
	public List<Resource> findMenusByUser(User user);
}
