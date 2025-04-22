package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomerId(String customerId);
    List<Order> findByRestaurantId(String restaurantId);
    List<Order> findByCustomerIdAndStatus(String customerId, OrderStatus status);
    List<Order> findByRestaurantIdAndStatusIn(String restaurantId, List<OrderStatus> statuses);
    boolean existsByDasherId(String dasherId);
    List<Order> findByDasherId(String dasherId);
    List<Order> findByDasherIdAndStatusIn(String dasherId, List<OrderStatus> status);
}