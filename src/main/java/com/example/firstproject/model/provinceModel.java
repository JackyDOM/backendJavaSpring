package com.example.firstproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class provinceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String provinceName;
    private String location;

    @ManyToOne // Specify the relationship with categoryModel
    @JoinColumn(name = "category_id") // Specify the foreign key column
    private categoryModel category;

    @OneToMany(mappedBy = "province") // Add this to establish the bidirectional relationship
    private List<foodModel> foods;

    private String provinceImageName;
    private String provinceImageType;
    @Lob
    private byte[] provinceImageData;

    // Method to return a shortened Base64 string for imageData
    public String getShortenedImageData() {
        if (provinceImageData != null) {
            String base64Image = Base64.getEncoder().encodeToString(provinceImageData);
            return base64Image.substring(0, 20) + "..."; // Show first 20 characters and add "..."
        }
        return null; // If no image data
    }


    public List<foodModel> getFoods() {
        return foods;
    }

    public void setFoods(List<foodModel> foods) {
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public categoryModel getCategory() {
        return category;
    }

    public void setCategory(categoryModel category) {
        this.category = category;
    }

    public String getProvinceImageName() {
        return provinceImageName;
    }

    public void setProvinceImageName(String provinceImageName) {
        this.provinceImageName = provinceImageName;
    }

    public String getProvinceImageType() {
        return provinceImageType;
    }

    public void setProvinceImageType(String provinceImageType) {
        this.provinceImageType = provinceImageType;
    }

    public byte[] getProvinceImageData() {
        return provinceImageData;
    }

    public void setProvinceImageData(byte[] provinceImageData) {
        this.provinceImageData = provinceImageData;
    }
}
