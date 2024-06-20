/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.Post;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author doant
 */
public interface UserService extends UserDetailsService {

    List<User> getUsers(Map<String, String> params);

    int countUser();

    void addOrUpdateUser(User u, String currAv);

    User getUserById(int id);

    void deleteUser(int id);

    User getUserByUsername(String username);

    List<User> getUsers();

    boolean authUser(String username, String password);

    List<User> getFollowers(int lid);

//    List<Landlord> getLandlords();
}
