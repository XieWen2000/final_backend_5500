package org.example.final_backend_5500.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    private String id;

    private String name;
    private String address;
    private String imageUrl;
    private String logoUrl;
    private String email;
    private String phone;
    private String password;
    private Double rating;
    private Integer numberOfRatings;
}
