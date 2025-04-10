package org.example.final_backend_5500.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    private String userId;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private Date paidAt;
}
