package dev.flaviosantos.minierp.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flaviosantos.minierp.dto.ItemDto;
import dev.flaviosantos.minierp.exception.ResourceNotFoundException;
import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.service.ItemServiceInterface;

@RestController
@RequestMapping("/order/{orderId}/item")
public class ItemController {

	private ItemServiceInterface itemService;
	private ModelMapper modelMapper;

	@Autowired
	public ItemController(ItemServiceInterface itemService, ModelMapper modelMapper) {
		this.itemService = itemService;
		this.modelMapper = modelMapper;
  }

	@GetMapping
	public List<ItemDto> getItems(@PathVariable UUID orderId) {
		var items = this.itemService.getItems(orderId);
		var itemsDto = items.stream().map(i -> {
			return this.modelMapper.map(i, ItemDto.class);
		}).collect(Collectors.toList());
		return itemsDto;
	}
	
	@GetMapping("/{id}")
	public ItemDto getItem(@PathVariable UUID orderId, @PathVariable UUID id) throws ResourceNotFoundException {
		var item = this.itemService.getItem(orderId, id);
		return this.modelMapper.map(item, ItemDto.class);
	}
	
	@PostMapping
	public ItemDto createItem(@PathVariable UUID orderId, @RequestBody ItemDto itemDto) throws ResourceNotFoundException {
		var item = this.modelMapper.map(itemDto, Item.class);
		item = this.itemService.createItem(orderId, item);
		return this.modelMapper.map(item, ItemDto.class);
	}
	
	@PutMapping("/{id}")
	public ItemDto updateItem(@PathVariable UUID orderId, @PathVariable UUID id, @RequestBody ItemDto itemDto) throws ResourceNotFoundException {
		var item = this.modelMapper.map(itemDto, Item.class);
		var updatedItem = this.itemService.updateItem(orderId, id, item);
		return this.modelMapper.map(updatedItem, ItemDto.class);
	}
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable UUID orderId, @PathVariable UUID id) {
		this.itemService.deleteItem(orderId, id);
	}

}
