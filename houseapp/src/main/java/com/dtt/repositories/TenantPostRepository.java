/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Post;
import com.dtt.pojo.Tenantpost;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface TenantPostRepository {
     List<Tenantpost> getTenantPosts(Map<String, String> params);

    int countTenantPost();

    void addOrUpdateTenantPost(Tenantpost p);

    Tenantpost getTenantPostById(int id);

    void deleteTenantPost(int id);

    List<Post> getPosts();
    
    List<Tenantpost> getTPosts(double lat, double lng);
}
