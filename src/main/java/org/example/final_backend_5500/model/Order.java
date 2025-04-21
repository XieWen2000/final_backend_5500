package org.example.final_backend_5500.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String id;

    private String customerId;
    private String deliveryAddress;

    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;

    private String dasherId;
    private String dasherName;

    private Date orderTime;
    private OrderStatus status;
    private Double totalPrice;

    private List<OrderItem> items;
    private PaymentInfo payment;
}
