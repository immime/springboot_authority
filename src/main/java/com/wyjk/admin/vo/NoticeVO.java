package com.wyjk.admin.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable {
	private static final long serialVersionUID = -6854169094559892301L;
	private Integer id;
	private String title;
	private String iconUrl;
	private String content;

	public NoticeVO() {
	}
	
	public NoticeVO(Integer id, String title, String iconUrl, String content) {
		super();
		this.id = id;
		this.title = title;
		this.iconUrl = iconUrl;
		this.content = content;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl
	 *            the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
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
	

}
