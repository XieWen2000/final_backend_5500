package org.example.final_backend_5500.service;

import org.example.final_backend_5500.model.Dish;
import org.example.final_backend_5500.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish getDishById(String id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish getDishByName(String name) {
        return dishRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Dish> getDishesByRestaurantId(String restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish updateDish(String id, Dish dish) {
        dish.setDishId(id);
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(String id) {
        dishRepository.deleteById(id);
    }

}