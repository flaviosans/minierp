package dev.flaviosantos.minierp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.repository.ItemRepository;

@Service
public class ItemService implements ItemServiceInterface {

	private OrderServiceInterface orderService;
	private ItemRepository itemRepository;

	@Autowired
	public ItemService(OrderServiceInterface orderService, ItemRepository itemRepository) {
		this.orderService = orderService;
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> getItems(UUID orderId) {
		// var order = this.orderService.getOrder(orderId);
		var items = this.itemRepository.findByOrderId(orderId);
		return items;
	}

	@Override
	public Item addItem(UUID orderId, Item item) {
		var order = this.orderService.getOrder(orderId);
		order.add

	}

	@Override
	public void removeItem(UUID orderId, Item item) {
		// TODO Auto-generated method stub

	}

}
