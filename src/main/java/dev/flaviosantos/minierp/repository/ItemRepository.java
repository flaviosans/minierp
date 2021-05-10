package dev.flaviosantos.minierp.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.flaviosantos.minierp.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
	List<Item> findByOrderId(UUID orderId);

	Item findByIdAndOrderId(UUID orderId, UUID id);
}
