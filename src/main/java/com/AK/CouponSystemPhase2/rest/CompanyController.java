package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CompanyService service;

	@GetMapping("getcustomer")
	public ResponseEntity<?> getCompanyByEmailAndPassword(@RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass) {
		return new ResponseEntity<Company>((Company) service.getCompany(email, pass), HttpStatus.OK);
	}

}
