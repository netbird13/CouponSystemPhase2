package com.AK.CouponSystemPhase2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.service.CompanyService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CompanyService service;

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity <List<Company>>( service.getAllCompanies(), HttpStatus.OK);
	}
	
	@GetMapping("getCustomer")
	public ResponseEntity<?> getCompanyByEmailAndPassword(@RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass) {
		return new ResponseEntity<Company>((Company) service.getCompany(email, pass), HttpStatus.OK);
	}
}
