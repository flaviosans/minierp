package dev.flaviosantos.minierp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Order;
import dev.flaviosantos.minierp.service.OrderServiceInterface;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderServiceInterface orderService;

	@Autowired
	public OrderController(OrderServiceInterface orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<Order> getOrders() {
		return this.orderService.getOrders();
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable UUID id) throws ResourceNotFoundException {
		return this.orderService.getOrder(id);
	}

	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		var savedOrder = this.orderService.createOrder(order);
		return savedOrder;
	}

	@PutMapping("/{id}")
	public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) throws ResourceNotFoundException {
		return this.orderService.updateOrder(id, order);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable UUID id) throws ResourceNotFoundException {
		this.orderService.deleteOrder(id);
	}
}
