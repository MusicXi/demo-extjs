package com.myron.ims.dao;

import java.util.List;
import java.util.Map;

import com.myron.db.mybatis.annotation.MyBatisRepository;

/**
 * 传递sql,用map来存放结果集
 * @author Administrator
 *
 */
@MyBatisRepository
public interface BaseMapper {
	public Map<String,Object> select(String sql);
	public List<Map<String,Object>> selectList(String sql);
}
