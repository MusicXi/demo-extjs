package com.myron.ims.bean;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import com.myron.common.util.UuidUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String articleId;			//编号	
	private String categoryId;			//栏目编号	
	private String title;			//标题	
	private String link;			//文章链接	
	private String color;			//标题颜色	
	private String image;			//文章图片	
	private String keywords;			//关键字	
	private String description;			//摘要	
	private Integer weight;			//权重	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date weightDate;			//权重期限	
	private Integer hits;			//点击数	
	private String posid;			//推荐位	
	private String createBy;			//创建者	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;			//创建时间	
	private String updateBy;			//更新者	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate;			//更新时间	
	private String remarks;			//备注信息	
	private String delFlag;			//删除标记	
	private String content;			//文章内容	
	
    public String getArticleId() {
        return StringUtils.isBlank(articleId) ? articleId : articleId.trim();
    }
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    
    
    public String getCategoryId() {
        return StringUtils.isBlank(categoryId) ? categoryId : categoryId.trim();
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    
    public String getTitle() {
        return StringUtils.isBlank(title) ? title : title.trim();
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    public String getLink() {
        return StringUtils.isBlank(link) ? link : link.trim();
    }
    public void setLink(String link) {
        this.link = link;
    }
    
    
    public String getColor() {
        return StringUtils.isBlank(color) ? color : color.trim();
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    
    public String getImage() {
        return StringUtils.isBlank(image) ? image : image.trim();
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    
    public String getKeywords() {
        return StringUtils.isBlank(keywords) ? keywords : keywords.trim();
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    
    public String getDescription() {
        return StringUtils.isBlank(description) ? description : description.trim();
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    
    public Date getWeightDate() {
        return weightDate;
    }
    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    
    public Integer getHits() {
        return hits;
    }
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    
    public String getPosid() {
        return StringUtils.isBlank(posid) ? posid : posid.trim();
    }
    public void setPosid(String posid) {
        this.posid = posid;
    }
    
    
    public String getCreateBy() {
        return StringUtils.isBlank(createBy) ? createBy : createBy.trim();
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    public String getUpdateBy() {
        return StringUtils.isBlank(updateBy) ? updateBy : updateBy.trim();
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    public String getRemarks() {
        return StringUtils.isBlank(remarks) ? remarks : remarks.trim();
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    public String getDelFlag() {
        return StringUtils.isBlank(delFlag) ? delFlag : delFlag.trim();
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    
    
    public String getContent() {
        return StringUtils.isBlank(content) ? content : content.trim();
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
	
	/**
	 * 插入之前执行方法，需要手动调用
	 * @return this
	 */
	public void preInsert(){
		if(this.articleId==null || "".equals(this.articleId)){
			this.setArticleId(UuidUtils.creatUUID());
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
		if(!(this.articleId==null || "".equals(this.articleId))){
//			User user=(User) UserUtils.getPrincipal();
//			this.updateBy = user.getId();
//			this.updateDate=new Date();
		}
		return this;
	}
}