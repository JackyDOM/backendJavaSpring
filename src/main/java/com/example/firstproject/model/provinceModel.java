package com.example.firstproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String provinceImageName;
    private String provinceImageType;
    @Lob
    private byte[] provinceImageData;

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
