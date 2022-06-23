package com.ibra.spring.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	private Long id;
	private String name;
	private String pro_Description;
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPro_Description() {
		return pro_Description;
	}

	public void setPro_Description(String pro_Description) {
		this.pro_Description = pro_Description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", pro_Description=" + pro_Description + ", price=" + price
				+ "]";
	}

}
