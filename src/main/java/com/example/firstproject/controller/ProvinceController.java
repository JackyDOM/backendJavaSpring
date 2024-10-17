package com.example.firstproject.controller;

import com.example.firstproject.model.provinceModel;
import com.example.firstproject.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    // GET all provinces
    @GetMapping
    public List<provinceModel> getAllProvinces() {
        return provinceService.getAllProvinces();
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
