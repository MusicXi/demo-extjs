package com.myron.ims.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myron.common.util.ExcelUtil;
import com.myron.common.util.StringUtils;
import com.myron.common.util.JsonUtil;
import com.myron.db.mybatis.bean.ExtjsPage;
import com.myron.ims.annotation.SystemControllerLog;
import com.myron.ims.bean.DictionaryItem;
import com.myron.ims.bean.Role;
import com.myron.ims.bean.User;
import com.myron.ims.service.DictionaryService;
import com.myron.ims.service.RoleService;
import com.myron.ims.service.UserService;

@Controller
@RequestMapping("/product/system/userManager")
public class UserController {
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DictionaryService dictionaryService;
	

	
	@RequiresPermissions("user:read")
	@RequestMapping("read.do")
	@ResponseBody
	public  Map<String, Object>read(String filter, ExtjsPage extjsPage){
		Map<String, Object> map=new HashMap<>();
		//TODO 设置默认排序
		User user=JsonUtil.toBean(filter, User.class);

		if(user==null){
			user=new User();
		}
		Map<String, Object> resultMap=this.userService.findListByPage(user, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("records", resultMap.get("data"));
		return map;
	}
	
	
	@RequiresPermissions("user:create")
	@SystemControllerLog(description="用户管理-添加用户")
	@RequestMapping("create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<User> list){
		Map<String, Object> map=new HashMap<>();
		for(User user: list){
			user.preInsert();
			this.userService.createUser(user);			
		}
		map.put("msg", "添加用户成功");
		map.put("valid", true);
		return map;
	}
	
	@RequiresPermissions("user:update")
	@SystemControllerLog(description="用户管理-修改用户信息")
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<User> list){
		Map<String, Object> map=new HashMap<>();
		int flag=0;
		for(User user: list){
			flag=this.userService.updateUser(user);
		}
		if(flag!=0){
			map.put("msg", "修改用户成功");
			map.put("valid", true);			
		}
		return map;
	}
	
	@SystemControllerLog(description="用户管理-删除用户")
	@RequestMapping("destroy.do")
	@ResponseBody
	public Map<String, Object> destroy(@RequestBody List<User> list){
		Map<String, Object> map=new HashMap<>();
		int count=0;
		for(User user: list){
			count=count+this.userService.deleteUser(user);
		}
		map.put("count", count);//返回成功删除个数
		return map;
	}
	
	@RequestMapping("getDictItem.do")
	@ResponseBody
	public Map<String, Object> getDictItem(DictionaryItem item){
		Map<String, Object> map=new HashMap<>();
		List<DictionaryItem> list=this.dictionaryService.getDictItem(item);
		map.put("records", list);//返回成功删除个数
		return map;
	}
	
	@RequestMapping("getUserById.do")
	@ResponseBody
	public Map<String, Object> getUserById(User user){
		Map<String, Object> map=new HashMap<>();
		User u=this.userService.findByUsername(user.getUsername());
		map.put("data", u);//返回成功删除个数
		map.put("success", true);
		return map;
	}
	
	/**
	 * 导出Excel文件
	 * @param req
	 * @param resp
	 * @param model
	 */
	@RequestMapping("exportExcel.do")
	public void exportExcel(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		
		resp.setContentType("application/x-msdownload");
		try {
			ServletOutputStream output=resp.getOutputStream();
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = String.valueOf(simpleDateFormat.format(date));
			String fileName=StringUtils.toUtf8String("User_"+dateStr);
			//String fileName="Test";
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");
			
			List<Map<String,Object>> fieldData=this.userService.findList();
			Map<String,String> fieldName=new LinkedHashMap<String,String>();
			fieldName.put("createDate", "创建日期");
			fieldName.put("userId", "用户ID");
			fieldName.put("username", "用户名");
			fieldName.put("name", "姓名");
			fieldName.put("email", "邮件");

			ExcelUtil.export(fieldName, fieldData, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * excel导入
	 * TODO 导入成功回调提示
	 * @param req
	 * @param file
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("importExcel.do")
	@ResponseBody
	public Map<String, Object> importExcel(HttpServletRequest req, 
			@RequestParam(value="excelFile", required=false) MultipartFile file){
		logger.debug("执行导入操作开始");
		Map<String, Object> map=new HashMap<>();
		CommonsMultipartFile commonsMultipartFile=(CommonsMultipartFile) file;
		try {
			List<Map<String,Object>> list=ExcelUtil.readExcelData(commonsMultipartFile);
			for(Map<String,Object> obj:list){
				User user=new User();
				BeanUtils.populate(user, obj);
				//User user=(User) BeanToMapUtil.convertMap(User.class,obj);
				user.preInsert();
				this.userService.createUser(user);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		map.put("msg", "导入成功啦啦啦");
		map.put("success", true);
		return map;
	}

	/**
	 * 下载模板
	 * @param model
	 * @param req
	 * @param resp
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("downloadTemplate.do")
	public void downloadTemplate(String model,HttpServletRequest req, HttpServletResponse resp){
		Map<String, String> fieldName;
		try {
			if(model!=null && !"".equals(model) ){
				ObjectMapper mapper = new ObjectMapper();
				fieldName = mapper.readValue(model, LinkedHashMap.class);								
			}else{
				fieldName=new LinkedHashMap<String,String>();
				fieldName.put("createDate", "创建日期");
				fieldName.put("userId", "用户ID");
				fieldName.put("username", "用户名");
				fieldName.put("name", "姓名");
				fieldName.put("email", "邮件");
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName=StringUtils.toUtf8String("userTemplate");

			resp.setContentType("application/x-msdownload");
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");

			ExcelUtil.exportTemplate(fieldName, outputStream);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	

	/**
	 * 查询用户拥有的所有角色信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("findRoleByUserId.do")
	@ResponseBody
	public Map<String, Object> findRoleByUserId(String userId){
		Map<String, Object> map=new HashMap<>();
		System.out.println("UserController.findRoleByUserId(): "+userId);
		List<Role> list=this.roleService.listRoleByUserId(userId);
		map.put("records", list);
		return map;
	}
	
	/**
	 * 查询所有用户未委派的角色列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("findUnselectedRole.do")
	@ResponseBody
	public Map<String, Object> findUnselectedRole(String userId){
		Map<String, Object> map=new HashMap<>();
		System.out.println("UserController.findUnselectedRole(): "+userId);
		List<Role> list=this.roleService.listRoleByUserId(userId);
		List<Role> all=this.roleService.findList();
		Iterator<Role> iterator = all.iterator();  
	    while(iterator.hasNext()){
	    	Role role=iterator.next();
	    	for(Role r: list){
	    		if(role.getRoleId().equals(r.getRoleId())){
	    			iterator.remove();
	    		}
	    	}
	    }  
		map.put("records", all);
		return map;
	}
	
}
