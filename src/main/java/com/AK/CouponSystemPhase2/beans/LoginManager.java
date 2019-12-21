package com.AK.CouponSystemPhase2.beans;

import org.springframework.stereotype.Component;

@Component
public class LoginManager {

	private String name;
	private String password;
	
	public LoginManager() {
	}

	public LoginManager(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	
}
