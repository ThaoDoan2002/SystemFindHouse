/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Province;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface ProvinceRepository {

    List<Province> getProvinces();

    List<Province> getProvinces(Map<String, String> params);

    int countProvince();

    void addOrUpdateProvince(Province p);

    Province getProvinceById(int id);

    void deleteProvince(int id);
}
