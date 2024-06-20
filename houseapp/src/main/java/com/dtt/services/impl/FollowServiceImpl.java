/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Follow;
import com.dtt.pojo.User;
import com.dtt.repositories.FollowRepository;
import com.dtt.services.FollowService;
import java.io.Serial;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepo;

    @Override
    public List<Follow> getFollows(Map<String, String> params) {
        return this.followRepo.getFollows(params);
    }

    @Override
    public int countFollow() {
        return this.followRepo.countFollow();
    }

    @Override
    public void addOrUpdateFollow(Follow fl) {
        this.followRepo.addOrUpdateFollow(fl);
    }

    @Override
    public Follow getFollowById(int id) {
        return this.followRepo.getFollowById(id);
    }

    @Override
    public void deleteFollow(int id) {
        this.followRepo.deleteFollow(id);
    }

    @Override
    public List<User> getFollowers() {
        return this.followRepo.getFollowers();
    }

    @Override
    public List<User> getLandlords(int id) {
        return this.followRepo.getLandlords(id);
    }

}
