package com.ibra.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibra.spring.data.entities.Product;
import com.ibra.spring.data.ropository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository repo1; 
	
	@GetMapping()   // This annotation will allow the client to use the Get method to get all products.
	public Iterable<Product> getProduct(){
		return repo1.findAll();
		
	}

}
