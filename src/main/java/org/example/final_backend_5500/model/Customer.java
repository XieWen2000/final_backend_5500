package org.example.final_backend_5500.model;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends User {
    private String address;
    private PaymentInfo paymentInfo;
}
