package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.MetaTagsDim;
import com.myron.ims.dao.MetaTagsDimDao;
import com.myron.ims.service.MetaTagsDimService;

@Service("metaTagsDimService")
@Transactional(rollbackFor=Exception.class)
public class MetaTagsDimServiceImpl  implements MetaTagsDimService {
	private static final Logger LOGGER=LoggerFactory.getLogger(MetaTagsDimServiceImpl.class);
	
	@Autowired
	private MetaTagsDimDao metaTagsDimDao;
	
	@Override
	public Map<String, Object> createMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		int flag = this.metaTagsDimDao.insertSelective(metaTagsDim);
		if (flag != 1) {
			LOGGER.error("创建MetaTagsDim失败! flag={}", flag);
			throw new Exception("创建MetaTagsDim失败!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "创建MetaTagsDim 成功!");
		LOGGER.info("创建MetaTagsDim 成功 " + metaTagsDim.toString());
		return resultMap;
	}
	
	@Override
	public Map<String, Object> createMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();

		if (metaTagsDimList == null || metaTagsDimList.isEmpty()) {
			throw new Exception("无批量新增的数据");
		}
		
		for (MetaTagsDim metaTagsDim : metaTagsDimList) {
			//TODO 可修改主键生成Id方式
		    //metaTagsDim.setId(UuidUtils.creatUUID());
			if (this.metaTagsDimDao.insertSelective(metaTagsDim) != 1) {
				throw new Exception("新增数据失败!");
			}
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量创建MetaTagsDim 成功!");
		return resultMap;

	}
	
	@Override
	public Map<String, Object> updateMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		int flag = this.metaTagsDimDao.updateByPrimaryKeySelective(metaTagsDim);
		if (flag != 1) {
			LOGGER.error("更新MetaTagsDim 失败! flag={}", flag);
			throw new Exception("updateMetaTagsDim failure!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "修改MetaTagsDim 成功!");
		LOGGER.info("修改MetaTagsDim 成功! " + metaTagsDim.toString());
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		if (metaTagsDimList == null || metaTagsDimList.isEmpty()) {
			throw new Exception("无批量新增的数据");
		}
		
		for (MetaTagsDim metaTagsDim : metaTagsDimList) {
			if (this.metaTagsDimDao.updateByPrimaryKeySelective(metaTagsDim) != 1){
				throw new Exception("修改数据失败!");
			}
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量修改MetaTagsDim 成功!");
		return resultMap;
	}


	@Override
	public Map<String, Object> deleteMetaTagsDim(MetaTagsDim metaTagsDim) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		//TODO 设置主键ID
	    String id = metaTagsDim.getId() + "";
		int flag = this.metaTagsDimDao.deleteByPrimaryKey(id);
		if (flag != 1) {
			LOGGER.error("删除MetaTagsDim 失败! flag={}", flag);
			throw new Exception("deleteMetaTagsDim failure!");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "删除MetaTagsDim 成功!");
		LOGGER.info("删除MetaTagsDim 成功 key:{}", id);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> deleteMetaTagsDim(List<MetaTagsDim> metaTagsDimList) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		int count = 0;
		
		if (metaTagsDimList == null || metaTagsDimList.isEmpty()) {
			throw new Exception("无批量删除的数据!");
		}
		
		for (MetaTagsDim metaTagsDim : metaTagsDimList) {
			//TODO 设置主键ID
		    String id = metaTagsDim.getId() + "";
			if (this.metaTagsDimDao.deleteByPrimaryKey(id) != 1) {
				throw new Exception("删除数据失败!");
			}
			count++;
		}
		resultMap.put("success", true);
		resultMap.put("msg", "批量删除MetaTagsDim 成功!");
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public MetaTagsDim findMetaTagsDimByPrimaryKey(String id) {
		return this.metaTagsDimDao.selectByPrimaryKey(id);
	}

	@Override
	public Page<Map<String, Object>> findMapListByPage(MetaTagsDim metaTagsDim, Page<Map<String, Object>> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("metaTagsDim", metaTagsDim);
		map.put("page", page);
		List<Map<String, Object>> list = this.metaTagsDimDao.selectMapListByPage(map);
		page.setResultList(list);
		return page;
	}
	
	@Override
	public Page<MetaTagsDim> findListByPage(MetaTagsDim metaTagsDim, Page<MetaTagsDim> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("metaTagsDim", metaTagsDim);
		map.put("page", page);
		List<MetaTagsDim> list = this.metaTagsDimDao.selectListByPage(map);
		page.setResultList(list);
		return page;
	}

	@Override
	public List<Map<String, Object>> findMapList(MetaTagsDim metaTagsDim) {
		return this.metaTagsDimDao.selectMapList(metaTagsDim);
	}
	
	@Override
	public List<MetaTagsDim> findList(MetaTagsDim metaTagsDim){
		return this.metaTagsDimDao.selectList(metaTagsDim);
	}

	@Override
	public List<MetaTagsDim> getChildList(MetaTagsDim metaTagsDim) {
		return this.metaTagsDimDao.getChildList(metaTagsDim);
	}






}
