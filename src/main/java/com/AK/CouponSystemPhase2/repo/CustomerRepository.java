package com.AK.CouponSystemPhase2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// multi condition & ignore case
	public List<Customer> findCustomerByEmailAndPassword(String email, String password);

	public List<Customer> addCustomer(Customer customer);// add

	public List<Customer> updateCustomer(Customer customer); // update

	public List<Customer> deleteCustomerByID(int id); // delete

	public List<Customer> findAllCustomers();

	public List<Customer> findOneCustomer(int id);

}
