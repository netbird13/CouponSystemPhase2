package com.AK.CouponSystemPhase2.CouponSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AK.CouponSystemPhase2.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CompanyService service;
}
