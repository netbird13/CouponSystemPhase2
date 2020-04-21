package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Category;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CouponRepository;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repoCustomer;
	@Autowired
	private CouponRepository repoCoupon;

//	@PostConstruct
//	public void InitDB() {
//		repoCustomer.deleteAll();
//		List<Customer> customers = new ArrayList<>();
//		customers.add(new Customer("Naftalin", "Cohen", "Naftalin@gmail.com", "1234"));
//		customers.add(new Customer("Matisyahu", "Makabi", "Matisyahu@gmail.com", "5678"));
//		repoCustomer.saveAll(customers);
//	}

	public ResponseEntity<?> purchaseCouponService(long customerId, long couponid) {
        Optional<Customer> existCustomer = repoCustomer.findById(customerId);
        Optional<Coupon> existCoupon = repoCoupon.findById(couponid);
        if (existCustomer.isEmpty()) {
            return new ResponseEntity<>("This customer doesn't exist in our database", HttpStatus.BAD_REQUEST);
        }
        if (existCoupon.isEmpty()) {
            return new ResponseEntity<>("This coupon doesn't exist in our database", HttpStatus.BAD_REQUEST);
        }
        if (existCoupon.get().getAmount() == 0) {
            return new ResponseEntity<>("Purchase FAILED: This coupon is out of stock", HttpStatus.BAD_REQUEST);
        }
        List<Coupon> customerCoupons = existCustomer.get().getCoupons();
        for (Coupon coupon : customerCoupons) {
            if (coupon.getId() == couponid) {
                return new ResponseEntity<>("Current customer already has this coupon", HttpStatus.BAD_REQUEST);
            }
        }
        Calendar couponExpirationDate = existCoupon.get().getEndDate();
        Calendar currentDate = Calendar.getInstance();       
        if (couponExpirationDate.before(currentDate)) {
            return new ResponseEntity<>("This coupon has already expired", HttpStatus.BAD_REQUEST);
        }
        existCustomer.get().getCoupons().add(existCoupon.get());
        existCoupon.get().setAmount(existCoupon.get().getAmount() - 1);
        repoCustomer.save(existCustomer.get());
        repoCoupon.save(existCoupon.get());
        return new ResponseEntity<>("the coupon with id:" + couponid + " was purchased successfully", HttpStatus.OK);
    }

	public List<Coupon> getCustomerCoupons(long customerId) {
		Optional<Customer> existCustomer = repoCustomer.findById(customerId);
		List<Coupon> customerCoupons = repoCustomer.getOne(existCustomer.get().getId()).getCoupons();
		return customerCoupons;
	}

	public List<Coupon> getCustomerCouponsByCategory(long customerId, Category category) {
		Optional<Customer> existCustomer = repoCustomer.findById(customerId);
		List<Coupon> customerCoupons = repoCustomer.getOne(existCustomer.get().getId()).getCoupons();
		List<Coupon> customerCouponsByCategory = null;
		for (Coupon coupon : customerCoupons) {
			if (coupon.getCategory().equals(category)) {
				customerCouponsByCategory.add(coupon);
			}
		}
		return customerCouponsByCategory;
	}

	public List<Coupon> getCustomerCouponsByMaxPrice(long customerId, double price) {
		Optional<Customer> existCustomer = repoCustomer.findById(customerId);
		List<Coupon> customerCoupons = repoCustomer.getOne(existCustomer.get().getId()).getCoupons();
		List<Coupon> customerCouponsByMaxPrice = null;
		for (Coupon coupon : customerCoupons) {
			if (coupon.getPrice() <= price) {
				customerCouponsByMaxPrice.add(coupon);
			}
		}
		return customerCouponsByMaxPrice;
	}

	public String getCustomerDetails(long customerId) {
		String customerDetails = repoCustomer.getOne(customerId).toString();
		return customerDetails;
	}
}
