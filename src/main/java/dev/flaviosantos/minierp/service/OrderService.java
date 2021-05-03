package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;
import dev.flaviosantos.minierp.repository.OrderRepository;

@Service
public class OrderService implements OrderServiceInterface {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getOrders() {
		return this.orderRepository.findAll();
	}

	public Order getOrder(UUID id) throws ResourceNotFoundException {
		var optionalOrder = this.orderRepository.findById(id);
		return optionalOrder.orElseThrow(ResourceNotFoundException::new);
	}

	public Order createOrder(Order order) {
		for (Item cada : order.getItems()) {
			cada.setOrder(order);
		}
		return this.orderRepository.save(order);
	}

	public Order updateOrder(UUID id, Order order) throws ResourceNotFoundException {
		var entityOrder = this.getOrder(id);

		// TODO: UPDATE ORDERS

		return this.orderRepository.save(entityOrder);
	}

	public void deleteOrder(UUID id) throws ResourceNotFoundException {
		var order = this.getOrder(id);
		this.orderRepository.delete(order);
	}
}
