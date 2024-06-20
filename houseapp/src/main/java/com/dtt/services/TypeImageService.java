/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Typeimage;
import java.util.List;

/**
 *
 * @author doant
 */
public interface TypeImageService {
     List<Typeimage> getTypeImages();

    void addOrUpdateTypeImage(Typeimage t);

    Typeimage getTypeImageById(int id);


    void deleteTypeImage(int id);
}
