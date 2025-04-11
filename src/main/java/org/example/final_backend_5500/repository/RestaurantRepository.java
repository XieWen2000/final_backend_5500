package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByName(String name);
    List<Restaurant> findByAdminUsername(String adminUsername);
}
