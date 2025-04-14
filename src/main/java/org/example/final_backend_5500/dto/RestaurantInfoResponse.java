package org.example.final_backend_5500.dto;

import lombok.Data;
import org.example.final_backend_5500.model.Dish;
import org.example.final_backend_5500.model.Restaurant;

import java.util.List;

@Data
public class RestaurantInfoResponse {
    private String id;
    private String name;
    private String address;
    private List<Dish> dishes;
    private String imageUrl;
    private String logoUrl;
    private String email;
    private String phone;

    public RestaurantInfoResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.dishes = restaurant.getDishes();
        this.imageUrl = restaurant.getImageUrl();
        this.email = restaurant.getEmail();
        this.phone = restaurant.getPhone();
        this.logoUrl = restaurant.getLogoUrl();
    }
}

