package dev.flaviosantos.minierp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.flaviosantos.minierp.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {

}
