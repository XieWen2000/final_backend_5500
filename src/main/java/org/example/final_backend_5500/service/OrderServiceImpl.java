package org.example.final_backend_5500.service;

import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;
import org.example.final_backend_5500.model.Restaurant;
import org.example.final_backend_5500.repository.DishRepository;
import org.example.final_backend_5500.repository.OrderRepository;
import org.example.final_backend_5500.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Order createOrder(Order order) {
        order.setOrderTime(new Date());
        order.setStatus(OrderStatus.PLACED);
        Restaurant restaurant = restaurantRepository.findById(order.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Restaurant not found"
                ));
        order.setRestaurantAddress(restaurant.getAddress());
        order.setRestaurantName(restaurant.getName());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrdersByCustomerIdAndStatus(String customerId, OrderStatus status) {
        return orderRepository.findByCustomerIdAndStatus(customerId, status);
    }

    @Override
    public List<Order> getRestaurantActiveOrders(String restaurantId) {
        List<OrderStatus> statuses = List.of(OrderStatus.PLACED, OrderStatus.PREPARING, OrderStatus.READY);
        return orderRepository.findByRestaurantIdAndStatusIn(restaurantId, statuses);
    }

    @Override
    public List<Order> getRestaurantCompletedOrders(String restaurantId) {
        List<OrderStatus> statuses = List.of(OrderStatus.ON_THE_WAY, OrderStatus.DELIVERED, OrderStatus.CANCELLED);
        return orderRepository.findByRestaurantIdAndStatusIn(restaurantId, statuses);
    }


    @Override
    public Order restaurantUpdateOrderStatus(String orderId, OrderStatus status, String restaurantId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        Set<OrderStatus> allowedStatuses = Set.of(OrderStatus.CANCELLED, OrderStatus.READY, OrderStatus.PREPARING);

        if (!Objects.equals(order.getRestaurantId(), restaurantId) || !allowedStatuses.contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not authorized to update this order"
            );
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order dasherUpdateOrderStatus(String orderId, OrderStatus status, String dasherId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        Set<OrderStatus> allowedStatuses = Set.of(OrderStatus.ON_THE_WAY, OrderStatus.DELIVERED, OrderStatus.CANCELLED);

        if (!Objects.equals(order.getDasherId(), dasherId) || !allowedStatuses.contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not authorized to update this order"
            );
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getUnassignedOrder() {
        List<OrderStatus> statuses = List.of(OrderStatus.READY);
        return orderRepository.findByDasherIdAndStatusIn(null, statuses);
    }

    @Override
    public Order assignDasherToOrder(String orderId, String dasherId, String dasherName) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        if (order.getDasherId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Order already has a dasher assigned"
            );
        }
        List<OrderStatus> statuses = List.of(OrderStatus.ON_THE_WAY, OrderStatus.READY);
        List<Order> existingOrders = orderRepository.findByDasherIdAndStatusIn(dasherId, statuses);
        if (!existingOrders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Dasher already has an order assigned"
            );
        }
        order.setDasherId(dasherId);
        order.setDasherName(dasherName);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByDasherId(String dasherId) {
        return orderRepository.findByDasherId(dasherId);
    }

    @Override
    public Order getActiveOrderByDasherId(String dasherId) {
        List<OrderStatus> statuses = List.of(OrderStatus.ON_THE_WAY, OrderStatus.READY);
        List<Order> foundOrder = orderRepository.findByDasherIdAndStatusIn(dasherId, statuses);
        if (foundOrder.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No active order found for this dasher"
            );
        }
        if (foundOrder.size() > 1) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Multiple active orders found for this dasher"
            );
        }
        return foundOrder.get(0);
    }
}

