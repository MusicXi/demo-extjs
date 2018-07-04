package com.myron.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.db.mybatis.bean.Page;
import com.myron.ims.bean.DictionaryItem;
import com.myron.ims.dao.DictionaryItemDao;
import com.myron.ims.service.DictionaryItemService;

@Service("dictionaryItemService")
public class DictionaryItemServiceImpl  implements DictionaryItemService{
	
	@Autowired
	private DictionaryItemDao dictionaryItemDao;
	
	@Override
	public int createDictionaryItem(DictionaryItem dictionaryItem) {
		return this.dictionaryItemDao.insertSelective(dictionaryItem);
	}
	
	@Override
	public int updateDictionaryItem(DictionaryItem dictionaryItem) {
		return this.dictionaryItemDao.updateByPrimaryKeySelective(dictionaryItem);
	}

	@Override
	public int deleteDictionaryItem(String id) {
		return this.dictionaryItemDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteDictionaryItem(DictionaryItem dictionaryItem) {
		return this.dictionaryItemDao.deleteByPrimaryKey(dictionaryItem.getDictionaryId());
	}


	@Override
	public Map<String, Object> findListByPage(DictionaryItem dictionaryItem, Page page) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictionaryItem", dictionaryItem);
		map.put("page", page);
		List<Map<String, Object>> list=this.dictionaryItemDao.selectMapListByPage(map);
		map.put("data", list);	
		return map;
	}

	@Override
	public List<Map<String, Object>> findMapList() {
		DictionaryItem dictionaryItem=new DictionaryItem();
		return this.dictionaryItemDao.selectMapList(dictionaryItem);
	}
	
	@Override
	public List<DictionaryItem> findList(){
		return null;
	}

	@Override
	public List<DictionaryItem> getDictionaryItemList(String dictionaryId) {
		// TODO Auto-generated method stub
		return null;
	}






}
