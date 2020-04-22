package com.AK.CouponSystemPhase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CouponSystemPhase2Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponSystemPhase2Application.class, args);
		System.out.println("Go");
	}

//	RestTemplate restTemplate = new RestTemplate();

	/*
	 * // getCustomerByEmailAndPassword
	 * System.out.println(restTemplate.getForObject(
	 * "http://localhost:8080/customer/getName?email=Naftalin@gmail.com&pass=1234",
	 * Customer.class));
	 * 
	 * // getAllCustomers System.out.println(restTemplate.getForObject(
	 * "http://localhost:8080/customer/allcstmrs", Customer.class));
	 * 
	 * // getOneCustomer (byID) System.out.println(restTemplate.getForObject(
	 * "http://localhost:8080/customer/getname?id=4451", Customer.class);
	 * 
	 * // add System.out.println(restTemplate.postForObject(
	 * "http://localhost:8080/customer/addcstmr/postForObject",c1,Customer.class);
	 * 
	 * // delete System.out.println(restTemplate.deleteForObject(
	 * "http://localhost:8080/customer/deletecstmr?id=4451", Customer.class);
	 * 
	 * // update System.out.println(restTemplate.updateForObject(
	 * "http://localhost:8080/customer/addcstmr/updateForObject",c1,Customer.class);
	 */
}
