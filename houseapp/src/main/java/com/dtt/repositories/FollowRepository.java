/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Follow;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface FollowRepository {

    List<Follow> getFollows(Map<String, String> params);

    int countFollow();

    void addOrUpdateFollow(Follow fl);

    Follow getFollowById(int id);

    void deleteFollow(int id);

    List<User> getFollowers();

    List<User> getLandlords(int id);
}
