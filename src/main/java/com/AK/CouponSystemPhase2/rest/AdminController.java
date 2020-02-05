package com.AK.CouponSystemPhase2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.service.AdminService;
import com.AK.CouponSystemPhase2.service.CompanyService;
import com.AK.CouponSystemPhase2.service.CustomerService;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	CustomerService customerService;
	@Autowired
	CompanyService companyService;	
	@Autowired
	AdminService adminService;
	
	//CUSTOMER

	@PostMapping("add")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<>(customer.getFirstName() + " " + customer.getLastName() + "was added successfully", HttpStatus.OK);
	}
	
	@GetMapping("getCustomer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable(name = "num") String id) {
		int cstmrId = Integer.parseInt(id);
		return new ResponseEntity<Customer>((Customer) customerService.getOneCustomer(cstmrId).get(), HttpStatus.OK);
	}
	
	@GetMapping("getCustomers")
	public ResponseEntity<?> getallCustomers() {
		return new ResponseEntity<Customer>((Customer) customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("getCompanies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity <List<Company>>(companyService.getAllCompanies(), HttpStatus.OK);
	}
	
	@GetMapping("getCompany")
	public ResponseEntity<?> getOneCompany(@RequestParam(name = "num") String id) {
		int cmpnyId = Integer.parseInt(id);
		return new ResponseEntity<Company>(companyService.getOneCompany(cmpnyId).get(), HttpStatus.OK);
	}
	
	@DeleteMapping("deleteCompany")
	public ResponseEntity<?> deleteCompany(@RequestParam(name = "num") String id) {
		int cmpnyId = Integer.parseInt(id);
		adminService.deleteCompany(cmpnyId);
		return new ResponseEntity<String>("Company deleted", HttpStatus.OK);
	} 
	
	//COMPANY
	
//	public ResponseEntity<?> addCompany(@RequestBody Company company){
//		companyService. (company)
//		return new ;
//	}
}
