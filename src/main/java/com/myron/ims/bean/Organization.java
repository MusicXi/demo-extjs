package com.myron.ims.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 组织机构 
 * @author lin.r.x
 *
 */
public class Organization implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String code;
	private String type;
	private String grade;
	private Organization parent;
	
	private Boolean leaf;
	private Boolean expanded;
	private Boolean checked;
	
	private List<Organization> children;
	private String[] Ids;

	


	public String getId() {
		return id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if("公司".equals(type)){
			setLeaf(false);
			setExpanded(true);
		}else if("部门".equals(type)){
			setLeaf(true);
		}
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public String[] getIds() {
		return Ids;
	}

	public void setIds(String[] ids) {
		Ids = ids;
	}
	
	

	public List<Organization> getChildren() {
		return children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}
	
	



	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
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
		return "Orgnization [id=" + id + ", name=" + name + ", code=" + code
				+ ", type=" + type + ", grade=" + grade + ", parent=" + parent
				+ ", Ids=" + Arrays.toString(Ids) + "]";
	}
	
}
