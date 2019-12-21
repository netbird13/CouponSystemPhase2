package com.AK.CouponSystemPhase2.beans;

import org.springframework.stereotype.Component;

@Component
public class Admin {

	private String name;
	private String password;
	
	public Admin() {
	}

	public Admin(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	
}
