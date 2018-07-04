package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myron.common.util.UuidUtils;
import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.Goods;
import com.myron.ims.dao.GoodsDao;
import com.myron.ims.service.GoodsService;

//@Service("goodsService")
//@Transactional(rollbackFor=Exception.class)
public class GoodsServiceImpl  implements GoodsService {
	private static final Logger LOGGER=LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	@Qualifier("sqlSessionTemplateForD1")
	private SqlSessionTemplate templateD1;
	
	@Autowired
	@Qualifier("sqlSessionTemplateForD2")
	private SqlSessionTemplate templateD2;
	
	@Override
	public Map<String, Object> createGoods(Goods goods) throws Exception{
/*		Map<String, Object> resultMap = new HashMap<>();
		int flag = this.goodsDao.insertSelective(goods);
		if (flag != 1) {
			LOGGER.error("创建Goods失败! flag={}", flag);
			throw new Exception("创建Goods失败!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "创建Goods 成功!");
		LOGGER.info("创建Goods 成功 " + goods.toString());
		return resultMap;*/
		Map<String, Object> resultMap = new HashMap<>();
		//int flag = this.goodsDao.insertSelective(goods);
		int flag1 = this.templateD1.insert("com.myron.ims.dao.GoodsDao.insertSelective", goods);
		int flag2 = this.templateD2.insert("com.myron.ims.dao.GoodsDao.insertSelective", goods);
		System.out.println("flag1:" + flag1 + "flag2:" + flag2);
	/*	if (flag != 1) {
			LOGGER.error("创建Goods失败! flag={}", flag);
			throw new Exception("创建Goods失败!");
		}*/
		if (flag2 == 1) {
			throw new Exception("自定义异常");			
		}
		resultMap.put("success", true);
		resultMap.put("msg", "创建Goods 成功!");
		LOGGER.info("创建Goods 成功 " + goods.toString());
		return resultMap;
	}
	
	@Override
	public Map<String, Object> createGoods(List<Goods> goodsList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();

		if (goodsList == null || goodsList.isEmpty()) {
			throw new Exception("无批量新增的数据");
		}
		
		for (Goods goods : goodsList) {
			//TODO 可修改主键生成Id方式
		    goods.setGoodsId(UuidUtils.creatUUID());
			if (this.goodsDao.insertSelective(goods) != 1) {
				throw new Exception("新增数据失败!");
			}
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量创建Goods 成功!");
		return resultMap;

	}
	
	@Override
	public Map<String, Object> updateGoods(Goods goods) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		int flag = this.goodsDao.updateByPrimaryKeySelective(goods);
		if (flag != 1) {
			LOGGER.error("更新Goods 失败! flag={}", flag);
			throw new Exception("updateGoods failure!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "修改Goods 成功!");
		LOGGER.info("修改Goods 成功! " + goods.toString());
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateGoods(List<Goods> goodsList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		if (goodsList == null || goodsList.isEmpty()) {
			throw new Exception("无批量新增的数据");
		}
		
		for (Goods goods : goodsList) {
			if (this.goodsDao.updateByPrimaryKeySelective(goods) != 1){
				throw new Exception("修改数据失败!");
			}
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量修改Goods 成功!");
		return resultMap;
	}


	@Override
	public Map<String, Object> deleteGoods(Goods goods) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		//TODO 设置主键ID
	    String id = goods.getGoodsId();
		int flag = this.goodsDao.deleteByPrimaryKey(id);
		if (flag != 1) {
			LOGGER.error("删除Goods 失败! flag={}", flag);
			throw new Exception("deleteGoods failure!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "删除Goods 成功!");
		LOGGER.info("删除Goods 成功 key:{}", id);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> deleteGoods(List<Goods> goodsList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		int count = 0;
		
		if (goodsList == null || goodsList.isEmpty()) {
			throw new Exception("无批量删除的数据!");
		}
		
		for (Goods goods : goodsList) {
			//TODO 设置主键ID
		    String id = goods.getGoodsId();
			if (this.goodsDao.deleteByPrimaryKey(id) != 1) {
				throw new Exception("删除数据失败!");
			}
			count++;
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量删除Goods 成功!");
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public Goods findGoodsByPrimaryKey(String id) {
		return this.goodsDao.selectByPrimaryKey(id);
	}

	@Override
	public Page<Map<String, Object>> findMapListByPage(Goods goods, Page<Map<String, Object>> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods", goods);
		map.put("page", page);
		List<Map<String, Object>> list = this.goodsDao.selectMapListByPage(map);
		page.setResultList(list);
		return page;
	}
	
	@Override
	public Page<Goods> findListByPage(Goods goods, Page<Goods> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods", goods);
		map.put("page", page);
		List<Goods> list = this.goodsDao.selectListByPage(map);
		page.setResultList(list);
		return page;
	}

	@Override
	public List<Map<String, Object>> findMapList(Goods goods) {
		return this.goodsDao.selectMapList(goods);
	}
	
	@Override
	public List<Goods> findList(Goods goods){
		return this.goodsDao.selectList(goods);
	}






}
