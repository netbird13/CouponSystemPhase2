package com.AK.CouponSystemPhase2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;

@Service
public class AdminService {

	@Autowired
	CompanyRepository repoCmpny;
	@Autowired
	CustomerRepository repoCstmr;
	
	
	// Company methods	
	public Company addCompany(Company company) {
		return repoCmpny.save(company);
	}
	
	public void updateCompany(long id, Company newCompany) {
		Optional<Company> company = repoCmpny.findById(id);
		if(company.isPresent()) {
			newCompany.setId(id);
			repoCmpny.save(newCompany);
		}
	}
	
	public void deleteCompany(long id) {
		 Company company = repoCmpny.findById(id).get();
		 repoCmpny.delete(company);
	}

	public List<Company> getCompanies() {
		return repoCmpny.findAll();
	}	
	
	public Optional<Company> getCompany(long id) {
		return repoCmpny.findById(id);
	}	
		
	// Customer methods	
	public Customer addCustomer(Customer customer) {
		return repoCstmr.save(customer);
	}
	
	public void updateCustomer(long id, Customer newCustomer) {
		Optional<Customer> customer = repoCstmr.findById(id);
		if(customer.isPresent()){
			newCustomer.setId(id);
			repoCstmr.save(newCustomer);
		}
	}
	
	public void deleteCustomer(long id) {
		Customer customer = repoCstmr.findById(id).get();
		 repoCstmr.delete(customer);
	}
	
	public List<Customer> getCustomers(){
		return repoCstmr.findAll();
	}
	
	public Optional<Customer> getCustomer(long id){
		return repoCstmr.findById(id);
	}
	
}
