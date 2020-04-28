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

@Component
public class DailyJob {

	@Autowired
	private CompanyRepository repoCompany;
	@Autowired
	private CustomerRepository repoCustomer;

	
	@Scheduled(fixedRate = 7000)
	public void deleteExpiredCoupons() {
		Calendar currentTime = Calendar.getInstance();
		
		List<Customer> customers = repoCustomer.findAll();
		List<Company> companies = repoCompany.findAll();
		for (Company company : companies) {
			List<Coupon> companyCoupons = company.getCoupons();
			for (Coupon coupon : companyCoupons) {
				if (coupon.getEndDate().before(currentTime)) {
					companyCoupons.remove(coupon);
				}
			}
		}
		repoCompany.saveAll(companies);

		for (Customer customer : customers) {
			List<Coupon> customerCoupons = customer.getCoupons();
			for (Coupon coupon : customerCoupons) {
				if (coupon.getEndDate().before(currentTime)) {
					customerCoupons.remove(coupon);
				}
			}
		}
		repoCustomer.saveAll(customers);
	}
}
