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
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "purchase_order")
@Where(clause = "deleted = false")
public class Order {
	@Id
	@GeneratedValue
	private UUID id;

	private BigDecimal total = BigDecimal.ZERO;

	private BigDecimal discount = BigDecimal.ZERO;

	private String customerName;
	
	private boolean deleted = false;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
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
		this.calculate();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void addItem(Item item) {
		this.items.add(item);
		this.calculate();
	}

	public void removeItem(Item item) {
		this.items.remove(item);
		this.calculate();
	}

	public List<Item> getItems() {
		return new ArrayList<Item>(this.items);
	}

	public void calculate() {
		BigDecimal total = BigDecimal.ZERO;

		if (this.discount == null) {
			this.discount = BigDecimal.ZERO;
		}

		for (Item each : this.items) {
			if (!each.isHandWork()) {
				var qty = each.getQty();
				var price = each.getPrice();

				var pricePlusQty = price.multiply(qty);

				total = total.add(pricePlusQty);
			}
		}

		this.total = total.subtract(this.discount);
	}

	@PostLoad
	public void postLoad() {
		this.calculate();
	}
}
