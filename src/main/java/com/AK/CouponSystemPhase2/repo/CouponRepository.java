package com.AK.CouponSystemPhase2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	public List <Coupon>deleteCouponById(int id); // delete coupon

}
