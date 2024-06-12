/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.District;
import com.dtt.repositories.DistrictRepository;
import com.dtt.services.DistrictService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepo;

    @Override
    public List<District> getDistrictsByProvince(int id) {
        return this.districtRepo.getDistrictsByProvince(id);
    }

    @Override
    public List<District> getDistricts() {
        return this.districtRepo.getDistricts();
    }

    @Override
    public District getDistrictById(int id) {
        return this.districtRepo.getDistrictById(id);
    }

    @Override
    public List<District> getDistricts(Map<String, String> params) {
        return this.districtRepo.getDistricts(params);
    }

    @Override
    public int countDistrict() {
        return this.districtRepo.countDistrict();
    }

    @Override
    public void addOrUpdateDistrict(District d) {
        this.districtRepo.addOrUpdateDistrict(d);
    }

    @Override
    public void deleteDistrict(int id) {
        this.districtRepo.deleteDistrict(id);
    }

}
