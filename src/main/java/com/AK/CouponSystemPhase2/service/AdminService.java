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
		Optional<Company> companyInRepoName = repoCompany.getCompanyByName(company.getName());
		Optional<Company> companyInRepoEmail = repoCompany.getCompanyByEmail(company.getEmail());
		if (companyInRepoName.isPresent() || companyInRepoEmail.isPresent()) {
			return null;
		}
		return repoCompany.save(company);
	}

	public Company updateCompany(long id, Company newCompany) {
		Optional<Company> companyInRepoById = repoCompany.findById(id);
		if (!newCompany.getName().equalsIgnoreCase(companyInRepoById.get().getName())) {
			return null;
		}
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
		Company company = repoCompany.findById(id).get();
		if(repoCompany.getOne(id).getCoupons()!=null) {
			repoCompany.getOne(id).setCoupons(null);
		}
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
