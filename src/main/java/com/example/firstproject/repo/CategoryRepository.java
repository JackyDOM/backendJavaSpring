package com.example.firstproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.firstproject.model.categoryModel;

public interface CategoryRepository extends JpaRepository<categoryModel, Integer> {
}
