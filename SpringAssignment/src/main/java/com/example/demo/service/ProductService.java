package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public ResponseEntity<?> save(Product product) {
		 Product savedProduct = repository.save(product);
		return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
	}

	public ResponseEntity<?> getProductById(String id) {
		Optional<Product> product = repository.findById(id);
		
		if(product.isPresent())
			return new ResponseEntity<>(product.get(),HttpStatus.FOUND);
		else
		return new ResponseEntity<>(new ResourceNotFoundException(" Resource not found for id: " +id), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> updateProductById(Product product, String id) {
		Optional<Product> oldProduct = repository.findById(id);
		if(oldProduct.isPresent()) {
			 Product newProduct = repository.save(product);
			return ResponseEntity.ok(newProduct);
		}
		else 
		return new ResponseEntity<>(new ResourceNotFoundException(" Resource not found for id: " +id), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> deleteProductById(String id) {
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()) {
			repository.delete(product.get());
			return ResponseEntity.ok("Deleted Successfully");
		}
		else 
			return new ResponseEntity<>(new ResourceNotFoundException(" Resource not found for id: " +id), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> getProductByCategoryOnAvailability(String category, Pageable pageable) {
		
		Query query =  new Query().with(pageable);
		
		if(category != null && !category.isEmpty()) {
			query.addCriteria(Criteria.where("inventory.available").gt(0).andOperator(Criteria.where("name").regex(category)));
		}
		
		Page<Product> product = PageableExecutionUtils.getPage(mongoTemplate.find(query, Product.class), pageable,
				()->mongoTemplate.count(query.skip(0).limit(0), Product.class));
		
		if(product.isEmpty())
			return new ResponseEntity<>(new ResourceNotFoundException(" Resources are not available for category "+ category), HttpStatus.NOT_FOUND);
		else
		    return new ResponseEntity<>(product, HttpStatus.FOUND);
	}

	public ResponseEntity<?> getProductByPrice(String minPrice, String maxPrice, Pageable pageable) {
		
		Query query = new Query().with(pageable);
        //List<Criteria> criteria = new ArrayList<>();
        		
        if(minPrice !=null && maxPrice !=null) {
        	query.addCriteria(Criteria.where("price.amount").gte(minPrice).lte(maxPrice));
        }
        System.out.println("query: "+query);
        Page<Product> product = PageableExecutionUtils.getPage(mongoTemplate.find(query, Product.class), pageable,
				()->mongoTemplate.count(query.skip(0).limit(0), Product.class));
        System.out.println("output: "+product);
        
        if(product.isEmpty())
			return new ResponseEntity<>(new ResourceNotFoundException(" Resources are not available"), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(product, HttpStatus.FOUND);
	}
	
	/*//search person
    @Override
    public Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {

        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if(name !=null && !name.isEmpty()) {
            criteria.add(Criteria.where("firstName").regex(name,"i"));
        }

        if(minAge !=null && maxAge !=null) {
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }

        if(city !=null && !city.isEmpty()) {
            criteria.add(Criteria.where("addresses.city").is(city));
        }

        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Person> people = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class), pageable, () -> mongoTemplate.count(query.skip(0).limit(0),Person.class));
        return people;
    }*/

}
