package org.example.final_backend_5500.controller;

import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.dto.DasherInfoResponse;
import org.example.final_backend_5500.dto.DasherOrderStatusUpdateRequest;
import org.example.final_backend_5500.dto.RestaurantOrderStatusUpdateRequest;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;
import org.example.final_backend_5500.service.DasherService;
import org.example.final_backend_5500.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DasherService dasherService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable String customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrdersByRestaurantId(@PathVariable String restaurantId) {
        List<Order> orders = orderService.getOrdersByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurantId}/active")
    public ResponseEntity<List<Order>> getRestaurantActiveOrders(@PathVariable String restaurantId) {
        List<Order> orders = orderService.getRestaurantActiveOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/restaurant/{restaurantId}/completed")
    public ResponseEntity<List<Order>> getRestaurantCompletedOrders(@PathVariable String restaurantId) {
        List<Order> orders = orderService.getRestaurantCompletedOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/restaurant/{id}")
    public ResponseEntity<Order> restaurantUpdateOrderStatus(@PathVariable String id, @RequestBody RestaurantOrderStatusUpdateRequest request) {
        Order updatedOrder = orderService.restaurantUpdateOrderStatus(id, request.getStatus(), request.getRestaurantId());
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/customer/{customerId}/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByCustomerIdAndStatus(@PathVariable String customerId, @PathVariable OrderStatus status) {
        List<Order> orders = orderService.getOrdersByCustomerIdAndStatus(customerId, status);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/status/dasher/{id}")
    public ResponseEntity<Order> dasherUpdateOrderStatus(@PathVariable String id, @RequestBody DasherOrderStatusUpdateRequest request) {
        Order updatedOrder = orderService.dasherUpdateOrderStatus(id, request.getStatus(), request.getDasherId());
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<Order>> getUnassignedOrder() {
        List<Order> orders = orderService.getUnassignedOrder();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/assignDasher/{orderId}")
    public ResponseEntity<Order> assignDasherToOrder(@PathVariable String orderId, @RequestBody String dasherId) {
        DasherInfoResponse dasher = dasherService.getDasherById(dasherId);
        Order updatedOrder = orderService.assignDasherToOrder(orderId, dasherId, dasher.getFirstName() + " " + dasher.getLastName());
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/dasher/{dasherId}")
    public ResponseEntity<List<Order>> getOrdersByDasherId(@PathVariable String dasherId) {
        List<Order> orders = orderService.getOrdersByDasherId(dasherId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/dasher/{dasherId}/active")
    public ResponseEntity<Order> getActiveOrderByDasherId(@PathVariable String dasherId) {
        Order order = orderService.getActiveOrderByDasherId(dasherId);
        return ResponseEntity.ok(order);
    }
}
