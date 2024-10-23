package com.example.firstproject.controller;

import com.example.firstproject.model.categoryModel;
import com.example.firstproject.model.foodModel;
import com.example.firstproject.model.provinceModel;
import com.example.firstproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // GET all categories with shortened imageData
    // GET all categories with provinces and shortened imageData
    @GetMapping
    public List<Map<String, Object>> getAllCategories() {
        List<categoryModel> categories = categoryService.getAllCategories();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<Integer, Map<String, Object>> provinceMap = new HashMap<>(); // To store unique provinces by ID

        for (categoryModel category : categories) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", category.getId());
            categoryMap.put("name", category.getName());
            categoryMap.put("imageName", category.getImageName());
            categoryMap.put("imageType", category.getImageType());
            categoryMap.put("shortenedImageData", category.getShortenedImageData());

            for (provinceModel province : category.getProvinces()) {
                // Check if province already exists in the map
                if (!provinceMap.containsKey(province.getId())) {
                    Map<String, Object> provinceDetails = new HashMap<>();
                    provinceDetails.put("id", province.getId());
                    provinceDetails.put("provinceName", province.getProvinceName());
                    provinceDetails.put("location", province.getLocation());
                    provinceDetails.put("shortenedImageData", province.getShortenedImageData());

                    // Add foods for this province
                    List<Map<String, Object>> foodList = new ArrayList<>();
                    for (foodModel food : province.getFoods()) {
                        Map<String, Object> foodMap = new HashMap<>();
                        foodMap.put("id", food.getId());
                        foodMap.put("foodName", food.getFoodName());
                        foodMap.put("foodDescription", food.getFoodDescription());
                        foodMap.put("foodIngredient", food.getFoodIngredient());
                        foodMap.put("foodLocation", food.getFoodLocation());
                        foodMap.put("foodImageName", food.getFoodImageName());
                        foodMap.put("foodImageType", food.getFoodImageType());
                        foodList.add(foodMap);
                    }
                    provinceDetails.put("foods", foodList);
                    provinceMap.put(province.getId(), provinceDetails); // Store unique province
                }
            }
            // Add all unique provinces to the category map
            categoryMap.put("provinces", new ArrayList<>(provinceMap.values()));
            result.add(categoryMap);
        }
        return result;
    }


    // GET category by id
    @GetMapping("/{id}")
    public ResponseEntity<categoryModel> getCategoryById(@PathVariable int id) {
        Optional<categoryModel> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new category
    @PostMapping
    public categoryModel createCategory(@RequestBody categoryModel category) {
        return categoryService.createCategory(category);
    }

    // PUT update a category
    @PutMapping("/{id}")
    public ResponseEntity<categoryModel> updateCategory(@PathVariable int id, @RequestBody categoryModel updatedCategory) {
        return ResponseEntity.ok(categoryService.updateCategory(id, updatedCategory));
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
