package dev.flaviosantos.minierp.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import dev.flaviosantos.minierp.dto.ProductDto;
import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Product;
import dev.flaviosantos.minierp.service.ProductServiceInterface;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductServiceInterface productService;
	private ModelMapper modelMapper;

	@Autowired
	public ProductController(ProductServiceInterface productService, ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<ProductDto> getProducts() {
		var productList = this.productService.getProducts();
		var productDtoList = productList.stream().map(p -> {
			return this.modelMapper.map(p, ProductDto.class);
		}).collect(Collectors.toList());
		return productDtoList;
	}

	@GetMapping("/{id}")
	public ProductDto getProduct(@PathVariable UUID id) throws ResourceNotFoundException {
		var product = this.productService.getProduct(id);
		return this.modelMapper.map(product, ProductDto.class);
	}

	@PostMapping
	public ProductDto createProduct(@RequestBody ProductDto productDto) {
		var product = this.modelMapper.map(productDto, Product.class);
		var savedProduct = this.productService.createProduct(product);
		return this.modelMapper.map(savedProduct, ProductDto.class);
	}

	@PutMapping("/{id}")
	public ProductDto updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto)
			throws ResourceNotFoundException {
		var product = this.modelMapper.map(productDto, Product.class);
		var updatedProduct = this.productService.updateProduct(id, product);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable UUID id) throws ResourceNotFoundException {
		this.productService.deleteProduct(id);
	}
}
