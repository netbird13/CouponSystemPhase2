package com.AK.CouponSystemPhase2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.beans.Category;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("company")
public class CompanyController extends ClientController {

	@Autowired
	CompanyService serviceCompany;

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		serviceCompany.addCoupon(coupon);
		return new ResponseEntity<>(
				"the coupon titled: " + coupon.getTitle() + " was added succussfully",
				HttpStatus.OK);
	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestParam(name = "num") String id, @RequestBody Coupon newCoupon) {
		long companyId = Long.parseLong(id);
		serviceCompany.updateCoupon(companyId, newCoupon);
		return new ResponseEntity<>("Coupon with id: " + newCoupon.getId() + "titled: " + newCoupon.getTitle()
				+ " was succussfully updated ", HttpStatus.OK);
	}

	// ToDo: a company can delete only its own coupons
	@DeleteMapping("deleteCoupon")
	public ResponseEntity<?> deleteCoupon(@RequestParam(name = "num") String id) {
		long couponId = Long.parseLong(id);
		serviceCompany.deleteCouponById(couponId);
		return new ResponseEntity<>("The coupon was deleted succussfully", HttpStatus.OK);
	}

	@GetMapping("getCoupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam(name = "num") String id) {
		long companyId = Long.parseLong(id);
		return new ResponseEntity<List<Coupon>>(serviceCompany.getCompanyCoupons(companyId), HttpStatus.OK);
	}

	@GetMapping("couponsByCat")
	public ResponseEntity<?> getCompnayCouponsByCategory(@RequestParam(name = "num") String id,
			@RequestParam(name = "cat") Category category) {
		long companyId = Long.parseLong(id);
		return new ResponseEntity<List<Coupon>>(serviceCompany.getCompanyCouponsByCategory(companyId, category),
				HttpStatus.OK);
	}

	@GetMapping("coupnsByMax")
	public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestParam(name = "num") String id,
			@RequestParam(name = "price") String price) {
		long companyId = Long.parseLong(id);
		long maxPrice = Long.parseLong(price);
		return new ResponseEntity<List<Coupon>>(serviceCompany.getCompanyCouponsByMaxPrice(companyId, maxPrice),
				HttpStatus.OK);
	}

	@GetMapping("details")
	public ResponseEntity<?> getCompanyDetails(@RequestParam(name = "num") String id) {
		long companyId = Long.parseLong(id);
		return new ResponseEntity<>(serviceCompany.getCompanyDetails(companyId), HttpStatus.OK);
	}

}
