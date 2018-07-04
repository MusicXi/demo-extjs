package com.myron.ims.dao;



import java.util.List;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.Resource;
import com.myron.ims.bean.User;



@MyBatisRepository
public interface ResourceDao extends BaseMybatisDao<Resource>{
	public Resource findbyPermission(String permisson);
	
	/**
	 * 根据用户ID 获取菜单资源列表
	 * @param user
	 * @return
	 */
	public List<Resource> findMenuByUser(User user);
	
	/**
	 * 根据用户ID 获取所有资源列表
	 * @param user
	 * @return
	 */
	public List<Resource> findListByUser(User user);
	
	/**
	 * 获取下级菜单资源
	 * @param menu
	 * @return
	 */
	public List<Resource> getChildMenu(Resource menu);
	
	/**
	 * 获取下级所有资源
	 * @param resource
	 * @return
	 */
	public List<Resource> getChildren(Resource resource);
}
