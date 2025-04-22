package org.example.final_backend_5500.dto;

import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.model.Order;

public class DahserInfoResponse {
    private String dasherId;
    private String orderInfo;

    public DahserInfoResponse(Dasher dasher) {
        this.dasherId = dasher.getDasherId();
        this.orderInfo = dasher.getOrderId();
    }
}
