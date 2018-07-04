package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.MetaTagsDim;

public interface MetaTagsDimService {
	//增删改
	Map<String, Object> createMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception;
	Map<String, Object> createMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception;
	
	Map<String, Object> updateMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception;
	Map<String, Object> updateMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception;
	
	Map<String, Object> deleteMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception;
	Map<String, Object> deleteMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception;
	
	//查询
	MetaTagsDim findMetaTagsDimByPrimaryKey(String id);
	Page<MetaTagsDim> findListByPage(MetaTagsDim metaTagsDim, Page<MetaTagsDim> page);
	Page<Map<String, Object>> findMapListByPage(MetaTagsDim metaTagsDim, Page<Map<String, Object>> page);
	List<Map<String, Object>> findMapList(MetaTagsDim metaTagsDim);
	List<MetaTagsDim> findList(MetaTagsDim metaTagsDim);
	
	
	List<MetaTagsDim> getChildList(MetaTagsDim metaTagsDim);
	

	
}
