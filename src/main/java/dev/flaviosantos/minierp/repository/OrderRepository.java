package dev.flaviosantos.minierp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.flaviosantos.minierp.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
