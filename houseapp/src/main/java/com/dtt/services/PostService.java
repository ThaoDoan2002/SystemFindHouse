/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Post;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface PostService {

    List<Post> getPosts(Map<String, String> params);

    int countPost();

    void addOrUpdatePost(Post p);

    Post getPostById(int id);

    void deletePost(int id);

    List<Post> getPosts();
}
