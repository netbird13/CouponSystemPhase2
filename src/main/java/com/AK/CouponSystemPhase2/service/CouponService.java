package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.repo.CouponRepository;

@Service
public class CouponService {
	
	@Autowired
	private CouponRepository repo;
	
	public void initDB() {
		repo.deleteAll();
		List <Coupon>coupons = new ArrayList<>(); 
		// Enter here your coupons:
		
		
		repo.saveAll(coupons);
	}

}
