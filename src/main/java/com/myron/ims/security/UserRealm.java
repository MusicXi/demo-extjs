package com.myron.ims.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.myron.ims.bean.Role;
import com.myron.ims.bean.User;
import com.myron.ims.service.ResourceService;
import com.myron.ims.service.UserService;

/**
 * 系统安全认证实现类
 * @author Administrator
 *
 */
public class UserRealm extends AuthorizingRealm{

	
	private UserService userService;
	
	private ResourceService resourceService;
	
	
	/*@Autowired
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
		super.setCredentialsMatcher(credentialsMatcher);
	}*/
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * 获取身份验证信息
	 * 认证回调函数, 登录时调用(LoginController.login()中执行 subject.login()时):
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面subject.login(token)传过来的  
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findByUsername(token.getUsername());
		
		if (user == null) {
			throw new UnknownAccountException();		//没有找到账号	
		}
		
		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException();			//账号锁定
		}
		
		String salt=user.getUsername();
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
				user, 
				user.getPassword(),
				ByteSource.Util.bytes(salt),
				getName()								//realm name	
		);		

		return authenticationInfo;
	}
	/**
	 * 根据用户身份获取授权信息
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user =  (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.setRoles(userService.findRoles(user));
		//XXX 权限资源来自resourceService服务,直接调用避免服务之间的依赖
        //info.setStringPermissions(userService.findPermissions(user));
		info.setStringPermissions(this.resourceService.findPermissions(user));
		for (Role role: user.getRoleList()) {
			info.addRole(role.getRole());//添加用户的角色信息
			//info.addStringPermissions(role.getPermissionList());//添加角色的授权资源信息
		}
		//info.addStringPermission("user:read");
		return info;
	}


}
