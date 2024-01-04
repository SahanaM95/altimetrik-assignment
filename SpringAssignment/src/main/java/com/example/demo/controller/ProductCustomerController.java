package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products/customer")
public class ProductCustomerController {

	@Autowired
	ProductService service;
	
	
	@GetMapping("/search/category")
	public ResponseEntity<?> getProductByCategoryOnAvailability(
			@RequestParam String category,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
		
		Pageable pageable = PageRequest.of(page,size);
		return service.getProductByCategoryOnAvailability(category, pageable);
	}

	@GetMapping("/search/price")
	public ResponseEntity<?> getProductByPrice(
			@RequestParam String minPrice,
			@RequestParam String maxPrice,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
		
		Pageable pageable = PageRequest.of(page,size);
		return service.getProductByPrice(minPrice, maxPrice, pageable);
	}
	
	
}
