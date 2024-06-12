/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import com.dtt.repositories.LandlordRepository;
import com.dtt.services.LandlordService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class LandlordServiceImpl implements LandlordService {

    @Autowired
    private LandlordRepository landlordRepo;

    @Override
    public List<Landlord> getLandlords(Map<String, String> params) {
        return this.landlordRepo.getLandlords(params);
    }

    @Override
    public void addOrUpdateLandlord(Landlord l) {
        this.landlordRepo.addOrUpdateLandlord(l);
    }
    @Override
    public Landlord getLandlordById(int id) {
        return this.landlordRepo.getLandlordById(id);
    }

    @Override
    public int countLandlord() {
        return this.landlordRepo.countLandlord();
    }

    @Override
    public void deleteLandlord(int id) {
        this.landlordRepo.deleteLandlord(id);
    }

    @Override
    public List<User> getUsers() {
        return this.landlordRepo.getUsers();
    }

}
