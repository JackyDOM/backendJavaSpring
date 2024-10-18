package com.example.firstproject.controller;

import com.example.firstproject.model.categoryModel;
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
    @GetMapping
    public List<Map<String, Object>> getAllCategories() {
        List<categoryModel> categories = categoryService.getAllCategories();
        List<Map<String, Object>> result = new ArrayList<>();

        // Prepare the response with shortened imageData
        for (categoryModel category : categories) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", category.getId());
            categoryMap.put("name", category.getName());
            categoryMap.put("imageName", category.getImageName());
            categoryMap.put("imageType", category.getImageType());
            categoryMap.put("shortenedImageData", category.getShortenedImageData()); // Include shortened image data
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
