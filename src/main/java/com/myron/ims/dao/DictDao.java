package com.myron.ims.dao;

import java.util.List;

import com.myron.db.mybatis.dao.BaseCacheDao;
import com.myron.ims.bean.DictionaryItem;

/**
 * 数据字典Dao(支持缓存)
 * @author Administrator
 *
 */
public interface DictDao extends BaseCacheDao<DictionaryItem>{
	public List<DictionaryItem> findList(DictionaryItem item);
	public List<DictionaryItem> selectByDictionnaryId(String dictionnaryId);
}
