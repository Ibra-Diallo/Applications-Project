package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.model.Customer;
import com.customer.service.CustomerService;

@Controller
@ControllerAdvice
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService cs;

	public CustomerService getCs() {
		return cs;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/getcustomer")
	public String showCreateCustomerForm(Model model) {
		model.addAttribute("formData", new Customer());
		return "customer/create";
	}
	
//	@PostMapping("/create")
//	public String doCreateCustomer(@ModelAttribute("formData") Customer customer, Model model) {
//		
//		model.addAttribute("name", customer.getName());
//		model.addAttribute("Address", customer.getAddress());
//		model.addAttribute("phone", customer.getPhone());
//		model.addAttribute("state", customer.getState());
//		
//		
//		return "customer/view";
//		
//	}

}
