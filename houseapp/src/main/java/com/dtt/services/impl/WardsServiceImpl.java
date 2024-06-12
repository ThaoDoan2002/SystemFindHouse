/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Ward;
import com.dtt.repositories.WardsRepository;
import com.dtt.services.WardsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class WardsServiceImpl implements WardsService {

    @Autowired
    WardsRepository wardsRepo;

    @Override
    public List<Ward> getWardsByDistrict(int id) {
        return this.wardsRepo.getWardsByDistrict(id);
    }

    @Override
    public List<Ward> getWards() {
        return this.wardsRepo.getWards();
    }

    @Override
    public Ward getWardById(int id) {
        return this.wardsRepo.getWardById(id);
    }

}
