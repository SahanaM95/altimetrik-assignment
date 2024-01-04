package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.feign.AdminFeignUtil;
import com.example.demo.feign.CustomerFeignUtil;
import com.example.demo.model.Product;

@RestController
@RequestMapping("/login")
public class Controller {

	@Autowired
	private AdminFeignUtil adminFeignUtil;
	
	@Autowired
	private CustomerFeignUtil custFeignUtil;
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<?> getProductById(@PathVariable String id){
		return adminFeignUtil.getProductById(id);
	}
	
	@PostMapping("/admin/save")
	public ResponseEntity<?> addProducts(@RequestBody Product product){
		return adminFeignUtil.addProducts(product);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updateProductById(@RequestBody Product product, @PathVariable String id){
		return adminFeignUtil.updateProductById(product, id);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable String id){
		return adminFeignUtil.deleteProductById(id);
	}
	
	@GetMapping("/customer/search/category")
	public ResponseEntity<?> getProductByCategoryOnAvailability(
			@RequestParam String category,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
		
		return custFeignUtil.getProductByCategoryOnAvailability(category,page,size);
	}

	@GetMapping("/cutomer/search/price")
	public ResponseEntity<?> getProductByPrice(
			@RequestParam String minPrice,
			@RequestParam String maxPrice,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
		
		return custFeignUtil.getProductByPrice(minPrice, maxPrice, page,size);
	}
}
