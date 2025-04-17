package org.example.final_backend_5500.service;

import org.example.final_backend_5500.model.Dish;
import java.util.List;

public interface DishService {
    Dish createDish(Dish dish);
    Dish getDishById(String id);
    Dish getDishByName(String name);
    List<Dish> getAllDishes();
    List<Dish> getDishesByRestaurantId(String restaurantId);
    Dish updateDish(String id, Dish updatedDish);
    void deleteDish(String id);
}
