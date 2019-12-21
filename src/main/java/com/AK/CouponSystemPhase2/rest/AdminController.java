package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.service.CustomerService;


@RestController
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	CustomerService service;
	
	@PostMapping("add")
	public Customer addCustomer (@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}
}
