package com.myron.ims.bean;

import java.io.Serializable;

import com.myron.common.util.UuidUtils;

/**
 * 资源类(菜单/按钮)
 * @author lin.r.x
 *
 */
public class Resource implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;    //资源名称
	private String type;	//资源类型
	private String parentId;//父id
	private String url;		
	private String permission;//权限标识
	private String available; //是否可用
	private String icon;
	
	private Boolean leaf;//VO属性
	private Boolean checked;
	private Boolean expanded;
	
	public String getId() {
		return id;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if("button".equals(type)){
			setLeaf(true);
			setExpanded(false);
		}else if("menu".equals(type)){
			//setExpanded("true");
			setLeaf(false);
		}
		this.type = type;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

	
	

	
	
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	

	
	
	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", type=" + type
				+ ", parentId=" + parentId + ", url=" + url + ", permission="
				+ permission + ", available=" + available + ", icon=" + icon
				+ ", leaf=" + leaf + ", checked=" + checked + ", expanded="
				+ expanded + "]";
	}
	/**
	 * 插入之前执行方法，需要手动调用
	 * @return this
	 */
	public void preInsert(){
		if(this.id==null || "".equals(this.id)){
			this.setId(UuidUtils.creatUUID());
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
		if(!(this.id==null || "".equals(this.id))){
//			User user=(User) UserUtils.getPrincipal();
//			this.updateBy = user.getId();
//			this.updateDate=new Date();
		}
		return this;
	}
	
	
	
	
}
