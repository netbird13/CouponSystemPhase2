package com.AK.CouponSystemPhase2.service;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.hibernate.internal.util.compare.CalendarComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.CouponSystemPhase2Application;
import com.AK.CouponSystemPhase2.beans.Category;
import com.AK.CouponSystemPhase2.beans.Company;
import com.AK.CouponSystemPhase2.beans.Coupon;
import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CouponRepository;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository repoCompany;
	@Autowired
	CouponRepository repoCoupon;
	@Autowired
	CustomerRepository repoCustomer;

//	@PostConstruct
//	public void InitDB() {
//		int currentYear = new GregorianCalendar().get(Calendar.YEAR);
//		Calendar startDate = new GregorianCalendar();
//		Calendar endDate = new GregorianCalendar();
//
//		repoCoupon.deleteAll();
//
//		List<Coupon> coupons1 = new ArrayList<>();
//		startDate.set(Calendar.YEAR, new Random().nextInt((currentYear + 30) - currentYear) + currentYear);
//		endDate = startDate;
//		endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
//		coupons1.add(new Coupon(2, "Buy one shoe - get the other for free", "Shoes don't have to come in pairs",
//				startDate, endDate, 13, 33.99, "image", Category.Clothes));
//
//		List<Coupon> coupons2 = new ArrayList<>();
//		startDate.set(Calendar.YEAR, new Random().nextInt((currentYear + 30) - currentYear) + currentYear);
//		endDate = startDate;
//		endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
//		coupons2.add(new Coupon(2, "Println", "Print LN on T-shirt", startDate, endDate, 200, 99.33, "image",
//				Category.Clothes));
//
//		repoCompany.deleteAll();
//		List<Company> companies = new ArrayList<>();
//		Company company1 = new Company("Tavor", "Tavor@gmail.com", "31415");
//		Company company2 = new Company("Galil", "Galil@gmail.com", "75988");
//
//		for (Coupon coupon : coupons1) {
//			coupon.setCompanyID(company1.getId());
//		}
//		for (Coupon coupon : coupons2) {
//			coupon.setCompanyID(company2.getId());
//		}
//
//		company1.setCoupons(coupons1);
//		company2.setCoupons(coupons2);
//
//		companies.add(company1);
//		companies.add(company2);
//
//		repoCompany.saveAll(companies);
//	}

	public void addCoupon(Coupon coupon) {
		Optional<Company> company = repoCompany.findById(coupon.getCompanyID());
		company.get().getCoupons().add(coupon);
		repoCompany.save(company.get());
	}

	public void updateCoupon(long id, Coupon newCoupon) {
		Optional<Coupon> coupon = repoCoupon.findById(id);
		if (coupon.isPresent()) {
			newCoupon.setId(id);
			repoCoupon.save(newCoupon);
		}
	}

	public void deleteCouponById(long couponId) {
		Optional<Coupon> existCoupon = repoCoupon.findById(couponId);
		List<Customer> customers = repoCustomer.findAll();
		for (Customer customer : customers) {
			List<Coupon> coupons = customer.getCoupons();
			if (coupons != null) {
				coupons.removeIf(coupon -> coupon.getId() == couponId);
			}
		}
		repoCustomer.saveAll(customers);
		Optional<Company> company = repoCompany.findById(existCoupon.get().getCompanyID());
		company.get().getCoupons().removeIf(coupon -> coupon.getId() == couponId);
		repoCompany.save(company.get());
		repoCoupon.deleteById(couponId);
	}

	public List<Coupon> getCompanyCoupons(long companyId) {
		Optional<Company> company = repoCompany.findById(companyId);
		List<Coupon> companyCoupons = company.get().getCoupons();
		return companyCoupons;
	}

	public List<Coupon> getCompanyCouponsByCategory(long companyID, Category category) {
		List<Coupon> companyCouponsByCategory = repoCoupon.getCoupnsByCompanyIDAndCategory(companyID, category);
		return companyCouponsByCategory;
	}

	public List<Coupon> getCompanyCouponsByMaxPrice(long companyID, double price) {
		List<Coupon> coupons = repoCoupon.getCouponsByCompanyIDAndPriceLessThan(companyID, price);
		return coupons;
	}

	public String getCompanyDetails(long companyID) {
		String companyDetails = repoCompany.getOne(companyID).toString();
		return companyDetails;
	}

}
