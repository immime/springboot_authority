package com.wyjk.admin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FeedbackVO implements Serializable {
	private static final long serialVersionUID = 5254291595268333637L;
	
	private Integer id;

	private String content;

	private Date gmtCreate;

	private Date gmtModified;

	private String imageUrls;

	private String ip;

	private String nickname;

	private String phone;

	private String platform;

	private Integer status;
	
	private List<String> imgs;
	
	public FeedbackVO() {
	}

	public FeedbackVO(Integer id, String content, Date gmtCreate, Date gmtModified, String imageUrls, String ip,
			String nickname, String phone, String platform, Integer status, List<String> imgs) {
		super();
		this.id = id;
		this.content = content;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.imageUrls = imageUrls;
		this.ip = ip;
		this.nickname = nickname;
		this.phone = phone;
		this.platform = platform;
		this.status = status;
		this.imgs = imgs;
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
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	

	/**
	 * @param gmtCreate the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	

	/**
	 * @return the gmtModified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}
	

	/**
	 * @param gmtModified the gmtModified to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	

	/**
	 * @return the imageUrls
	 */
	public String getImageUrls() {
		return imageUrls;
	}
	

	/**
	 * @param imageUrls the imageUrls to set
	 */
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}
	

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	/**
	 * @return the imgs
	 */
	public List<String> getImgs() {
		return imgs;
	}
	

	/**
	 * @param imgs the imgs to set
	 */
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	

}
