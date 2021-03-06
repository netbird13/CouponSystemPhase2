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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("admin")
public class AdminController extends ClientController {

	@Autowired
	AdminService adminService;

	// Company methods

	@PostMapping("addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		Company companyReturned = adminService.addCompany(company);
		if (companyReturned != null) {
			return new ResponseEntity<>(company.getId() + " " + company.getName() + " was added successfully.",
					HttpStatus.OK);
		}
		return new ResponseEntity<>("The company was NOT added. its name '" + company.getName() + "' or its email '"
				+ company.getEmail() + "' may already been in use.", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestParam(name = "id") String id, @RequestBody Company newCompany) {
		long companyId = Long.parseLong(id);
		Company companyReturned = adminService.updateCompany(companyId, newCompany);
		if (companyReturned != null) {
			return new ResponseEntity<>(companyId + " " + newCompany.getName() + " was updated successfully.",
					HttpStatus.OK);
		}
		return new ResponseEntity<>(
				newCompany.getName() + " was NOT updated! Its id '" + companyId + "' and its name are immutable.",
				HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("deleteCompany")
	public ResponseEntity<?> deleteCompany(@RequestParam(name = "id") String id) {
		int cmpnyId = Integer.parseInt(id);
		adminService.deleteCompany(cmpnyId);
		return new ResponseEntity<String>("Company deleted", HttpStatus.OK);
	}

	@GetMapping("getCompanies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<List<Company>>(adminService.getCompanies(), HttpStatus.OK);
	}

	@GetMapping("getCompany/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable String id) {
		int cmpnyId = Integer.parseInt(id);
		return new ResponseEntity<Company>((Company) adminService.getCompany(cmpnyId).get(), HttpStatus.OK);
	}

	// Customer methods

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		Customer customerReturned = adminService.addCustomer(customer);
		if(customerReturned != null) {
		return new ResponseEntity<>(customer.getFirstName() + " " + customer.getLastName() + " was added successfully",
				HttpStatus.OK);
		}
		return new ResponseEntity<>(customer.getFirstName() + " " + customer.getLastName() + " was NOT added: The email inserted: " + customer.getEmail() + " is already in use",
				HttpStatus.BAD_REQUEST);
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestParam(name = "id") String id, @RequestBody Customer newCustomer) {
		long cstmrId = Long.parseLong(id);
		adminService.updateCustomer(cstmrId, newCustomer);
		return new ResponseEntity<>(cstmrId + " " + newCustomer.getFirstName() + " " + newCustomer.getLastName()
				+ " was updated successfully", HttpStatus.OK);
	}

	@GetMapping("getCustomer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable String id) {
		int cstmrId = Integer.parseInt(id);
		return new ResponseEntity<Customer>((Customer) adminService.getCustomer(cstmrId).get(), HttpStatus.OK);
	}

	@GetMapping("getCustomers")
	public ResponseEntity<?> getallCustomers() {
		return new ResponseEntity<List<Customer>>(adminService.getCustomers(), HttpStatus.OK);
	}

	@DeleteMapping("deleteCustomer")
	public ResponseEntity<?> deleteCustomer(@RequestParam(name = "id") String id) {
		int cstmrId = Integer.parseInt(id);
		adminService.deleteCustomer(cstmrId);
		return new ResponseEntity<String>("Customer deleted", HttpStatus.OK);
	}
	
	@DeleteMapping("deleteExpiredCoupons")
	public ResponseEntity<?> deleteCoupons(){
		adminService.deleteExpiredCoupons();
		return new ResponseEntity<String>("Expired coupons deleted", HttpStatus.OK);
	}
	
}
