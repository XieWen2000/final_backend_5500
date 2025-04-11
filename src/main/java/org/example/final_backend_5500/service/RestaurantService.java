package org.example.final_backend_5500.service;

import org.example.final_backend_5500.dto.RestaurantInfoResponse;
import org.example.final_backend_5500.model.Restaurant;
import org.example.final_backend_5500.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Restaurant Signup
    public RestaurantInfoResponse createRestaurant(Restaurant restaurant) {
        if (restaurantRepository.existsByAdminUsername(restaurant.getAdminUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Admin username already exists"
            );
        }
        Restaurant createdRestaurant = restaurantRepository.save(restaurant);
        return new RestaurantInfoResponse(createdRestaurant);
    }

    // Restaurant Admin Login
    public RestaurantInfoResponse login(String adminUsername, String password) {
        Restaurant restaurant = restaurantRepository.findByAdminUsername(adminUsername);
        if (restaurant == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Admin username does not exist"
            );
        }
        if (!restaurant.getAdminPassword().equals(password)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid password"
            );
        }
        return new RestaurantInfoResponse(restaurant);
    }


    public RestaurantInfoResponse findRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .map(RestaurantInfoResponse::new)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Restaurant not found"
                ));
    }
}
