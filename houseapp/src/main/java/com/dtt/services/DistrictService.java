/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.District;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface DistrictService {

    List<District> getDistricts();

    List<District> getDistrictsByProvince(int id);

    District getDistrictById(int id);

    List<District> getDistricts(Map<String, String> params);

    int countDistrict();

    void addOrUpdateDistrict(District d);

    void deleteDistrict(int id);
}
