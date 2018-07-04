package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.math.BigDecimal;

public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String goodsId;//商品ID	
	private String name;//商品名称	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date saleStartDate;//销售开始时间	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date saleEndDate;//销售截止时间	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;//创建时间	
	private String createBy;//创建人	
	private String status;//状态	
	private String comment;//商品说明	
	private Integer data;//流量	
	private String flowType;//流量类型	
	private Integer number;//商品数量	
	private Integer effectiveMonth;//有效月	
	private BigDecimal price;//单价	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;//修改时间	
	private String updateBy;//修改人	
	
	public Goods(){
		super();
	}
	
    public String getGoodsId() {
        return StringUtils.isBlank(goodsId) ? goodsId : goodsId.trim();
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    public String getName() {
        return StringUtils.isBlank(name) ? name : name.trim();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getSaleStartDate() {
        return saleStartDate;
    }
    
    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }
    
    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return StringUtils.isBlank(createBy) ? createBy : createBy.trim();
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String getStatus() {
        return StringUtils.isBlank(status) ? status : status.trim();
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getComment() {
        return StringUtils.isBlank(comment) ? comment : comment.trim();
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Integer getData() {
        return data;
    }
    
    public void setData(Integer data) {
        this.data = data;
    }

    public String getFlowType() {
        return StringUtils.isBlank(flowType) ? flowType : flowType.trim();
    }
    
    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
    
    public Integer getNumber() {
        return number;
    }
    
    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEffectiveMonth() {
        return effectiveMonth;
    }
    
    public void setEffectiveMonth(Integer effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return StringUtils.isBlank(updateBy) ? updateBy : updateBy.trim();
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
	
	@Override
	public String toString() {
		return "Goods ["
				+ "goodsId = " + goodsId 
				+ ", name = " + name 
				+ ", saleStartDate = " + saleStartDate 
				+ ", saleEndDate = " + saleEndDate 
				+ ", createTime = " + createTime 
				+ ", createBy = " + createBy 
				+ ", status = " + status 
				+ ", comment = " + comment 
				+ ", data = " + data 
				+ ", flowType = " + flowType 
				+ ", number = " + number 
				+ ", effectiveMonth = " + effectiveMonth 
				+ ", price = " + price 
				+ ", updateTime = " + updateTime 
				+ ", updateBy = " + updateBy 
				+ "]";
	}
	
}