package com.myron.ims.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.myron.ims.bean.DictionaryItem;
import com.myron.ims.dao.DictDao;

/**
 * 缓存的数据需要满足多读少写的特性，也就是不会经常更新（或者能接受过期数据），如果更新得非常频繁，缓存的价值就会大打折扣了
 * @author Administrator
 *
 */
@Repository("dictDao")
public class DictDaoImpl implements DictDao{
	private static final String NAMESPACE="com.myron.ims.dao.DictionaryDao";
	private static final String CACHE="dictCache";
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Cacheable(value=CACHE, key="#item.dictionaryItemId")
	@Override
	public DictionaryItem selectByPrimaryKey(String dictionaryItemId ) {
		return this.template.selectOne(NAMESPACE+".selectByPrimaryKey", dictionaryItemId);
	}

	@Cacheable(value="dictListCache", key="#item.dictionaryId")
	@Override
	public List<DictionaryItem> findList(DictionaryItem item) {
		return this.template.selectList(NAMESPACE+".findList", item);
	}

	@Override
	public int insertSelective(DictionaryItem item) {
		int flag=this.template.insert(NAMESPACE+".insertSelective", item);
		return flag;
	}

	@Override
	public int updateByPrimaryKeySelective(DictionaryItem item) {
		return this.template.update(NAMESPACE+".updateSelective", item);
	}

	@Override
	public List<DictionaryItem> selectByDictionnaryId(String dictionnaryId) {
		this.template.selectList("com.myron.ims.dao.DictionaryDao.selectByPrimaryKey", dictionnaryId);
		return null;
	}

	@CachePut(key="#item.dictionaryItemId",value=CACHE)
	@Override
	public DictionaryItem createReturn(DictionaryItem item) {
		int flag=this.template.insert(NAMESPACE+".insertSelective", item);
		return flag==0?null:item;
	}

	@CacheEvict(value=CACHE, key="#item.dictionaryItemId")
	@Override
	public DictionaryItem updateSelectiveReturn(DictionaryItem item) {
		int flag=this.template.update(NAMESPACE+".updateByPrimaryKeySelective", item);
		return flag==0?null:item;
	}

	@Override
	public int insert(DictionaryItem entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DictionaryItem entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DictionaryItem> selectList(DictionaryItem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DictionaryItem> selectListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMapList(DictionaryItem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMapListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
