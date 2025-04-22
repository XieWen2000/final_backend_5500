package org.example.final_backend_5500.service;

import org.example.final_backend_5500.dto.DahserInfoResponse;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.model.Order;
import org.example.final_backend_5500.model.OrderStatus;
import org.example.final_backend_5500.repository.DasherRepository;
import org.example.final_backend_5500.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class DasherService {

    private final DasherRepository dasherRepository;
    private final OrderRepository orderRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Autowired
    public DasherService(DasherRepository dasherRepository, OrderRepository orderRepository, OrderService orderService) {
        this.dasherRepository = dasherRepository;
        this.orderRepository = orderRepository;
    }

    public Dasher findDasher(Dasher dasher) {

        Dasher newDasher = new Dasher();
        return dasherRepository.save(newDasher);
    }

    public Dasher findDasherById(String id) {
        return dasherRepository.findById(id).orElse(null);
    }

    public DahserInfoResponse orderToBeDelivered(Dasher dasher, Order updateOrder) {
        if (dasher.getOrderId() == null && "READY".equals(updateOrder.getStatus())) {
            dasher.setOrderId(updateOrder.getId());
            scheduler.schedule(() -> {
                updateOrder.setStatus(OrderStatus.ON_THE_WAY);
                orderRepository.save(updateOrder);
            }, 5, TimeUnit.SECONDS);

            updateOrder.setStatus(OrderStatus.valueOf("ON_THE_WAY"));

            return new DahserInfoResponse(dasher);

        } else if (dasher.getOrderId() != null && "ON_THE_WAY".equals(updateOrder.getStatus())) {
            scheduler.schedule(() -> {
                updateOrder.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(updateOrder);
            }, 15, TimeUnit.SECONDS);
            orderRepository.save(updateOrder);
            Dasher updatedDasher = dasherRepository.save(dasher);
        }
        return new DahserInfoResponse(dasher);
    }

}
