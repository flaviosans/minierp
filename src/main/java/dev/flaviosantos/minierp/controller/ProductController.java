package dev.flaviosantos.minierp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Product;
import dev.flaviosantos.minierp.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getProducts() {
		return this.productService.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable UUID id) throws ResourceNotFoundException {
		return this.productService.getProduct(id);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		var savedProduct = this.productService.createProduct(product);
		return savedProduct;
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) throws ResourceNotFoundException {
		return this.productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable UUID id) {
		this.productService.deleteProduct(id);
	}
}
