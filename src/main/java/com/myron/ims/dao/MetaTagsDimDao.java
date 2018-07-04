package com.myron.ims.dao;

import java.util.List;
import java.util.Map;

import com.myron.db.mybatis.annotation.MyBatisRepository;


import com.myron.ims.bean.MetaTagsDim;

@MyBatisRepository
public interface MetaTagsDimDao {
	//增加记录
	int insert(MetaTagsDim metaTagsDim);
	int insertSelective(MetaTagsDim metaTagsDim);
	int insertByBatch(List<MetaTagsDim> list);
	int insertSelectiveByBatch(List<MetaTagsDim> list);
	//删除记录
	int deleteByPrimaryKey(String id);
	
	//修改记录
	int updateByPrimaryKey(MetaTagsDim metaTagsDim);
	int updateByPrimaryKeySelective(MetaTagsDim metaTagsDim);
	int updateByBatch(List<MetaTagsDim> list);
	int updateSelectiveByBatch(List<MetaTagsDim> list);
	
	//查询记录
	MetaTagsDim selectByPrimaryKey(String id);
	
	//查询记录列表
	List<MetaTagsDim> selectList(MetaTagsDim metaTagsDim);
	List<MetaTagsDim> selectListByPage(Map<String, Object> map);	
	
	List<Map<String, Object>> selectMapList(MetaTagsDim metaTagsDim);
	List<Map<String, Object>> selectMapListByPage(Map<String, Object> map);

	List<MetaTagsDim> getChildList(MetaTagsDim metaTagsDim);
}
