package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="feignClient", url="http://localhost:8080/products/customer")
public interface CustomerFeignUtil {

	@GetMapping("/search/category")
	public ResponseEntity<?> getProductByCategoryOnAvailability(
			@RequestParam String category,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size);
	
	@GetMapping("/search/price")
	public ResponseEntity<?> getProductByPrice(
			@RequestParam String minPrice,
			@RequestParam String maxPrice,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size);
}
