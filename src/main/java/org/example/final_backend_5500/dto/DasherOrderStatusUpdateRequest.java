package org.example.final_backend_5500.dto;

import lombok.Data;
import org.example.final_backend_5500.model.OrderStatus;

@Data
public class DasherOrderStatusUpdateRequest {
    private OrderStatus status;
    private String dasherId;
}
