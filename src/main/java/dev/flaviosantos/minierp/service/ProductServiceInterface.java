package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Product;

public interface ProductServiceInterface {
	List<Product> getProducts();
	
	Product getProduct(UUID id) throws ResourceNotFoundException;
	
	Product updateProduct(UUID id, Product product) throws ResourceNotFoundException;
	
	void deleteProduct(UUID id);
}