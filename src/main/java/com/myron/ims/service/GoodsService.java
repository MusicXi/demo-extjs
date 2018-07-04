package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Goods;

public interface GoodsService {
	//增删改
	Map<String, Object> createGoods(Goods goods) throws Exception;
	Map<String, Object> createGoods(List<Goods> goodsList) throws Exception;
	
	Map<String, Object> updateGoods(Goods goods) throws Exception;
	Map<String, Object> updateGoods(List<Goods> goodsList) throws Exception;
	
	Map<String, Object> deleteGoods(Goods goods) throws Exception;
	Map<String, Object> deleteGoods(List<Goods> goodsList) throws Exception;
	
	//查询
	Goods findGoodsByPrimaryKey(String id);
	Page<Goods> findListByPage(Goods goods, Page<Goods> page);
	Page<Map<String, Object>> findMapListByPage(Goods goods, Page<Map<String, Object>> page);
	List<Map<String, Object>> findMapList(Goods goods);
	List<Goods> findList(Goods goods);
	

	
}
