package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.annotation.SystemServiceLog;
import com.myron.ims.bean.Role;
import com.myron.ims.bean.User;
import com.myron.ims.dao.UserDao;
import com.myron.ims.service.ResourceService;
import com.myron.ims.service.UserService;

@Service("userService")
public class UserServiceImpl  implements UserService{
	//private  static  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ResourceService resourceService;

	@Override
	@SystemServiceLog(description = "查询用户") 
	public User login(String name, String password) throws Exception{
			
			if(name==null ||"".equals(name) ||
				password==null ||"".equals(password)){
				return null;
			}else{
				List<User> users=userDao.findByLogin(name, this.Md5Hash(password, name));
				if(users.size()>0){
					for(User u: users){
						if(this.Md5Hash(password, name).equals(u.getPassword())){
							return u;
						}
					}					
				}		
			}				
			return null;
	}



	@Override
	public int validName(User user) {
		return this.userDao.countByName(user);
		//return 0;
	}

	@Override
	public Map<String, Object> findListByPage(User user, Page page) {
		/*Map<String, Object> map=new HashMap<String, Object>();
		user.setPage(page);
		List<User> list=this.userDao.findListByPage(user);
		map.put("data", list);
		map.put("page", page);
		
		return map;*/
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user", user);
		map.put("page", page);
		List<Map<String, Object>> list=this.userDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}



	@Override
	public int createUser(User user) {
		user.setPassword(this.Md5Hash("123", user.getUsername()));// 设置默认密码
		return userDao.insertSelective(user);
	}



	@Override
	public int changePassword(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}



	@Override
	public int addRoles(User user, Role role) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int removeRoles(User user, Role role) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Role> findRoles(User user) {
		if(user!=null){
			return null;
		}
		return null;
	}

	/**
	 * 设置MD5密码
	 * 
	 */
	public String Md5Hash(String password, String username) {
		//构造方法中：
		//第一个参数：明文，原始密码 
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(password, username, 1);
		return md5Hash.toString();
	}




	@Override
	public int deleteUser(String id) {
		return this.userDao.deleteByPrimaryKey(id);
	}



	@Override
	public int deleteUser(User user) {
		return this.userDao.deleteByPrimaryKey(user.getId());
	}



	@Override
	public Set<String> findPermissions(User user) {
		//return this.roleService.findPermissions(user.getRoleList());
		return this.resourceService.findPermissions(user);
	}



	@Override
	public List<User> dataGrid() {
		User user=new User();
		return this.userDao.selectList(user);
	}



	@Override
	public int updateUser(User user) {
		return this.userDao.updateByPrimaryKeySelective(user);
	}



	@Override
	public List<Map<String, Object>> findList() {
		User user=new User();
		return this.userDao.selectMapList(user);
	}






}
