package org.example.final_backend_5500.model;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "dashers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dasher extends User{
    private String licenseNumber;
    private String vehicleInfo;
    private String orderId;
    private String dasherId;
}
