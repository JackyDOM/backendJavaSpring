package com.example.firstproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class foodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String foodName;
    private String foodDescription;
    private String foodIngredient;
    private String foodLocation;

    @ManyToOne
    @JoinColumn(name = "province_id") // Specify the foreign key column
    private provinceModel province;

    private String foodImageName;
    private String foodImageType;
    @Lob
    private byte[] foodImageData;

    // Method to return a shortened Base64 string for imageData
    public String getShortenedImageData() {
        if (foodImageData != null) {
            String base64Image = Base64.getEncoder().encodeToString(foodImageData);
            return base64Image.substring(0, 20) + "..."; // Show first 20 characters and add "..."
        }
        return null; // If no image data
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodIngredient() {
        return foodIngredient;
    }

    public void setFoodIngredient(String foodIngredient) {
        this.foodIngredient = foodIngredient;
    }

    public String getFoodLocation() {
        return foodLocation;
    }

    public void setFoodLocation(String foodLocation) {
        this.foodLocation = foodLocation;
    }

    public provinceModel getProvince() {
        return province;
    }

    public void setProvince(provinceModel province) {
        this.province = province;
    }

    public String getFoodImageName() {
        return foodImageName;
    }

    public void setFoodImageName(String foodImageName) {
        this.foodImageName = foodImageName;
    }

    public String getFoodImageType() {
        return foodImageType;
    }

    public void setFoodImageType(String foodImageType) {
        this.foodImageType = foodImageType;
    }

    public byte[] getFoodImageData() {
        return foodImageData;
    }

    public void setFoodImageData(byte[] foodImageData) {
        this.foodImageData = foodImageData;
    }
}
