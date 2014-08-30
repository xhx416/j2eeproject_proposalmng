package com.tcs.ilp.fqpms.model;

import java.util.ArrayList;

public class User {
	private String userId;
	private Insurance insurace;
	private String userName;
	
	public Insurance getInsurace() {
		return insurace;
	}
	public void setInsurace(Insurance insurace) {
		this.insurace = insurace;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User(String userId, String userName, Insurance insurace) {
		super();
		this.userId = userId;
		this.insurace = insurace;
		this.userName = userName;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
}
