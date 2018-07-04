package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MetaTagsDim implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;//标签ID	
	private String name;//标签名称	
	private Integer type;//1:分类，2:标签组，3:标签	
	private Integer parentId;//父标签	
	private String arrparentId;//父标签集合	
	private String value;//标签值	
	private String description;//业务含义	
	private Integer status;//状态：-1:废止,0:创建,99上线	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date releaseTime;//上线时间	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date trashTime;//废止时间	
	private String demand;//需求方	
	private Integer isValMulti;//标签多值  ，1是，2否	
	private Integer isValEnum;//标签值枚举  ，1是，2否	
	private String valType;//字段类型，DATE_INT支持yyyymmdd,yyyymm等格式	
	private Integer valWeightType;//标签权重  1:0/1  2:(0,1]	
	private Integer valWeightNorm;//标签权重归一化  ，1是，2否	
	private Integer freq;//更新频率  1:按天,2:按周,3:按月 4:实时	
	private Integer lifecycle;//生命周期即有效期，单位为天   0表示长期有效	
	private String ruleDesc;//标签规则描述	
	private String domain;//标签域，对应列簇	
	private String field;//标签列，对应列	
	private String dataFlow;//数据流	
	private String dataNode;//数据节点	
	private String committer;//开发负责人	
	private Integer isIndex;//是否建索引，指是否保存到solr中建立索引，1是，2否	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;//创建时间	
	private Integer createBy;//创建人	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifiedTime;//修改时间	
	private Integer modifiedBy;//修改人	
	
	private Boolean leaf;
	private Boolean expanded;
	
	public MetaTagsDim(){
		super();
	}
	
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return StringUtils.isBlank(name) ? name : name.trim();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
   	if (type == 2) {
    		this.setLeaf(true);
    	}
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getArrparentId() {
        return StringUtils.isBlank(arrparentId) ? arrparentId : arrparentId.trim();
    }
    
    public void setArrparentId(String arrparentId) {
        this.arrparentId = arrparentId;
    }
    
    public String getValue() {
        return StringUtils.isBlank(value) ? value : value.trim();
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getDescription() {
        return StringUtils.isBlank(description) ? description : description.trim();
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }
    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getTrashTime() {
        return trashTime;
    }
    
    public void setTrashTime(Date trashTime) {
        this.trashTime = trashTime;
    }

    public String getDemand() {
        return StringUtils.isBlank(demand) ? demand : demand.trim();
    }
    
    public void setDemand(String demand) {
        this.demand = demand;
    }
    
    public Integer getIsValMulti() {
        return isValMulti;
    }
    
    public void setIsValMulti(Integer isValMulti) {
        this.isValMulti = isValMulti;
    }

    public Integer getIsValEnum() {
        return isValEnum;
    }
    
    public void setIsValEnum(Integer isValEnum) {
        this.isValEnum = isValEnum;
    }

    public String getValType() {
        return StringUtils.isBlank(valType) ? valType : valType.trim();
    }
    
    public void setValType(String valType) {
        this.valType = valType;
    }
    
    public Integer getValWeightType() {
        return valWeightType;
    }
    
    public void setValWeightType(Integer valWeightType) {
        this.valWeightType = valWeightType;
    }

    public Integer getValWeightNorm() {
        return valWeightNorm;
    }
    
    public void setValWeightNorm(Integer valWeightNorm) {
        this.valWeightNorm = valWeightNorm;
    }

    public Integer getFreq() {
        return freq;
    }
    
    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }
    
    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public String getRuleDesc() {
        return StringUtils.isBlank(ruleDesc) ? ruleDesc : ruleDesc.trim();
    }
    
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
    
    public String getDomain() {
        return StringUtils.isBlank(domain) ? domain : domain.trim();
    }
    
    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    public String getField() {
        return StringUtils.isBlank(field) ? field : field.trim();
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public String getDataFlow() {
        return StringUtils.isBlank(dataFlow) ? dataFlow : dataFlow.trim();
    }
    
    public void setDataFlow(String dataFlow) {
        this.dataFlow = dataFlow;
    }
    
    public String getDataNode() {
        return StringUtils.isBlank(dataNode) ? dataNode : dataNode.trim();
    }
    
    public void setDataNode(String dataNode) {
        this.dataNode = dataNode;
    }
    
    public String getCommitter() {
        return StringUtils.isBlank(committer) ? committer : committer.trim();
    }
    
    public void setCommitter(String committer) {
        this.committer = committer;
    }
    
    public Integer getIsIndex() {
        return isIndex;
    }
    
    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }
    
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

	
	@Override
	public String toString() {
		return "MetaTagsDim ["
				+ "id = " + id 
				+ ", name = " + name 
				+ ", type = " + type 
				+ ", parentId = " + parentId 
				+ ", arrparentId = " + arrparentId 
				+ ", value = " + value 
				+ ", description = " + description 
				+ ", status = " + status 
				+ ", releaseTime = " + releaseTime 
				+ ", trashTime = " + trashTime 
				+ ", demand = " + demand 
				+ ", isValMulti = " + isValMulti 
				+ ", isValEnum = " + isValEnum 
				+ ", valType = " + valType 
				+ ", valWeightType = " + valWeightType 
				+ ", valWeightNorm = " + valWeightNorm 
				+ ", freq = " + freq 
				+ ", lifecycle = " + lifecycle 
				+ ", ruleDesc = " + ruleDesc 
				+ ", domain = " + domain 
				+ ", field = " + field 
				+ ", dataFlow = " + dataFlow 
				+ ", dataNode = " + dataNode 
				+ ", committer = " + committer 
				+ ", isIndex = " + isIndex 
				+ ", createTime = " + createTime 
				+ ", createBy = " + createBy 
				+ ", modifiedTime = " + modifiedTime 
				+ ", modifiedBy = " + modifiedBy 
				+ "]";
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
	
	
	
}