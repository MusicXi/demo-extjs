package com.myron.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myron.ims.bean.Organization;
import com.myron.ims.service.OrgService;


@Controller
@RequestMapping("/system/orgManager")
public class OrgController {
	@Autowired
	private OrgService orgService;
	
	@RequestMapping("/getOrgTree.do")
	@ResponseBody
	public List<Organization> getOrgTree(String node) {
		List<Organization> list=new ArrayList<Organization>();
		list=this.orgService.getChildren(node);
		return list;
	}

}
