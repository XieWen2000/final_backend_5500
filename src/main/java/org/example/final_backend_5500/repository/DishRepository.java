package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface DishRepository extends MongoRepository<Dish, String> {
    Optional<Dish> findByName(String name);
    List<Dish> findByPriceGreaterThan(Double price);
    List<Dish> findByRestaurantId(String restaurantId);
}
