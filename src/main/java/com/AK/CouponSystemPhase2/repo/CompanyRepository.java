package com.AK.CouponSystemPhase2.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Coupon;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
