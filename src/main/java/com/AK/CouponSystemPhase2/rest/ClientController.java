package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Client;
import com.AK.CouponSystemPhase2.service.ClientService;

@RestController
public abstract class ClientController {

	@Autowired
	ClientService clientService;

	public Client login(String email, String password) {
		return null;
	}
}
