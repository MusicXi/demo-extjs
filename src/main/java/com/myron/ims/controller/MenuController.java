package com.myron.ims.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myron.ims.bean.Resource;
import com.myron.ims.service.ResourceService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/loadByJson.do")
	@ResponseBody
	public Map<String, Object> getAllMenuItem(String node, String id){
		Map<String, Object> map=new HashMap<>();
		List<Resource> menuList=new ArrayList<Resource>();
		Resource menu=new Resource();
		if("root".equals(node)){
			menu.setId("1");
		}else{
			menu.setId(node);			
		}
		menuList=this.resourceService.getChildMenu(menu);
		map.put("data", menuList);
		return map;
	}
}
