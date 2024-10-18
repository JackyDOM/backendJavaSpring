package com.example.firstproject.service;

import com.example.firstproject.model.categoryModel;
import com.example.firstproject.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<categoryModel> getAllCategories() {
        return categoryRepository.findAll(); // This fetches categories with their provinces
    }

    public Optional<categoryModel> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public categoryModel createCategory(categoryModel category) {
        return categoryRepository.save(category);
    }

    public categoryModel updateCategory(int id, categoryModel updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    category.setImageName(updatedCategory.getImageName());
                    category.setImageType(updatedCategory.getImageType());
                    category.setImageData(updatedCategory.getImageData());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    updatedCategory.setId(id);
                    return categoryRepository.save(updatedCategory);
                });
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
