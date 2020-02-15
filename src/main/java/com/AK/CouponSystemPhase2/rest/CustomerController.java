package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("customer")
public class CustomerController extends ClientController {

	@Autowired
	CustomerService customerService;
	
	
}
