/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Province;
import com.dtt.repositories.ProvinceRepository;
import com.dtt.services.ProvinceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public List<Province> getProvinces() {
        return this.provinceRepo.getProvinces();
    }

    @Override
    public List<Province> getProvinces(Map<String, String> params) {
        return this.provinceRepo.getProvinces(params);
    }

    @Override
    public int countProvince() {
        return this.provinceRepo.countProvince();
    }

    @Override
    public void addOrUpdateProvince(Province p) {
        this.provinceRepo.addOrUpdateProvince(p);
    }

    @Override
    public Province getProvinceById(int id) {
        return this.provinceRepo.getProvinceById(id);
    }

    @Override
    public void deleteProvince(int id) {
        this.provinceRepo.deleteProvince(id);
    }

}
