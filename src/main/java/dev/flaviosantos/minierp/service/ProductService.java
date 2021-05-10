package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Product;
import dev.flaviosantos.minierp.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceInterface {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getProducts() {
		return this.productRepository.findAll(); // TODO: DESACOPLAR DO PAGE
	}

	public Product getProduct(UUID id) throws ResourceNotFoundException {
		var optionalProduct = this.productRepository.findById(id);
		return optionalProduct.orElseThrow(ResourceNotFoundException::new);
	}

	public Product createProduct(Product product) {
		return this.productRepository.save(product);
	}

	public Product updateProduct(UUID id, Product product) throws ResourceNotFoundException {
		var entityProduct = this.getProduct(id);

		entityProduct.setName(product.getName()); // TODO: CHECK FOR NULL
		entityProduct.setSku(product.getSku());
		entityProduct.setPrice(product.getPrice());

		return this.productRepository.save(entityProduct);
	}

	public void deleteProduct(UUID id) throws ResourceNotFoundException {
		var product = this.getProduct(id);

		this.productRepository.delete(product);
	}

}
