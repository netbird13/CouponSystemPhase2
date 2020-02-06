package com.AK.CouponSystemPhase2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AK.CouponSystemPhase2.repo.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository repoCleint;
}
