package com.myron.ims.service;

import java.util.List;
import java.util.Map;
import com.myron.db.mybatis.bean.Page;

import com.myron.ims.bean.DictionaryItem;

public interface DictionaryItemService {
	//增删改
	public int createDictionaryItem(DictionaryItem dictionaryItem);
	public int updateDictionaryItem(DictionaryItem dictionaryItem);
	public int deleteDictionaryItem(DictionaryItem dictionaryItem);
	public int deleteDictionaryItem(String id);
	
	//查询
	public Map<String, Object> findListByPage(DictionaryItem dictionaryItem, Page page);
	public List<Map<String, Object>> findMapList();
	public List<DictionaryItem> findList();
	
	public List<DictionaryItem> getDictionaryItemList(String dictionaryId);
	
	

	
}
