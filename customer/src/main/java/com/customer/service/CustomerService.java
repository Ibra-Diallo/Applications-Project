package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerService {

		@Autowired
		private CustomerRepository customerRepository;
		
		public void addCustomer(Customer customer) {
			
			customerRepository.save(customer);
			
		}
}
