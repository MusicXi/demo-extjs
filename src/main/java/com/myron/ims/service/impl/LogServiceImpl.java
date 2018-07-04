package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Log;
import com.myron.ims.dao.LogDao;
import com.myron.ims.service.LogService;

@Service("logService")
public class LogServiceImpl  implements LogService{
	
	@Autowired
	private LogDao logDao;
	
	@Override
	public int createLog(Log log) {
		return this.logDao.insertSelective(log);
	}
	
	@Override
	public int updateLog(Log log) {
		return this.logDao.updateByPrimaryKeySelective(log);
	}


	@Override
	public int deleteLog(String id) {
		return this.logDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public Log findLogByPrimaryKey(String id) {
		return this.logDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findMapListByPage(Log log, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		//page.setDefaultSort("operateDate", "desc");
		map.put("log", log);
		map.put("page", page);
		List<Map<String, Object>> list=this.logDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}
	
	@Override
	public Map<String, Object> findListByPage(Log log, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		page.setDefaultSort("operate_date", "desc");
		map.put("log", log);
		map.put("page", page);
		List<Log> list=this.logDao.selectListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList(Log log) {
		return this.logDao.selectMapList(log);
	}
	
	@Override
	public List<Log> findList(Log log){
		return this.logDao.selectList(log);
	}






}
