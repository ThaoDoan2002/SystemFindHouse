/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface UserRepository {

    List<User> getUsers(Map<String, String> params);

    int countUser();

    void addOrUpdateUser(User u);

    User getUserById(int id);

    void deleteUser(int id);

    User getUserByUsername(String username);

    List<User> getUsers();

    boolean authUser(String username, String password);
    
    List<User> getFollowers(int lid);

//    List<Landlord> getLandlords();
}
