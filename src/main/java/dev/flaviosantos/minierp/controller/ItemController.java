package dev.flaviosantos.minierp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flaviosantos.minierp.model.Item;
import dev.flaviosantos.minierp.service.ItemServiceInterface;

@RestController
@RequestMapping("/order/{orderId}/item")
public class ItemController {
	
	/*
	 * private ItemServiceInterface itemService;
	 * 
	 * public ItemController(ItemServiceInterface itemService) { this.itemService =
	 * itemService; }
	 */
	@PostMapping
	public List<Item> getItems() {
		return null;
	}

}
