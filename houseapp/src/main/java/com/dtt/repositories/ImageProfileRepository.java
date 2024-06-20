/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface ImageProfileRepository {
    List<Imageprofile> getImageProfiles(Map<String, String> params);

    int countImageProfile();

    void addOrUpdateImageProfile(Imageprofile img);

    Imageprofile getImageProfileById(int id);

    void deleteImageProfile(int id);

    List<Typeimage> listTypes();

    List<User> listUsers();

}
