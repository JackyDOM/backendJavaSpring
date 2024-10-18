package com.example.firstproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private int id;
    private String name;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    // Bidirectional relationship
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<provinceModel> provinces = new ArrayList<>(); // Initialize the list

    // Method to return a shortened Base64 string for imageData
    public String getShortenedImageData() {
        if (imageData != null) {
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return base64Image.substring(0, 20) + "..."; // Show first 20 characters and add "..."
        }
        return null; // If no image data
    }

    public List<provinceModel> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<provinceModel> provinces) {
        this.provinces = provinces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
