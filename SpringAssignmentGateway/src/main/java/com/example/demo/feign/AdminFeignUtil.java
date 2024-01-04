package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Product;

@FeignClient(value="feignClient", url="http://localhost:8080/products/admin")
public interface AdminFeignUtil {

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable String id);
	
	@PostMapping("/save")
	public ResponseEntity<?> addProducts(@RequestBody Product product);
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductById(@RequestBody Product product, @PathVariable String id);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable String id);
}
