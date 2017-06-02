package com.wyjk.admin.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AccountVO implements Serializable {
	private static final long serialVersionUID = -7708071993675825050L;
	
	private Integer id;
	private String nickname;
	@JSONField(format="yyyy-MM-dd hh:mm:ss")
	private Date registerTime;
	private String phone;
	private Integer gender;
	private Integer status;
	
	public AccountVO() {
	}

	public AccountVO(Integer id, String nickname, Date registerTime, String phone, Integer gender, Integer status) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.registerTime = registerTime;
		this.phone = phone;
		this.gender = gender;
		this.status = status;
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
	 * @return the registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	
	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getGender() {
		return gender;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
}
