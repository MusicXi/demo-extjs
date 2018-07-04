package  com.myron.ims.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import com.myron.db.mybatis.bean.ExtjsPage;
import com.myron.ims.bean.UserRole;
import com.myron.ims.service.UserRoleService;


@Controller
@RequestMapping("/product/system/userRoleManager")
public class UserRoleController {
	//private static final Logger logger=LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;
	

	@RequestMapping("read.do")
	@ResponseBody
	public  Map<String, Object>read(String filter, ExtjsPage extjsPage){
		Map<String, Object> map=new HashMap<>();
		//TODO filter json转对象
		UserRole userRole=new UserRole();
	
		Map<String, Object> resultMap=this.userRoleService.findListByPage(userRole, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("records", resultMap.get("data"));
		return map;
	}
	
	@RequestMapping("create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<UserRole> list){
		Map<String, Object> map=new HashMap<>();
		for(UserRole userRole: list){
			userRole.preInsert();
			this.userRoleService.createUserRole(userRole);			
		}
		map.put("tip", "操作成功");
		map.put("valid", true);
		return map;
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<UserRole> list){
		Map<String, Object> map=new HashMap<>();
		int flag=0;
		for(UserRole userRole: list){
			flag=this.userRoleService.updateUserRole(userRole);
		}
		if(flag!=0){
			map.put("tip", "操作成功");
			map.put("valid", true);			
		}
		return map;
	}
		
	
	@RequestMapping("test.do")
	public void test(@RequestBody List<UserRole> list){
		String userId="";
		for(UserRole userRole: list){
			userId=userRole.getUserId();
		}
		this.userRoleService.clearUserRole(userId);
		for(UserRole userRole: list){
			this.userRoleService.createUserRole(userRole);
		}
		System.out.println("RoleController.test()");
	}
	
	
	
	
	
	
}
