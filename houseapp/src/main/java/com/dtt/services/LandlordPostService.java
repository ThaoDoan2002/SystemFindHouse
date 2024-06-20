/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Landlordpost;
import com.dtt.pojo.Post;
import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface LandlordPostService {
          List<Landlordpost> getLandlordPosts(Map<String, String> params);

    int countLandlordPost();

    void addOrUpdateLandlordPost(Landlordpost p);

    Landlordpost getLandlordPostById(int id);

    void deleteLandlordPost(int id);

    List<Post> getPosts();
    
    List<Room> getRoomsByPostUser(int id);
}
