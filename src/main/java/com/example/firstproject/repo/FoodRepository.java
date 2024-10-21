package com.example.firstproject.repo;

import com.example.firstproject.model.foodModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<foodModel, Integer> {
}
