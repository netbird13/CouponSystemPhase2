package com.AK.CouponSystemPhase2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CouponRepository;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;

@Service
public class AdminService {

	@Autowired
	CompanyRepository repoCompany;
	@Autowired
	CustomerRepository repoCustomer;
	@Autowired
	CouponRepository repoCoupon;

	// Company methods

	public Company addCompany(Company company) {
		Optional<Company> companyInRepoName = repoCompany.getCompanyByName(company.getName());
		Optional<Company> companyInRepoEmail = repoCompany.getCompanyByEmail(company.getEmail());
		if (companyInRepoName.isPresent() || companyInRepoEmail.isPresent()) {
			return null;
		}
		return repoCompany.save(company);
	}

	public Company updateCompany(long id, Company newCompany) {
		// Preventing from changing the id
		Optional<Company> companyInRepoById = repoCompany.findById(id);
		if (!newCompany.getName().equalsIgnoreCase(companyInRepoById.get().getName())) {
			return null;
		}
		// Preventing from changing into an in-use email by either another company or
		// customer
		if (!newCompany.getEmail().equals(companyInRepoById.get().getEmail())) {
			Optional<Company> companyInRepoEmail = repoCompany.getCompanyByEmail(newCompany.getEmail());
			Optional<Customer> customerInRepoEmail = repoCustomer.getCustomerByEmail(newCompany.getEmail());
			if (companyInRepoEmail.isPresent() || customerInRepoEmail.isPresent()) {
				return null;
			}
		}
		companyInRepoById.get().setEmail(newCompany.getEmail());
		companyInRepoById.get().setPassword(newCompany.getPassword());
		return repoCompany.save(companyInRepoById.get());
	}

	public void deleteCompany(long id) {
		// deleting company-coupons from customers-coupons
		Optional<Company> existCompany = repoCompany.findById(id);
		List<Customer> customers = repoCustomer.findAll();
		List<Coupon> companyCoupons = existCompany.get().getCoupons();
		for (Coupon coupon : companyCoupons) {
			for (Customer customer : customers) {
				List<Coupon> customerCoupons = customer.getCoupons();
				if (customerCoupons != null) {
					customerCoupons.removeAll(companyCoupons);
				}
			}
		}
		repoCustomer.saveAll(customers);
		repoCompany.delete(existCompany.get());
	}

	public List<Company> getCompanies() {
		return repoCompany.findAll();
	}

	public Optional<Company> getCompany(long id) {
		return repoCompany.findById(id);
	}

	// Customer methods
	public Customer addCustomer(Customer customer) {
		Optional<Customer> customr = repoCustomer.getCustomerByEmail(customer.getEmail());
		if (customr.isPresent()) {
			return null;
		}
		return repoCustomer.save(customer);
	}

	public void updateCustomer(long id, Customer newCustomer) {
		Optional<Customer> customer = repoCustomer.findById(id);
		if (customer.isPresent()) {
			newCustomer.setId(id); // the updated customer gets id already in the repository, thus overwrites id of
									// newCustomer if exists
			repoCustomer.save(newCustomer);
		}
	}

	public void deleteCustomer(long id) {
		Optional<Customer> customer = repoCustomer.findById(id);
		customer.get().getCoupons().clear();
		repoCustomer.save(customer.get());
		repoCustomer.delete(customer.get());
	}

	public List<Customer> getCustomers() {
		return repoCustomer.findAll();
	}

	public Optional<Customer> getCustomer(long id) {
		return repoCustomer.findById(id);
	}

}
