package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByOrderTimeAfter(Date orderTime);
}