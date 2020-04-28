package com.AK.CouponSystemPhase2;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CouponRepository;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;
import com.AK.CouponSystemPhase2.service.CompanyService;

@Component
public class DailyJob {

	@Autowired
	private CouponRepository repoCoupon;
	@Autowired
	CompanyService serviceCompany;

	@Scheduled(fixedRate = 86_400_000)
	public void deleteExpiredCoupons() {
		Calendar currentTime = Calendar.getInstance();
		List<Coupon> coupons = repoCoupon.findAll();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(currentTime)) {
				serviceCompany.deleteCouponById(coupon.getId());
			}
		}
	}
}
