package com.myron.ims.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myron.ims.bean.Resource;
import com.myron.ims.bean.RoleResource;
import com.myron.ims.service.ResourceService;
import com.myron.ims.service.RoleResourceService;

@Controller
@RequestMapping("/product/system/resourceManager")
public class ResourceController {
	private static final Logger logger=LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@RequestMapping("/getResourceGrid.do")
	@ResponseBody
	public Map<String, Object> getAllMenuItem(String node, String filter){
		Map<String, Object> map=new HashMap<>();
		List<Resource> menuList=new ArrayList<Resource>();
		Resource resource=new Resource();
		if("root".equals(node)){
			resource.setId("1");
		}else{
			resource.setId(node);			
		}
		menuList=this.resourceService.getChildren(resource);
		if(!StringUtils.isBlank(filter)){
			//查询角色拥有的全部权限资源--逻辑需优化
			List<RoleResource> roleResourceList=this.roleResourceService.findRoleResourceByRoleId(filter);
			for(Resource re: menuList){
				re.setChecked(false);
				for(RoleResource rr: roleResourceList){
					if(re.getId().equals(rr.getResourceId())){
						re.setChecked(true);
						break;
					}
				}
			}	
		}
		map.put("data", menuList);
		return map;
	}
	
	/**
	 * 更新资源
	 * @param list
	 * @return
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<Resource> list){
		Map<String, Object> map=new HashMap<>();
		logger.info("更新数据：{}", list);
		int flag=0;//更新标记
		if(list.size()>0){
			for(Resource resource: list){
				flag=this.resourceService.updateResource(resource);
			}
		}
		if(flag!=0){
			map.put("tip", "更新资源成功 ");
			map.put("success", true);		
		}else{
			map.put("tip", "更新资源失败");
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("/create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<Resource> list){
		Map<String, Object> map=new HashMap<>();
		logger.info("添加数据：{}", list);
		int flag=0;//更新标记
		if(list.size()>0){
			for(Resource resource: list){
				resource.preInsert();
				flag=this.resourceService.createResource(resource);
			}
		}
		if(flag!=0){
			map.put("tip", "添加资源成功 ");
			map.put("success", true);		
		}else{
			map.put("tip", "添加资源失败");
			map.put("success", false);
		}
		return map;
	}
	
	/**
	 * 删除资源
	 * @param list
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody List<Resource> list){
		Map<String, Object> map=new HashMap<>();
		logger.info("删除权限资源数据：{}", list);
		int flag=0;//更新标记
		if(list.size()>0){
			for(Resource resource: list){
				flag=this.resourceService.deleteResource(resource);
			}
		}
		if(flag!=0){
			map.put("tip", "删除资源成功 ");
			map.put("success", true);		
		}else{
			map.put("tip", "删除资源失败");
			map.put("success", false);
		}
		return map;
	}
	
	/**
	 * 删除角色权限资源
	 * @param list
	 * @return
	 */
	@RequestMapping("/updatePermission.do")
	@ResponseBody
	public Map<String, Object> updatePermission(String[] checkedIds, String roleId){
		Map<String, Object> map=new HashMap<>();
		logger.info("删除权限资源数据：{}", StringUtils.join(checkedIds, ","));
		int flag=0;//更新标记
		this.roleResourceService.deleteRoleResource(roleId);
		RoleResource roleResource=new RoleResource();
		for(int i=0; i<checkedIds.length; i++){
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(checkedIds[i]);
			flag=flag+this.roleResourceService.createRoleResource(roleResource);
		}
		if(flag==checkedIds.length){
			map.put("tip", "更新角色权限成功 ");
			map.put("success", true);		
		}else{
			map.put("tip", "更新角色权限失败");
			map.put("success", false);
		}
		return map;
	}
}
