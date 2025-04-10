package org.example.final_backend_5500.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    private String dishId;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
}
