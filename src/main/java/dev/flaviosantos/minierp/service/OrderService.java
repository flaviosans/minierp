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

	@Override
	public List<Order> getOrders() {
		var orders =  this.orderRepository.findAll();
		orders.stream().forEach(o -> {
			o.calculate();
		});
		
		return orders;
	}

	public Order getOrder(UUID id) throws ResourceNotFoundException {
		var optionalOrder = this.orderRepository.findById(id);
		var order = optionalOrder.orElseThrow(ResourceNotFoundException::new);
		order.calculate();
		
		return order;
	}

	public Order createOrder(Order order) {
		var savedOrder = this.orderRepository.save(order);
		
		for (Item cada : savedOrder.getItems()) {
			cada.setOrder(order);
		}
//		order.calculate();
		return order;
	}

	public Order updateOrder(UUID id, Order order) throws ResourceNotFoundException {
		var entityOrder = this.getOrder(id);
		entityOrder.setCustomerName(order.getCustomerName());
		entityOrder.setDiscount(order.getDiscount());
		
		return this.orderRepository.save(entityOrder);
	}

	public void deleteOrder(UUID id) throws ResourceNotFoundException {
		var order = this.getOrder(id);
		order.setDeleted(true);
	}
}
