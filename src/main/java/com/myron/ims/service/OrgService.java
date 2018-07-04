package com.myron.ims.service;

import java.util.List;
import com.myron.ims.bean.Organization;

public interface OrgService {

	
	public List<Organization> findAllList();
	public List<Organization> getChildren(String id);
	
	
}
