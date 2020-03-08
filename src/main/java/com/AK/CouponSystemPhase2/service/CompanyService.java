package com.AK.CouponSystemPhase2.service;

import java.util.GregorianCalendar;
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
import com.AK.CouponSystemPhase2.repo.CompanyRepository;
import com.AK.CouponSystemPhase2.repo.CouponRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository repoCompany;
	@Autowired
	CouponRepository repoCoupon;

//	Coupon CTOR w/o couponId
//	long companyID, int categoryID, String title, String description, Date startDate, Date endDate,
//	Integer amount, double price, String image, Category category	

	@PostConstruct
	public void InitDB() {
		int currentYear = new GregorianCalendar().get(Calendar.YEAR);
		Calendar startDate = new GregorianCalendar();
		Calendar endDate = new GregorianCalendar();
		
		repoCoupon.deleteAll();
		
		List<Coupon> coupons = new ArrayList<>();
		startDate.set(Calendar.YEAR, new Random().nextInt((currentYear + 30) - currentYear) + currentYear);
		endDate = startDate;
		endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
		coupons.add(new Coupon(666, 1, "Buy one shoe - get the other for free", "Shoes don't have to come in pairs",
				startDate, endDate, 13, 33.99, "image", Category.Clothes));

		List<Coupon> coupons1 = new ArrayList<>();
		startDate.set(Calendar.YEAR, new Random().nextInt((currentYear + 30) - currentYear) + currentYear);
		endDate = startDate;
		endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR) + 1);
		coupons1.add(new Coupon(999, 1, "Println", "Print LN on T-shirt", startDate, endDate, 200, 99.33, "image",
				Category.Clothes));

		repoCompany.deleteAll();
		List<Company> companies = new ArrayList<>();
		companies.add(new Company(666,"Tavor", "Tavor@gmail.com", "31415"));
		companies.add(new Company(999,"Galil", "Galil@gmail.com", "75988"));
		repoCompany.saveAll(companies);
	}

	public Coupon addCoupon(Coupon coupon) {
		return repoCoupon.save(coupon);
	}

	public void updateCoupon(long id, Coupon newCoupon) {
		Optional<Coupon> coupon = repoCoupon.findById(id);
		if (coupon.isPresent()) {
			newCoupon.setId(id);
			repoCoupon.save(newCoupon);
		}
	}

	public void deleteCouponById(long couponId) {
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
