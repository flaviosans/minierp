package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;

public interface ItemServiceInterface {
	List<Item> getItems(UUID orderId);
	Item addItem(UUID orderId, Item item);
	void removeItem(UUID orderId, Item item);
}
