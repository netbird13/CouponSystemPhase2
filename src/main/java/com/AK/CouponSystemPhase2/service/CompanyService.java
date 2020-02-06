package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CouponRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository repoCompany;
	@Autowired
	CouponRepository repoCoupon;

	@PostConstruct
	public void InitDB() {
		repoCompany.deleteAll();
		List<Company> companies = new ArrayList<>();
		companies.add(new Company("Tavor", "tavor@gmail.com", "31415"));
		repoCompany.saveAll(companies);
	}

	public Coupon addCoupon(Coupon coupon) {
		return repoCoupon.save(coupon);
	}

	public void updateCoupon(long id, Coupon newCoupon) {
		Optional<Coupon> coupon = repoCoupon.findById(id);
		if (coupon.isPresent()) {
			newCoupon.setId(id);
			repoCoupon.save(newCoupon);
		}
	}

	public void deleteCoupon(long id) {
		Coupon coupon = repoCoupon.findById(id).get();
		repoCoupon.delete(coupon);
	}
	
	public List <Coupon> getCompanyCoupons (long companyId){
		
		return null;
	}
}
