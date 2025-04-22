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
    void deleteOrder(String OrderId);
    List<Order> getOrdersByCustomerIdAndStatus(String customerId, OrderStatus status);
    List<Order> getRestaurantActiveOrders(String restaurantId);
    List<Order> getRestaurantCompletedOrders(String restaurantId);
    Order restaurantUpdateOrderStatus(String orderId, OrderStatus status, String restaurantId);

    Order dasherUpdateOrderStatus(String orderId, OrderStatus status, String dasherId);
    List<Order> getUnassignedOrder();
    Order assignDasherToOrder(String orderId, String dasherId, String dasherName);
    List<Order> getOrdersByDasherId(String dasherId);

    Order getActiveOrderByDasherId(String dasherId);
}
