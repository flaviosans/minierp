package dev.flaviosantos.minierp.service;

import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;

public interface ItemServiceInterface {
	void addItem(Order order, Item item);
	void removeItem(Order order, Item item);
}
