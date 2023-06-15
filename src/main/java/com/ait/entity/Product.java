package com.ait.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "product_tbl")
public class Product {
	
	@Id
	@GeneratedValue
	private Integer pId;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 3, max = 15, message = "Name should have only 3 to 15 characters")
	private String productName;
	
	@NotNull(message = "Price is mandatory")
	@Positive(message = "Price should be positive number")
	private Double productPrice;
	
	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity should be positive number")
    private Integer productQuantity;

}
