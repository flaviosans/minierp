package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Order;

public interface OrderServiceInterface {

	List<Order> getOrders();
	
	Order getOrder(UUID id) throws ResourceNotFoundException;
	
	Order createOrder(Order order);
	
	Order updateOrder(UUID id, Order order) throws ResourceNotFoundException;
	
	void deleteOrder(UUID id) throws ResourceNotFoundException;
}
