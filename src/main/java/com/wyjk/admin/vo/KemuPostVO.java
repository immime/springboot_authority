package com.wyjk.admin.vo;

import java.io.Serializable;

public class KemuPostVO implements Serializable {
	private static final long serialVersionUID = -3836468394779540817L;
	private Integer id;
	private String thumbUrl;
	private String videoUrl;
	private String title;
	private String description;
	private String content;
	
	
	public KemuPostVO() {
	}
	
	public KemuPostVO(Integer id, String thumbUrl, String videoUrl, String title, String description, String content) {
		super();
		this.id = id;
		this.thumbUrl = thumbUrl;
		this.videoUrl = videoUrl;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	


	/**
	 * @return the thumbUrl
	 */
	public String getThumbUrl() {
		return thumbUrl;
	}
	


	/**
	 * @param thumbUrl the thumbUrl to set
	 */
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
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
	


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
	
}
