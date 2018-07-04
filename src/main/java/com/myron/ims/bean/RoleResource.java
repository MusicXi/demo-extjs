package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import com.myron.common.util.UuidUtils;

public class RoleResource implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String roleId;				
	private String resourceId;				
	
    public String getRoleId() {
        return StringUtils.isBlank(roleId) ? roleId : roleId.trim();
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    
    public String getResourceId() {
        return StringUtils.isBlank(resourceId) ? resourceId : resourceId.trim();
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    
    
	
	/**
	 * 插入之前执行方法，需要手动调用
	 * @return this
	 */
	public void preInsert(){
		if(this.roleId==null || "".equals(this.roleId)){
			this.setRoleId(UuidUtils.creatUUID());
//			User user=(User) UserUtils.getPrincipal();
//			this.createBy=user.getId();
//			this.createDate=new Date();
		}
		
		if(this.resourceId==null || "".equals(this.resourceId)){
			this.setResourceId(UuidUtils.creatUUID());
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
		if(!(this.roleId==null || "".equals(this.roleId))){
//			User user=(User) UserUtils.getPrincipal();
//			this.updateBy = user.getId();
//			this.updateDate=new Date();
		}
		return this;
	}
}