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
	public Item getItem(UUID orderId, UUID id) throws ResourceNotFoundException {
		var order = this.orderService.getOrder(orderId);
		var itemFromOrder = order.getItems().stream().filter(i -> {
			return i.getId() == id;
		}).findAny().orElseThrow(ResourceNotFoundException::new);
		
		return itemFromOrder;
	}
	
	@Override
	public List<Item> getItems(UUID orderId) {
		// TODO: VALIDAR SE PEDIDO EXISTE
		var items = this.itemRepository.findByOrderId(orderId);
		return items;
	}

	@Override
	public Item createItem(UUID orderId, Item item) throws ResourceNotFoundException {
		var order = this.orderService.getOrder(orderId);
		return this.itemRepository.save(item);

	}
	
	@Override
	public Item updateItem(UUID orderId, UUID id, Item item) throws ResourceNotFoundException {
		var itemFromEntity = this.getItem(orderId, id);
		itemFromEntity.setQty(item.getQty());
		return this.itemRepository.save(itemFromEntity);
	}

	@Override
	public void deleteItem(UUID orderId, UUID id) {
		this.itemRepository.deleteById(id);

	}

}
