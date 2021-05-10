package dev.flaviosantos.minierp.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import dev.flaviosantos.minierp.dto.OrderDto;
import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.model.Order;
import dev.flaviosantos.minierp.service.OrderServiceInterface;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderServiceInterface orderService;
	private ModelMapper modelMapper;

	@Autowired
	public OrderController(
			OrderServiceInterface orderService,
			ModelMapper modelMapper) {
		this.orderService = orderService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<OrderDto> getOrders() {
		
		var orderList = this.orderService.getOrders();
		var orderDtoList = orderList.stream().map(l -> {
			return this.modelMapper.map(l, OrderDto.class);
		}).collect(Collectors.toList());
		
		return orderDtoList;
	}
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable UUID id) throws ResourceNotFoundException {
		return this.orderService.getOrder(id);
	}

	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		return this.orderService.createOrder(order);
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
