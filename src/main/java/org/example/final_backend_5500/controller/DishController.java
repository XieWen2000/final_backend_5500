package org.example.final_backend_5500.controller;

import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.model.Dish;
import org.example.final_backend_5500.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        Dish createdDish = dishService.createDish(dish);
        return new ResponseEntity<>(createdDish, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable String id) {
        Dish dish = dishService.getDishById(id);
        return ResponseEntity.ok(dish);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Dish> getDishByName(@PathVariable String name) {
        Dish dish = dishService.getDishByName(name);
        return ResponseEntity.ok(dish);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Dish>> getDishesByRestaurantId(@PathVariable String restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurantId(restaurantId);
        return ResponseEntity.ok(dishes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable String id, @RequestBody Dish dish) {
        Dish updatedDish = dishService.updateDish(id, dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable String id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

}
