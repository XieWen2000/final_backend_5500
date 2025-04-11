package org.example.final_backend_5500.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfo {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
}
