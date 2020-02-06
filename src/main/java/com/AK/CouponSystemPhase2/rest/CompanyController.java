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
		return new ResponseEntity<>("the coupon with id: " + coupon.getCompanyID() + " titled " + coupon.getTitle()
				+ " was added succussfully", HttpStatus.OK);
	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestParam(name = "num") String id, @RequestBody Coupon newCoupon) {
		long companyId = Long.parseLong(id);
		serviceCompany.updateCoupon(companyId, newCoupon);
		return new ResponseEntity<>("Coupon with id: " + newCoupon.getId() + "titled " + newCoupon.getTitle()+ " was succussfully updated ", HttpStatus.OK);
	}
	
	@DeleteMapping("deleteCoupon")
	public ResponseEntity<?> deleteCoupon(@RequestParam (name = "num")String id){
		
		return new ResponseEntity<>("The coupon was deleted succussfully", HttpStatus.OK);
	}

}
