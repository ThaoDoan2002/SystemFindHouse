/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.District;
import com.dtt.pojo.Ward;
import java.util.List;

/**
 *
 * @author doant
 */
public interface WardsRepository {

    List<Ward> getWards();

    List<Ward> getWardsByDistrict(int id);

    Ward getWardById(int id);
}
