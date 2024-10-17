package com.example.firstproject.service;

import com.example.firstproject.model.provinceModel;
import com.example.firstproject.repo.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<provinceModel> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<provinceModel> getProvinceById(int id) {
        return provinceRepository.findById(id);
    }

    public provinceModel createProvince(provinceModel province) {
        return provinceRepository.save(province);
    }

    public provinceModel updateProvince(int id, provinceModel updatedProvince) {
        return provinceRepository.findById(id)
                .map(province -> {
                    province.setProvinceName(updatedProvince.getProvinceName());
                    province.setLocation(updatedProvince.getLocation());
                    province.setCategory(updatedProvince.getCategory());
                    province.setProvinceImageName(updatedProvince.getProvinceImageName());
                    province.setProvinceImageType(updatedProvince.getProvinceImageType());
                    province.setProvinceImageData(updatedProvince.getProvinceImageData());
                    return provinceRepository.save(province);
                })
                .orElseGet(() -> {
                    updatedProvince.setId(id);
                    return provinceRepository.save(updatedProvince);
                });
    }

    public void deleteProvince(int id) {
        provinceRepository.deleteById(id);
    }
}
