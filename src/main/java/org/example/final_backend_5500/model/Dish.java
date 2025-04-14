package org.example.final_backend_5500.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "dishes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    @Id
    private String dishId;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private String restaurantId;
}
