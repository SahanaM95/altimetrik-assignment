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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products/admin")
public class ProductAdminController {

	@Autowired
	ProductService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> addProducts(@RequestBody Product product){
		return service.save(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable String id){
		return service.getProductById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductById(@RequestBody Product product, @PathVariable String id){
		return service.updateProductById(product, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable String id){
		return service.deleteProductById(id);
	}
	
	
}
