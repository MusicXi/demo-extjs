package com.myron.ims.dao;

import java.util.List;
import java.util.Map;

import com.myron.db.mybatis.annotation.MyBatisRepository;


import com.myron.ims.bean.Goods;

@MyBatisRepository
public interface GoodsDao {
	//增加记录
	int insert(Goods goods);
	int insertSelective(Goods goods);
	int insertByBatch(List<Goods> list);
	int insertSelectiveByBatch(List<Goods> list);
	//删除记录
	int deleteByPrimaryKey(String id);
	
	//修改记录
	int updateByPrimaryKey(Goods goods);
	int updateByPrimaryKeySelective(Goods goods);
	int updateByBatch(List<Goods> list);
	int updateSelectiveByBatch(List<Goods> list);
	
	//查询记录
	Goods selectByPrimaryKey(String id);
	
	//查询记录列表
	List<Goods> selectList(Goods goods);
	List<Goods> selectListByPage(Map<String, Object> map);	
	
	List<Map<String, Object>> selectMapList(Goods goods);
	List<Map<String, Object>> selectMapListByPage(Map<String, Object> map);

}
