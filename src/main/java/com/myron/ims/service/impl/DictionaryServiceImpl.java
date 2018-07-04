package com.myron.ims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myron.ims.bean.DictionaryItem;
import com.myron.ims.dao.DictionaryDao;
import com.myron.ims.service.DictionaryService;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public List<DictionaryItem> getDictItem(DictionaryItem item) {
		return this.dictionaryDao.selectList(item);
	}



}
