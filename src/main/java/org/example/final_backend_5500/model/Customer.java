package org.example.final_backend_5500.model;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;


@Document(collection = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends User {
    private Optional<String> address;
    private Optional<List<PaymentInfo>> paymentInfo;
}
