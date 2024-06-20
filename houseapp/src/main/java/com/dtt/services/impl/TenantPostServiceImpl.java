/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Post;
import com.dtt.pojo.Tenantpost;
import com.dtt.repositories.TenantPostRepository;
import com.dtt.services.TenantPostService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class TenantPostServiceImpl implements TenantPostService{

    @Autowired
    private TenantPostRepository tpostRepo;
    
    @Override
    public List<Tenantpost> getTenantPosts(Map<String, String> params) {
        return this.tpostRepo.getTenantPosts(params);
    }

    @Override
    public int countTenantPost() {
        return this.tpostRepo.countTenantPost();
    }

    @Override
    public void addOrUpdateTenantPost(Tenantpost p) {
        
        this.tpostRepo.addOrUpdateTenantPost(p);
    }

    @Override
    public Tenantpost getTenantPostById(int id) {
        return this.tpostRepo.getTenantPostById(id);
    }

    @Override
    public void deleteTenantPost(int id) {
        this.tpostRepo.deleteTenantPost(id);
    }

    @Override
    public List<Post> getPosts() {
        return this.tpostRepo.getPosts();
    }

    @Override
    public List<Tenantpost> getTPosts(double lat, double lng) {
        return this.tpostRepo.getTPosts(lat, lng);
    }
}
    
