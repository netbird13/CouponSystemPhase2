package com.AK.CouponSystemPhase2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.service.CompanyService;
import com.AK.CouponSystemPhase2.service.CustomerService;


@RestController
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	CustomerService customerService;
	CompanyService companyService;	
	
	//CUSTOMER

	@PostMapping("add")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<>(customer.getFirstName() + " " + customer.getLastName() + "was added successfully", HttpStatus.OK);
	}
	
	@GetMapping("getone/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable(name = "num") String id) {
		int cstmrId = Integer.parseInt(id);
		return new ResponseEntity<Customer>((Customer) customerService.getOneCustomer(cstmrId), HttpStatus.OK);
	}
	
	@GetMapping("getcustomers")
	public ResponseEntity<?> getallCustomers() {
		return new ResponseEntity<Customer>((Customer) customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	//COMPANY
	
	public ResponseEntity<?> addCompany(@RequestBody Company company){
		companyService. (company)
		return new ;
	}
}
