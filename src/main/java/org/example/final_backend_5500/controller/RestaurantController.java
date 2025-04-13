package org.example.final_backend_5500.controller;

import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.dto.RestaurantInfoResponse;
import org.example.final_backend_5500.model.Restaurant;
import org.example.final_backend_5500.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/signup")
    public ResponseEntity<RestaurantInfoResponse> createRestaurant(@RequestBody Restaurant restaurant) {
        RestaurantInfoResponse createdRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RestaurantInfoResponse> loginRestaurant(@RequestBody Restaurant restaurant) {
        RestaurantInfoResponse restaurantInfo = restaurantService.login(restaurant.getEmail(), restaurant.getPassword());
        return ResponseEntity.ok(restaurantInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantInfoResponse> getRestaurantById(@PathVariable String id) {
        RestaurantInfoResponse restaurant = restaurantService.findRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantInfoResponse> updateRestaurant(@PathVariable String id, @RequestBody Restaurant updatedData) {
        RestaurantInfoResponse updatedRestaurant = restaurantService.updateRestaurantAccountInfo(id, updatedData);
        return ResponseEntity.ok(updatedRestaurant);
    }


}

