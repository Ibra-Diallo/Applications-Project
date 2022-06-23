package com.ibra.spring.data;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.ibra.spring.data.entities.Product;
import com.ibra.spring.data.ropository.ProductRepository;

@SpringBootTest
public class SpringdatajpaApplicationTests {
	
	@Autowired
	ApplicationContext ctx;

	@Test
	public void saveProduct() {
		
		ProductRepository repo = ctx.getBean(ProductRepository.class);
		
		Product prod = new Product();
		prod.setId(11L);
		prod.setName("Mango");
		prod.setPro_Description("Delicious");
		prod.setPrice(8.99d);
		
//		repo.save(prod);
//		
//		//This Reads the data
//		Optional<Product> prod1 = repo.findById(11L);
//		System.out.println(prod1);
//		
//		//This Updates the price of this particular item in the database;
//		prod.setPrice(10.99d);
//		repo.save(prod);
		
		repo.findAll().forEach(p->{System.out.println(p.getPrice());});
		
		
		System.out.println(repo.findByName("Mango"));
		System.out.println(repo.findByNameAndPrice("Mango", 11d));
	}

}
