package com.myron.ims.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myron.ims.bean.Organization;
import com.myron.ims.dao.OrgDao;
import com.myron.ims.service.OrgService;

@Service("orgService")
public class OrgServiceImpl implements OrgService {
	
	@Autowired
	private OrgDao orgDao;



	@Override
	public List<Organization> findAllList() {
		Organization org=new Organization();
		return this.orgDao.selectList(org);
	}

	@Override
	public List<Organization> getChildren(String id) {
		return this.orgDao.getChildren(id);
	}

}
