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
	CompanyRepository repoCompany;
	@Autowired
	CustomerRepository repoCustomer;

	// Company methods
	public Company addCompany(Company company) {
		return repoCompany.save(company);
	}

	public void updateCompany(long id, Company newCompany) {
		Optional<Company> company = repoCompany.findById(id);
		if (company.isPresent()) {
			newCompany.setId(id);
			repoCompany.save(newCompany);
		}
	}

	public void deleteCompany(long id) {
		Company company = repoCompany.findById(id).get();
		repoCompany.delete(company);
	}

	public List<Company> getCompanies() {
		return repoCompany.findAll();
	}

	public Optional<Company> getCompany(long id) {
		return repoCompany.findById(id);
	}

	// Customer methods
	public Customer addCustomer(Customer customer) {
		return repoCustomer.save(customer);
	}

	public void updateCustomer(long id, Customer newCustomer) {
		Optional<Customer> customer = repoCustomer.findById(id);
		if (customer.isPresent()) {
			newCustomer.setId(id);
			repoCustomer.save(newCustomer);
		}
	}

	public void deleteCustomer(long id) {
		Customer customer = repoCustomer.findById(id).get();
		repoCustomer.delete(customer);
	}

	public List<Customer> getCustomers() {
		return repoCustomer.findAll();
	}

	public Optional<Customer> getCustomer(long id) {
		return repoCustomer.findById(id);
	}

}
