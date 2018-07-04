package com.myron.ims.dao;

import java.util.List;
import java.util.Map;

import com.myron.db.mybatis.annotation.MyBatisRepository;
import com.myron.db.mybatis.dao.BaseMybatisDao;
import com.myron.ims.bean.User;



@MyBatisRepository
public interface UserDao extends BaseMybatisDao<User>{
	/**
	 * 根据用户名和密码查询用户
	 * @param user
	 * @return
	 */
	public List<User> findByLogin(String name, String password);
	
	/**
	 * 根据账号查询用户信息
	 * @param loginName
	 * @return
	 */
	public User findByUsername(String username);
	
	
	public List<User> findAllTest(User user);
	
	/**
	 * 同名用户数
	 * @param user
	 * @return
	 */
	public int countByName(User user);
	
	/**
	 * map封装分页信息和查询参数
	 * @param map
	 * @return
	 */
	public List<User> findAllByPage(Map<String, Object> map);
	
	//public int createSelective(User user);
	
	int insertByBatch(List<User> list);
	
}
