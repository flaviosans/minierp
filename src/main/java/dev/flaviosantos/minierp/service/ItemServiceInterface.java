package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;

public interface ItemServiceInterface {
	List<Item> getItems(UUID orderId);
	Item getItem(UUID orderId, UUID id) throws ResourceNotFoundException;
	Item createItem(UUID orderId, Item item) throws ResourceNotFoundException;
	Item updateItem(UUID orderId, UUID id, Item item) throws ResourceNotFoundException;
	void deleteItem(UUID orderId, UUID id);
}
