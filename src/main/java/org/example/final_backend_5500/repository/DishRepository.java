package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {
    List<Dish> findByName(String name);
    List<Dish> findByPriceGreaterThan(Double price);
}
