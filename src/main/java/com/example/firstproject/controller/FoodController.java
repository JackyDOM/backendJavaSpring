package com.example.firstproject.controller;

import com.example.firstproject.model.foodModel;
import com.example.firstproject.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // Get all food items
    @GetMapping
    public List<Map<String, Object>> getAllFoods() {
        List<foodModel> foods = foodService.getAllFoods();
        List<Map<String, Object>> result = new ArrayList<>();

        for(foodModel food : foods){
            Map<String, Object> foodMap = new HashMap<>();
            foodMap.put("id", food.getId());
            foodMap.put("foodName", food.getFoodName());
            foodMap.put("foodDescription", food.getFoodDescription());
            foodMap.put("foodIngredient", food.getFoodIngredient());
            foodMap.put("foodLocation", food.getFoodLocation());
            foodMap.put("foodImageName", food.getFoodImageName());
            foodMap.put("foodImageType", food.getFoodImageType());
            foodMap.put("shortenedImageData", food.getShortenedImageData());
            result.add(foodMap);
        }

        return result;
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
