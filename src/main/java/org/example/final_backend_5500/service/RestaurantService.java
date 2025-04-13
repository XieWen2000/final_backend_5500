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
        if (restaurantRepository.existsByEmail(restaurant.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Email already exists"
            );
        }
        if (restaurantRepository.existsByPhone(restaurant.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Phone number already exists"
            );
        }
        Restaurant createdRestaurant = restaurantRepository.save(restaurant);
        return new RestaurantInfoResponse(createdRestaurant);
    }

    // Restaurant Admin Login
    public RestaurantInfoResponse login(String email, String password) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);
        if (restaurant == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Admin username does not exist"
            );
        }
        if (!restaurant.getPassword().equals(password)) {
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
