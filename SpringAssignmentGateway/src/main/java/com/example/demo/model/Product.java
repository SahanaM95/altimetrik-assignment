package com.example.demo.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	private String id;
	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<Attributes> attributes;
	
	
}
