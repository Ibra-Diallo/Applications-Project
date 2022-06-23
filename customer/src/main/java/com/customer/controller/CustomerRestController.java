package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
@RequestMapping("/customerapi")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}

}
