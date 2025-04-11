package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByName(String name);
    Restaurant findByAdminUsername(String adminUsername);  // Admin user name needs to be unique
    boolean existsByAdminUsername(String adminUsername);
}
