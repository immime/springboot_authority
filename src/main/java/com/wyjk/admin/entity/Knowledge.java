package com.wyjk.admin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the knowledge database table.
 * 
 */
@Entity
@NamedQuery(name="Knowledge.findAll", query="SELECT k FROM Knowledge k")
public class Knowledge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String author;

	@Column(name="category_id")
	private Integer categoryId;

	private String content;

	@Column(name="content_short")
	private String contentShort;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_create")
	private Date gmtCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_modified")
	private Date gmtModified;

	@Column(name="icon_url")
	private String iconUrl;
	
	@Column(name="video_url")
	private String videoUrl;

	@Column(name="is_good")
	private Integer isGood;

	@Column(name="is_top")
	private Integer isTop;

	private String keyword;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="publish_time")
	private Date publishTime;

	@Column(name="short_title")
	private String shortTitle;

	@Column(name="sort_order")
	private Integer sortOrder;

	private String source;

	@Column(name="source_uri")
	private String sourceUri;

	private Integer status;

	@Column(name="thumb_url")
	private String thumbUrl;

	private String title;
	
	@PrePersist
	public void prePersist() {
		this.gmtCreate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.gmtModified = new Date();
	}
	
	public Knowledge() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getContentShort() {
		return this.contentShort;
	}

	public void setContentShort(String contentShort) {
		this.contentShort = contentShort;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getIsGood() {
		return this.isGood;
	}

	public void setIsGood(Integer isGood) {
		this.isGood = isGood;
	}

	public Integer getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getShortTitle() {
		return this.shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceUri() {
		return this.sourceUri;
	}

	public void setSourceUri(String sourceUri) {
		this.sourceUri = sourceUri;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getThumbUrl() {
		return this.thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the videoUrl
	 */
	public String getVideoUrl() {
		return videoUrl;
	}
	

	/**
	 * @param videoUrl the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	

}