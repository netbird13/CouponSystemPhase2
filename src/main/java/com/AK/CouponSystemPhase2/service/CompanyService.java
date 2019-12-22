package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repo;

	@PostConstruct
	public void InitDB() {
		repo.deleteAll();
		List<Company> companies = new ArrayList<>();
		companies.add(new Company("Tavor", "tavor@gmail.com", "31415"));
		repo.saveAll(companies);
	}

	public Company getCompany(String email, String pass) {
		return repo.getCompanyByEmailAndPass(email, pass);
	}

	public Company addCompany(Company company) {
		return repo.save(company);
	}

	public Company updateCompany(Company oldCompany, Company newCompany) {
		return repo.save(newCompany);
	}

	public Company deleteCompany(int id) {
		return repo.deleteCompanyById(id);
	}

	public List<Company> getAllCompanies() {
		return repo.findAll();
	}

	public Company getOneCompany(int id) {
		return repo.deleteCompanyById(id);
	}
}
