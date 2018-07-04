package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.Log;

public interface LogService {
	//增删改
	public int createLog(Log log);
	public int updateLog(Log log);
	
	public int deleteLog(String id);
	
	//查询
	public Log findLogByPrimaryKey(String id);
	public Map<String, Object> findListByPage(Log log, Page page);
	public Map<String, Object> findMapListByPage(Log log, Page page);
	public List<Map<String, Object>> findMapList(Log log);
	public List<Log> findList(Log log);
	

	
}
