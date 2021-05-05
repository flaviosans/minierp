package dev.flaviosantos.minierp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order")
public class Order {
	@Id
	@GeneratedValue
	private UUID id;
	
	private BigDecimal total;
	
	private BigDecimal discount;
	
	private String customerName;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
		this.recalculate();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		this.recalculate();
	}

	public void removeItem(Item item) {
		this.items.remove(item);
		this.recalculate();
	}
	
	public List<Item> getItems() {
		return new ArrayList<Item>(this.items);
	}
	
	public void recalculate() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(Item each: this.items) {
			var qty = each.getQty();
			var price = each.getPrice();
			
			var pricePlusQty = price.multiply(qty);
			
			total = total.add(pricePlusQty);
		}
		
		this.total = this.total.subtract(this.discount);
	}
}
