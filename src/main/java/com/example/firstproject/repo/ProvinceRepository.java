package com.example.firstproject.repo;

import com.example.firstproject.model.provinceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<provinceModel, Integer> {
}
