package com.AK.CouponSystemPhase2.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> getCustomerByEmail(String email);

}
