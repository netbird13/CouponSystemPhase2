package com.AK.CouponSystemPhase2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Category;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("customer")
public class CustomerController extends ClientController {

	@Autowired
	CustomerService serviceCustomer;

	@PostMapping("purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestParam(name = "num") String id, @RequestBody Coupon coupon) {
		long customerId = Long.parseLong(id);
		serviceCustomer.purchaseCoupon(customerId, coupon);
		return new ResponseEntity<>("the coupon with id: " + coupon.getId() + "titled: " + coupon.getTitle()
				+ " was purchased successfully", HttpStatus.OK);
	}

	@GetMapping("getCoupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestParam(name = "num") String id) {
		long customerId = Long.parseLong(id);
		return new ResponseEntity<List<Coupon>>(serviceCustomer.getCustomerCoupons(customerId), HttpStatus.OK);
	}

	@GetMapping("couponsByCat") // add ignoreCase to category
	public ResponseEntity<?> getCustomerCouponsByCategory(@RequestParam(name = "num") String id,
			@RequestParam(name = "cat") Category category) {
		long customerId = Long.parseLong(id);
		return new ResponseEntity<List<Coupon>>(serviceCustomer.getCustomerCouponsByCategory(customerId, category),
				HttpStatus.OK);
	}

	@GetMapping("couponsByMax")
	public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestParam(name = "num") String id,
			@RequestParam(name = "price") String price) {
		long customerId = Long.parseLong(id);
		long maxPrice = Long.parseLong(price);
		return new ResponseEntity<List<Coupon>>(serviceCustomer.getCustomerCouponsByMaxPrice(customerId, maxPrice),
				HttpStatus.OK);
	}

	@GetMapping("details")
	public ResponseEntity<?> getCustomerDetails(@RequestParam(name = "num") String id) {
		long customerId = Long.parseLong(id);
		return new ResponseEntity<>(serviceCustomer.getCustomerDetails(customerId), HttpStatus.OK);
	}

}
