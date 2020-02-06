package com.AK.CouponSystemPhase2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.beans.Company;
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
//		Customer c1 = new Customer();
//		c1.setCoupons(null);
//		c1.setEmail("Naftalin@gmail.com");
//		c1.setFirstName("Naftalin");
//		c1.setLastName("Cohen");
//		c1.setPassword("1234");		
//		customers.add(c1);		
		repo.saveAll(customers);
	}

	// multi condition
	public Customer getCustomer(String email, String password) {
		return repo.findCustomerByEmailAndPassword(email, password);
	}

	public Customer addCustomer(Customer customer) {
		return repo.save(customer);
	}

	public Customer updateCustomer(Customer oldCustomer, Customer newCustomer) {
		return repo.save(newCustomer);
	}

	public List<Customer> deleteCustomer(int id) {
		return repo.deleteCustomerByid(id);
	}

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}
	
	public Optional<Customer> getOneCustomer(long id) {
		return repo.findById(id);
	}

}
