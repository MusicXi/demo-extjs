package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import com.myron.common.util.UuidUtils;

public class DictionaryItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String dictionaryItemId;			//字典项ID	
	private String dictionaryId;			//字典类型ID	
	private String value;			//字典值	
	private String text;			//显示名称	
	private String parentId;			//父级ID	
	private Integer grade;			//层级	
	private Integer sort;			//排序	
	
    public String getDictionaryItemId() {
        return StringUtils.isBlank(dictionaryItemId) ? dictionaryItemId : dictionaryItemId.trim();
    }
    public void setDictionaryItemId(String dictionaryItemId) {
        this.dictionaryItemId = dictionaryItemId;
    }
    
    
    public String getDictionaryId() {
        return StringUtils.isBlank(dictionaryId) ? dictionaryId : dictionaryId.trim();
    }
    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
    
    
    public String getValue() {
        return StringUtils.isBlank(value) ? value : value.trim();
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    
    public String getText() {
        return StringUtils.isBlank(text) ? text : text.trim();
    }
    public void setText(String text) {
        this.text = text;
    }
    
    
    public String getParentId() {
        return StringUtils.isBlank(parentId) ? parentId : parentId.trim();
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    
	
	/**
	 * 插入之前执行方法，需要手动调用
	 * @return this
	 */
	public void preInsert(){
		if(this.dictionaryItemId==null || "".equals(this.dictionaryItemId)){
			this.setDictionaryItemId(UuidUtils.creatUUID());
//			User user=(User) UserUtils.getPrincipal();
//			this.createBy=user.getId();
//			this.createDate=new Date();
		}
		
	}
	
	/**
	 * 更新之前调用,需要手动调用
	 * @return
	 */
	public Object preUpdate(){
		if(!(this.dictionaryItemId==null || "".equals(this.dictionaryItemId))){
//			User user=(User) UserUtils.getPrincipal();
//			this.updateBy = user.getId();
//			this.updateDate=new Date();
		}
		return this;
	}
}