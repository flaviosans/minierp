package dev.flaviosantos.minierp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDto {
	
	private UUID id;
	
	private OrderDto order;
	
	private ProductDto product;
	
	private BigDecimal qty;
	
	private BigDecimal price;
	
	private BigDecimal discount;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	/*
	 * public OrderDto getOrder() { return order; }
	 * 
	 * public void setOrder(OrderDto order) { this.order = order; }
	 */

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

}
