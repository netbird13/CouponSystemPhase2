package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.service.CustomerService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@GetMapping("getcustomer")
	public ResponseEntity<?> getCustomerByEmailAndPassword(@RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass) {
		return new ResponseEntity<Customer>((Customer) service.getCustomer(email, pass), HttpStatus.OK);
	}

}
