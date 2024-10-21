package com.example.firstproject.controller;

import com.example.firstproject.model.foodModel;
import com.example.firstproject.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // Get all food items
    @GetMapping
    public List<foodModel> getAllFoods() {
        return foodService.getAllFoods();
    }

    // Get food by ID
    @GetMapping("/{id}")
    public ResponseEntity<foodModel> getFoodById(@PathVariable int id) {
        Optional<foodModel> food = foodService.getFoodById(id);
        return food.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new food item
    @PostMapping
    public ResponseEntity<foodModel> createFood(@RequestBody foodModel food) {
        foodModel createdFood = foodService.createFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    // Update an existing food item
    @PutMapping("/{id}")
    public ResponseEntity<foodModel> updateFood(@PathVariable int id, @RequestBody foodModel food) {
        foodModel updatedFood = foodService.updateFood(id, food);
        return ResponseEntity.ok(updatedFood);
    }

    // Delete a food item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
