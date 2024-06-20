/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import java.util.List;

/**
 *
 * @author doant
 */
public interface TypeImageRepository {

    List<Typeimage> getTypeImages();

    void addOrUpdateTypeImage(Typeimage t);

    Typeimage getTypeImageById(int id);

    void deleteTypeImage(int id);

   
}
