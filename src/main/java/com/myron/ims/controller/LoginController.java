package com.myron.ims.controller;
import java.awt.image.RenderedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myron.ims.annotation.SystemControllerLog;
import com.myron.ims.bean.User;
import com.myron.ims.config.Global;
import com.myron.ims.exception.WrongVerifyCodeException;
import com.myron.ims.service.UserService;
import com.myron.ims.util.ValidateUtils;


@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**session中验证码的变量*/
	public static final String VERIFICATION_CODE = "verificationCode";
	/**session中用户的变量*/
	public static final String CURRENT_USER = "currrentUser";
	/**登入页*/
	public static final String LOGIN_PAGE="/login.jsp";
	/**首页*/
	public static final String INDEX_PAGE="/index_ims.jsp";
	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转用户管理界面
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLoginView(HttpServletRequest request, ModelMap map){
		if(Global.isDevelopMode()){
			UsernamePasswordToken token=new UsernamePasswordToken("ims", "ims");
			Subject subject=SecurityUtils.getSubject();
			subject.login(token);
			User user=(User) subject.getPrincipal();
			Session session = subject.getSession();
			session.setAttribute("ims_user", user);
			logger.info("开发模式 登入系统 用户名:{}", "ims");
			return INDEX_PAGE;
		}	
		logger.info("当前为产品模式");
		return LOGIN_PAGE;
	}
	
	/**
	 * 系统登入
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @param verifycode
	 * @param req
	 * @return
	 * @throws WrongVerifyCodeException 
	 */
	@SystemControllerLog(description="登入系统")
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(String username, String password, Boolean rememberMe, String verifycode, HttpServletRequest req) throws WrongVerifyCodeException{		
		Map<String, Object> map=new HashMap<>();
		//校验验证码
		checkVerifycode(req,verifycode);
		
		//身份验证
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		token.setRememberMe(rememberMe==null?false:rememberMe);
		Subject subject=SecurityUtils.getSubject();
		try{
			subject.login(token);
			User user=(User) subject.getPrincipal();
			Session session = subject.getSession();//使用shiro的session不依赖任何底层容器,可以独立使用
			session.setAttribute("ims_user", user);
			//session.setTimeout(1000);//设置过期时间
			logger.info("用户:{} - 认证登入  :{} - 是否记住我:{} - 主机:{} - SessionId:{}",
					user.getUsername(),subject.isAuthenticated(), subject.isRemembered(),session.getHost(),session.getId());
			map.put("view", "index_ims.jsp");
			map.put("success", true);
			return map;
		}catch(UnknownAccountException e){
			logger.error(e.getMessage());
			map.put("msg", "用户名不存在!");
			e.printStackTrace();
		}catch(LockedAccountException e){
			logger.error(e.getMessage());
			map.put("msg", "用户被锁定!");
			e.printStackTrace();
		}catch(AuthenticationException e){
			logger.error(e.getMessage());
			String msg=checkUserLoginTimes(subject);
			map.put("msg",msg);
			e.printStackTrace();
		}
		map.put("success", false);
		return map;
	}
	
	/**
	 * 安全退出登入
	 * @return
	 */
	@SystemControllerLog(description="安全退出系统")
	@RequestMapping("logout.do")
	public String logout(){
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return LOGIN_PAGE;
	}
	
	/**
	 * 无权限  (结合:SimpleMappingExceptionResolver 使用)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("unauthorized.do")
	@ResponseBody
	@Deprecated
	public Map<String, Object> refuse(HttpServletRequest request) throws Exception{

		//TODO 非ajax请求 转发无权限页面
		//return "/404.jsp";//重定向到登录页面
		
		//XXX ajax请求  return json格式的错误信息 
		System.out.println(request.getAttribute("exception"));
		Exception exception=(Exception) request.getAttribute("exception");
		Map<String, Object> map=new HashMap<>();
		map.put("msg", exception+" 您没有操作权限,请联系管理员!");
		map.put("success", false);
		return map;
	}
	
	/**
	 * 检查用户登入次数
	 * @param subject
	 * @return
	 */
	private String checkUserLoginTimes(Subject subject){
		Session session = subject.getSession();
		int count=session.getAttribute("loginTimes")==null?1:(int)session.getAttribute("loginTimes");
		session.setAttribute("loginTimes", count+1);
		return "密码不正确,次数:"+count;
	}
	
	
	
	@RequestMapping("verificationCode.do")
	public void randCodeImage(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object[] results = ValidateUtils.getRandcode();
		session.removeAttribute(VERIFICATION_CODE);
		session.setAttribute(VERIFICATION_CODE, results[0]);
		try {
			ImageIO.write((RenderedImage) results[1], "JPEG",
					response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验验证码
	 * @param request
	 * @param verifycode
	 * @throws Exception 
	 */
	private void checkVerifycode(HttpServletRequest request,String verifycode) throws WrongVerifyCodeException{
		HttpSession session = request.getSession();
		String code = session.getAttribute(VERIFICATION_CODE) + "";
		if(!code.toLowerCase().equals(verifycode.toLowerCase())){
			throw new WrongVerifyCodeException("验证码不正确,请重新输入");
		}
	}
	
	
}
