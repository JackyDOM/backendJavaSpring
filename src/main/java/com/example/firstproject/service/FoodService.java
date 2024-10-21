package com.example.firstproject.service;

import com.example.firstproject.model.foodModel;
import com.example.firstproject.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<foodModel> getAllFoods() {
        return foodRepository.findAll();
    }

    // Get food by ID
    public Optional<foodModel> getFoodById(int id) {
        return foodRepository.findById(id);
    }

    // Create a new food item
    public foodModel createFood(foodModel food) {
        return foodRepository.save(food);
    }

    // Update an existing food item
    public foodModel updateFood(int id, foodModel food) {
        food.setId(id); // Set the ID of the food item to update
        return foodRepository.save(food);
    }

    // Delete a food item
    public void deleteFood(int id) {
        foodRepository.deleteById(id);
    }
}
