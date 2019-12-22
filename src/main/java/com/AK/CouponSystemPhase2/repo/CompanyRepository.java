package com.AK.CouponSystemPhase2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	// multi condition & ignore case
	public Company getCompanyByEmailAndPass(String email, String password);

	public Company deleteCompanyById(int id); // delete

}
