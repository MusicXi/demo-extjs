package com.myron.ims.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典类
 * @author Administrator
 *
 */
public class Dictionary {
	// 数据字典值
	private String dictionaryId;

	// 数据字典名称
	private String dictionaryName;

	// 数据字典对应数据项
	private List<DictionaryItem> itemList;
	
	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public List<DictionaryItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<DictionaryItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * 设置数据字典项
	 * @param item
	 */
	public void setDataDictionaryItem(DictionaryItem item){
		if (this.itemList == null){
			this.itemList = new ArrayList<DictionaryItem>();			
		}
		this.itemList.add(item);
	}

	/**
	 * 设置数据字典项
	 * @param value
	 * @param text
	 */
	public void setDataDictionaryItem(String value, String text){
		if (this.itemList == null){
			this.itemList = new ArrayList<DictionaryItem>();			
		}
		DictionaryItem item = new DictionaryItem();
		item.setValue(value);
		item.setText(text);
		this.itemList.add(item);
	}

}
