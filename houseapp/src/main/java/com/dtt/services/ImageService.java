/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Image;
import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface ImageService {

   List<Image> getImages(Map<String, String> params);

    int countImage();

    void addOrUpdateImage(Image img, String curImg);
    
     void addOrUpdateImage(Image img);

    Image getImageById(int id);

    void deleteImage(int id);
}
