package com.AK.CouponSystemPhase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AK.CouponSystemPhase2.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	
}
