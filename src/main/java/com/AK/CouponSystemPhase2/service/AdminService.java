package com.AK.CouponSystemPhase2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;

@Service
public class AdminService {

	@Autowired
	CompanyRepository repo;
	
	public void deleteCompany(long id) {
		 Company company = repo.findById(id).get();
		 repo.delete(company);
	}
}
