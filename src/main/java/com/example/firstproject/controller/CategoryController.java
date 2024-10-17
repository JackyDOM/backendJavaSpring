package com.example.firstproject.controller;


import com.example.firstproject.model.categoryModel;
import com.example.firstproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET all categories
    @GetMapping
    public List<categoryModel> getAllCategories() {
        return categoryService.getAllCategories();
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
        //System.out.println("Received category: " + category);
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
