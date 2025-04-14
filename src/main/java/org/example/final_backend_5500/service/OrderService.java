package org.example.final_backend_5500.service;

import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(String id);
    List<Order> getOrdersByStatus(OrderStatus status);
    List<Order> getOrdersByCustomerId(String customerId);
    List<Order> getOrdersByRestaurantId(String restaurantId);
    Order updateOrder(String OrderId, Order order);
    void deleteOrder(String OrderId);
}
