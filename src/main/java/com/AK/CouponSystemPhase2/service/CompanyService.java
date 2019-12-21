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
		List<Company> companies = new ArrayList<>();
	}
}
