package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import com.myron.common.util.UuidUtils;

public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userId;				
	private String roleId;				
	
    public String getUserId() {
        return StringUtils.isBlank(userId) ? userId : userId.trim();
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
    public String getRoleId() {
        return StringUtils.isBlank(roleId) ? roleId : roleId.trim();
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    
	
	/**
	 * 插入之前执行方法，需要手动调用
	 * @return this
	 */
	public void preInsert(){
		if(this.userId==null || "".equals(this.userId)){
			this.setUserId(UuidUtils.creatUUID());
//			User user=(User) UserUtils.getPrincipal();
//			this.createBy=user.getId();
//			this.createDate=new Date();
		}
		
		if(this.roleId==null || "".equals(this.roleId)){
			this.setRoleId(UuidUtils.creatUUID());
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
		if(!(this.userId==null || "".equals(this.userId))){
//			User user=(User) UserUtils.getPrincipal();
//			this.updateBy = user.getId();
//			this.updateDate=new Date();
		}
		return this;
	}
}