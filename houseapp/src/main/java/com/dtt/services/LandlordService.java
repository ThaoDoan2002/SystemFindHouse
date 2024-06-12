/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface LandlordService {

    List<Landlord> getLandlords(Map<String, String> params);

    void addOrUpdateLandlord(Landlord l);

    Landlord getLandlordById(int id);

    int countLandlord();

    void deleteLandlord(int id);

    List<User> getUsers();
}
