package com.AK.CouponSystemPhase2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// multi condition & ignore case
	public Customer findCustomerByEmailAndPassword(String email, String password);

	public List<Customer> deleteCustomerByid(int id); // delete

}
