package dev.flaviosantos.minierp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDto {
	
	private UUID id;
	
	private ProductDto product;
	
	private BigDecimal qty;
	
	private BigDecimal discount;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

}
