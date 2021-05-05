package dev.flaviosantos.minierp.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flaviosantos.minierp.dto.ItemDto;
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
	
	@PostMapping
	public ItemDto createItem(@PathVariable UUID orderId, @RequestBody Item itemDto) {
		var item = this.modelMapper.map(orderId, itemDto);
		item = this.itemService.addItem(orderId, item);
	}

}
