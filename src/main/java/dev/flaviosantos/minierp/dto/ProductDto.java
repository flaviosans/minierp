package dev.flaviosantos.minierp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDto {
	
	private UUID id;
	
	private String name;
	
	private String sku;
	
	private BigDecimal price;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
