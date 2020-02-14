package com.AK.CouponSystemPhase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
