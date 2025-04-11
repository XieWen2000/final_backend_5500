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
    private Long id;

    private String name;
    private String address;
    private List<Dish> dishes;
    private String imageUrl;
    private String adminUsername;
    private String adminPassword;
}
