package com.AK.CouponSystemPhase2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public Optional <Company> getCompanyByName (String name);
	
	public Optional <Company> getCompanyByEmail (String email);
	
}
