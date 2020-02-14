package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Customer;
import com.AK.CouponSystemPhase2.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	@PostConstruct
	public void InitDB() {
		repo.deleteAll();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Naftalin", "Cohen", "Naftalin@gmail.com", "1234"));
		customers.add(new Customer("Matisyahu", "Makabi", "Matisyahu@gmail.com", "5678"));
		repo.saveAll(customers);
	}

}
