package com.example.firstproject.controller;

import com.example.firstproject.model.provinceModel;
import com.example.firstproject.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    // GET all provinces
    @GetMapping
    public List<Map<String, Object>> getAllProvinces() {
        List<provinceModel> provinces = provinceService.getAllProvinces();
        List<Map<String, Object>> result = new ArrayList<>();

        for (provinceModel province : provinces) {
            Map<String, Object> provinceMap = new HashMap<>();
            provinceMap.put("id", province.getId());
            provinceMap.put("provinceName", province.getProvinceName());
            provinceMap.put("location", province.getLocation());

            // Include category details
            if (province.getCategory() != null) {
                Map<String, Object> categoryMap = new HashMap<>();
                categoryMap.put("id", province.getCategory().getId());
                categoryMap.put("name", province.getCategory().getName());
                categoryMap.put("imageName", province.getCategory().getImageName());
                categoryMap.put("imageType", province.getCategory().getImageType());
                categoryMap.put("shortenedImageData", province.getCategory().getShortenedImageData());

                provinceMap.put("category", categoryMap); // Add the category map
            } else {
                provinceMap.put("category", null); // If no category is found
            }

            provinceMap.put("provinceImageName", province.getProvinceImageName());
            provinceMap.put("imageType", province.getProvinceImageType());
            provinceMap.put("shortenedImageData", province.getShortenedImageData());
            result.add(provinceMap);
        }

        return result;
    }


    // GET province by id
    @GetMapping("/{id}")
    public ResponseEntity<provinceModel> getProvinceById(@PathVariable int id) {
        Optional<provinceModel> province = provinceService.getProvinceById(id);
        return province.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new province
    @PostMapping
    public provinceModel createProvince(@RequestBody provinceModel province) {
        return provinceService.createProvince(province);
    }

    // PUT update a province
    @PutMapping("/{id}")
    public ResponseEntity<provinceModel> updateProvince(@PathVariable int id, @RequestBody provinceModel updatedProvince) {
        return ResponseEntity.ok(provinceService.updateProvince(id, updatedProvince));
    }

    // DELETE province
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable int id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }
}
