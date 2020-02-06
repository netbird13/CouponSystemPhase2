package com.AK.CouponSystemPhase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AK.CouponSystemPhase2.beans.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{	
}
